package com.trjst.service.api;

import com.trjst.mapper.*;
import com.trjst.model.*;
import com.trjst.util.ServerConfig;
import com.trjst.util.WeixinUtil;
import com.trjst.vo.OrderPay;
import com.trjst.vo.Pub;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class PayService {

    @Autowired
    private JstOrderMapper jstOrderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommodityInfoMapper commodityInfoMapper;

    @Autowired
    private DelivMerchMapper delivMerchMapper;

    @Autowired
    private SpendRecordMapper spendRecordMapper;

    @Autowired
    private MerchantInfoMapper merchantInfoMapper;

    @Autowired
    private DeliveryMapper deliveryMapper;

    /**
     * 微信统一支付接口
     * @param request
     * @param resp
     * @param orderPay
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Map wxpaymoney(HttpServletRequest request, HttpServletResponse resp, OrderPay orderPay)
            throws Exception {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
        Map map = new HashMap();
        try {
            String goods_desc;
            String order_code = String.valueOf(System.currentTimeMillis() / 1000) + (int) (Math.random() * 9000 + 1000);
            User user = userMapper.selectByPrimaryKey(orderPay.getUser_id());
            if(orderPay.getType()==2){
                goods_desc = "用户充值付款";
                JstOrder jo = new JstOrder();
                jo.setOrder_no(order_code);
                jo.setPay_no(order_code);
                jo.setPay_price(orderPay.getTotal_money());
                jo.setUser_id(orderPay.getUser_id());
                jo.setTotal_price(orderPay.getTotal_money());
                jo.setGoods_desc(goods_desc);
                jo.setType(2);
                jo.setPay_status(1);
                jstOrderMapper.insertSelective(jo);
            }else if(orderPay.getType()==3){
                goods_desc = "商家入驻缴纳费用";
                JstOrder jo = new JstOrder();
                jo.setOrder_no(order_code);
                jo.setPay_no(order_code);
                jo.setOrder_price(orderPay.getTotal_money());
                jo.setPay_price(orderPay.getTotal_money());
                jo.setUser_id(orderPay.getUser_id());
                jo.setTotal_price(orderPay.getTotal_money());
                jo.setGoods_desc(goods_desc);
                jo.setType(3);
                jo.setPay_status(1);
                jo.setMerchant_id(orderPay.getMerchant_id());
                jstOrderMapper.insertSelective(jo);
            } else if(orderPay.getType()==4){
                goods_desc = "配送员入驻缴纳费用";
                JstOrder jo = new JstOrder();
                jo.setOrder_no(order_code);
                jo.setPay_no(order_code);
                jo.setOrder_price(orderPay.getTotal_money());
                jo.setPay_price(orderPay.getTotal_money());
                jo.setUser_id(orderPay.getUser_id());
                jo.setTotal_price(orderPay.getTotal_money());
                jo.setGoods_desc(goods_desc);
                jo.setType(4);
                jo.setPay_status(1);
                jstOrderMapper.insertSelective(jo);
            }else {
                goods_desc = "购买商品付款";
                for (Pub op : orderPay.getPub()) {
                    // 生成支付流水号
                    //String oc = String.valueOf(System.currentTimeMillis() / 1000) + (int) (Math.random() * 9000 + 1000);
                    JstOrder jo = new JstOrder();
                    jo.setPay_no(order_code);
                    jo.setPay_price(op.getMoney());
                    jo.setId(op.getOrder_id());
                    jo.setPay_status(1);
                    jo.setGoods_desc(goods_desc);
                    jstOrderMapper.updateByPrimaryKeySelective(jo);
                }
            }
            // 支付逻辑
            Map<String, String> wxPayRespMap = WeixinUtil.configPayParam(request, order_code, String.valueOf(orderPay.getTotal_money().multiply(new BigDecimal(100)).intValue()), null,
                    goods_desc, ServerConfig.getPaynotify(),user.getOpen_id());
//            JSONObject json = new JSONObject();
//            json.putAll(wxPayRespMap);
            map.put("code",200);
            map.put("msg","success");
            map.put("data",wxPayRespMap);
            map.put("total_money",orderPay.getTotal_money());
        } catch (Exception e) {
            log.error("wxPayMoney：{}",e);
            map.put("code",500);
            map.put("msg","error");
        }
        return map;
    }

    /**
     * 差价支付接口2
     * @param orderPay
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Map wxPaySpread2(HttpServletRequest request, HttpServletResponse resp, OrderPay orderPay)
            throws Exception {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
        Map map = new HashMap();
        try {
            String order_code = String.valueOf(System.currentTimeMillis() / 1000) + (int) (Math.random() * 9000 + 1000);
            String goods_desc = "购买商品付款";
            for (Pub op : orderPay.getPub()) {
                JstOrder jo = new JstOrder();
                jo.setSpread_price(op.getMoney());
                jo.setSpread_status(1);
                jo.setSpread_no(order_code);
                jo.setSpread_jin_num(op.getJin_num());
                jo.setId(op.getOrder_id());
                jstOrderMapper.updateByPrimaryKeySelective(jo);
            }
            User user = userMapper.selectByPrimaryKey(orderPay.getUser_id());
            // 支付逻辑
            Map<String, String> wxPayRespMap = WeixinUtil.configPayParam(request, order_code, String.valueOf(orderPay.getTotal_money().multiply(new BigDecimal(100)).intValue()), null,
                    goods_desc, ServerConfig.getPaynotify2(),user.getOpen_id());
            map.put("code",200);
            map.put("msg","success");
            map.put("data",wxPayRespMap);
            map.put("total_money",orderPay.getTotal_money());
        } catch (Exception e) {
            log.error("wxPaySpread：{}",e);
            map.put("code",500);
            map.put("msg","error");
        }
        return map;
    }



    /**
     * 差价支付接口
     * @param order_id
     * @param money
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Map wxPaySpread(HttpServletRequest request, HttpServletResponse resp,Integer order_id,BigDecimal money,BigDecimal jin_num)
            throws Exception {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
        Map map = new HashMap();
        try {
            String goods_desc = "购买商品付款";
            String order_code = String.valueOf(System.currentTimeMillis() / 1000) + (int) (Math.random() * 9000 + 1000);
            JstOrder jstOrder = jstOrderMapper.selectByPrimaryKey(order_id);
            jstOrder.setSpread_price(money);
            jstOrder.setSpread_status(1);
            jstOrder.setSpread_no(order_code);
            jstOrder.setSpread_jin_num(jin_num);
            jstOrderMapper.updateByPrimaryKeySelective(jstOrder);
            User user = userMapper.selectByPrimaryKey(jstOrder.getUser_id());
            // 支付逻辑
            Map<String, String> wxPayRespMap = WeixinUtil.configPayParam(request, order_code, String.valueOf(money.multiply(new BigDecimal(100)).intValue()), null,
                    goods_desc, ServerConfig.getPaynotify2(),user.getOpen_id());
            map.put("code",200);
            map.put("msg","success");
            map.put("data",wxPayRespMap);
            map.put("total_money",money);
        } catch (Exception e) {
            log.error("wxPaySpread：{}",e);
            map.put("code",500);
            map.put("msg","error");
        }
        return map;
    }

    /**
     * 微信支付成功回调处理
     * @param request
     * @param resp
     * @return
     * @throws IOException
     */
    public synchronized JSONObject wxpaysuccess(HttpServletRequest request, HttpServletResponse resp) throws Exception {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
        log.info("==========微信支付成功回调接口开始==========");
        JSONObject json = new JSONObject();
        try{
            List<JstOrder> list = jstOrderMapper.orderAll();
            if(list.size()>0) {
                for (JstOrder order : list) {
                    Map<String, String> wxPayRespMap = WeixinUtil.wxpayquery(request, order.getPay_no());
                    if (String.valueOf(wxPayRespMap.get("trade_state")).equals("SUCCESS")) {
                        if(order.getPay_status()!=2) {
                            //1.修改订单状态
                            if (order.getType() == 1) {
                                DelivMerch delivMerch = delivMerchMapper.selectByMerId(order.getMerchant_id());
                                JstOrder order1 = new JstOrder();
                                if (delivMerch != null) {
                                    order1.setDelivery_id(delivMerch.getDelivery_id());
                                }
                                order1.setId(order.getId());
                                order1.setPay_status(2);
                                log.info("支付时间:" + new Date());
                                order1.setPay_time(new Date());
                                order1.setConfirm_receipt(0);

                                // 消费记录
                                SpendRecord sd = new SpendRecord();
                                sd.setUser_id(order.getUser_id());
                                sd.setSpend_amount(order.getPay_price());
                                sd.setDes("订单" + order.getOrder_no() + "完成付款-" + order.getPay_price().setScale(2, BigDecimal.ROUND_HALF_UP));
                                sd.setType(1);
                                sd.setOrder_id(order.getOrder_no());
                                SpendRecord srd = spendRecordMapper.selectByOrderId(order.getOrder_no());
                                if(srd!=null) {
                                    log.info("订单" + order.getOrder_no() + "已添加消费记录,重复的体现");
                                }else {
                                    spendRecordMapper.insertSelective(sd);
                                    log.info("消费记录没有填加,此时可以添加");
                                }

                                jstOrderMapper.updateByPrimaryKeySelective(order1);
                                CommodityInfo ci = commodityInfoMapper.selectByPrimaryKey2(order.getCommodity_id());
                                ci.setStock(ci.getStock() - order.getQuantity());
                                ci.setSold(ci.getSold() + order.getQuantity());
                                commodityInfoMapper.updateByPrimaryKeySelective(ci);
                            } else if (order.getType() == 2) {
                                JstOrder order1 = new JstOrder();
                                order1.setId(order.getId());
                                order1.setPay_status(2);
                                log.info("支付时间:" + new Date());
                                order1.setPay_time(new Date());

                                // 消费记录
                                SpendRecord sd = new SpendRecord();
                                sd.setUser_id(order.getUser_id());
                                sd.setSpend_amount(order.getPay_price());
                                sd.setDes("订单" + order.getOrder_no() + "充值成功+" + order.getPay_price().setScale(2, BigDecimal.ROUND_HALF_UP));
                                sd.setType(2);
                                sd.setOrder_id(order.getOrder_no());
                                SpendRecord srd = spendRecordMapper.selectByOrderId(order.getOrder_no());
                                if(srd!=null) {
                                    log.info("订单" + order.getOrder_no() + "已添加消费记录,重复的体现");
                                }else {
                                    spendRecordMapper.insertSelective(sd);
                                    log.info("消费记录没有填加,此时可以添加");
                                }

                                jstOrderMapper.updateByPrimaryKeySelective(order1);
                                User user = userMapper.selectByPrimaryKey(order.getUser_id());
                                user.setAmount(user.getAmount().add(order.getPay_price()));
                                userMapper.updateByPrimaryKeySelective(user);
                            } else if (order.getType() == 3) {
                                JstOrder order1 = new JstOrder();
                                order1.setId(order.getId());
                                order1.setPay_status(2);
                                log.info("支付时间:" + new Date());
                                order1.setPay_time(new Date());

                                // 消费记录
                                SpendRecord sd = new SpendRecord();
                                sd.setUser_id(order.getUser_id());
                                sd.setSpend_amount(order.getPay_price());
                                sd.setDes("订单" + order.getOrder_no() + "商家入驻缴费成功-" + order.getPay_price().setScale(2, BigDecimal.ROUND_HALF_UP));
                                sd.setType(1);
                                sd.setOrder_id(order.getOrder_no());
                                SpendRecord srd = spendRecordMapper.selectByOrderId(order.getOrder_no());
                                if(srd!=null) {
                                    log.info("订单" + order.getOrder_no() + "已添加消费记录,重复的体现");
                                }else {
                                    spendRecordMapper.insertSelective(sd);
                                    log.info("消费记录没有填加,此时可以添加");
                                }

                                jstOrderMapper.updateByPrimaryKeySelective(order1);
                                MerchantInfo mi = new MerchantInfo();
                                mi.setId(order.getMerchant_id());
                                mi.setIs_ruzhu(1);
                                mi.setRuzhu_amount(order.getPay_price());
                                merchantInfoMapper.updateByPrimaryKeySelective(mi);
                            } else if (order.getType() == 4) {
                                JstOrder order1 = new JstOrder();
                                order1.setId(order.getId());
                                order1.setPay_status(2);
                                log.info("支付时间:" + new Date());
                                order1.setPay_time(new Date());



                                // 消费记录
                                SpendRecord sd = new SpendRecord();
                                sd.setUser_id(order.getUser_id());
                                sd.setSpend_amount(order.getPay_price());
                                sd.setDes("订单" + order.getOrder_no() + "配送员入驻缴费成功-" + order.getPay_price().setScale(2, BigDecimal.ROUND_HALF_UP));
                                sd.setType(1);
                                sd.setOrder_id(order.getOrder_no());
                                SpendRecord srd = spendRecordMapper.selectByOrderId(order.getOrder_no());
                                if(srd!=null) {
                                    log.info("订单" + order.getOrder_no() + "已添加消费记录,重复的体现");
                                }else {
                                    spendRecordMapper.insertSelective(sd);
                                    log.info("消费记录没有填加,此时可以添加");
                                }

                                jstOrderMapper.updateByPrimaryKeySelective(order1);

                                Delivery record = new Delivery();
                                record.setIs_ruzhu(1);
                                record.setRuzhu_amount(order.getPay_price());
                                record.setId(deliveryMapper.selectByUserId(order.getUser_id()).getId());
                                deliveryMapper.updateByPrimaryKeySelective(record);
                            }
                        }
                    }
                }
            }
            // 成功回调逻辑
            json.put("code", "100");
            json.put("info", "回调成功");
        }catch(Exception e){
            json.put("code", "500");
            json.put("info", "回调失败");
            e.printStackTrace();
        }
        log.info("==========微信支付成功回调结束=========="+json);
        return json;
    }

    /**
     * 微信支付成功差价回调处理
     * @param request
     * @param resp
     * @return
     * @throws IOException
     */
    public synchronized JSONObject wxpaysuccess2(HttpServletRequest request, HttpServletResponse resp) throws Exception {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
        log.info("==========微信差价支付成功回调接口开始==========");
        JSONObject json = new JSONObject();
        try{
            List<JstOrder> list = jstOrderMapper.orderAll2();
            if(list.size()>0) {
                for (JstOrder order : list) {
                    Map<String, String> wxPayRespMap = WeixinUtil.wxpayquery(request, order.getSpread_no());
                    if (String.valueOf(wxPayRespMap.get("trade_state")).equals("SUCCESS")) {
                        if (order.getSpread_status() != 2) {
                            //1.修改差价支付状态
                            order.setSpread_status(2);
                            // 修改总价 支付金额加差价就是总价 在支付第一次的时候总价记录了第一次支付金额
                            order.setTotal_price(order.getTotal_price().add(order.getSpread_price()).setScale(2, BigDecimal.ROUND_HALF_UP));
                            //order.setJin_num(order.getJin_num().add(order.getSpread_jin_num()));

                            // 消费记录
                            SpendRecord sd = new SpendRecord();
                            sd.setUser_id(order.getUser_id());
                            sd.setSpend_amount(order.getSpread_price());
                            sd.setDes("订单" + order.getOrder_no() + "完成差价付款-" + order.getSpread_price().setScale(2, BigDecimal.ROUND_HALF_UP));
                            sd.setType(1);
                            sd.setOrder_id(order.getOrder_no());
                            SpendRecord srd = spendRecordMapper.selectByOrderId(order.getOrder_no());
                            if(srd!=null) {
                                log.info("订单" + order.getOrder_no() + "已添加消费记录,重复的体现");
                            }else {
                                spendRecordMapper.insertSelective(sd);
                                log.info("消费记录没有填加,此时可以添加");
                            }
                            jstOrderMapper.updateByPrimaryKeySelective(order);
                        }
                    }
                }
            }
            // 成功回调逻辑
            json.put("code", "100");
            json.put("info", "回调成功");
        }catch(Exception e){
            json.put("code", "500");
            json.put("info", "回调失败");
            e.printStackTrace();
        }
        log.info("==========微信差价支付成功回调接口结束=========="+json);
        return json;
    }
}
