package com.trjst.service.api;

import com.trjst.dto.DeliveryMerGroupDto;
import com.trjst.mapper.*;
import com.trjst.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private JstOrderMapper jstOrderMapper;

    @Autowired
    private CommodityInfoMapper commodityInfoMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private BrokerageMapper brokerageMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MerchantInfoMapper merchantInfoMapper;

    @Autowired
    private DeliveryMapper deliveryMapper;

    @Autowired
    private SpendRecordMapper spendRecordMapper;

    @Autowired
    private AssortMapper assortMapper;

    public List<JstOrder> orderList(JstOrder record){
        return jstOrderMapper.orderList(record);
    }

    @Transactional(rollbackFor = Exception.class)
    public Map addOrderList(List<JstOrder> record) throws Exception{
        Map map = new HashMap();
        List list = new ArrayList();
        try {
            for (JstOrder jstOrder :record){
                CommodityInfo ci = commodityInfoMapper.selectByPrimaryKey(jstOrder.getCommodity_id());
                if(jstOrder.getUser_id()==null || jstOrder.getUser_id()==0){
                    map.put("code",400);
                    map.put("msg","user_id不能为空");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    log.info("事务已经回滚");
                    return map;
                }

                if(ci==null){
                    map.put("code",400);
                    map.put("msg","商品编号"+jstOrder.getCommodity_id()+"的商品已下架");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    log.info("事务已经回滚");
                    return map;
                }
                if(ci.getStock()==0){
                    map.put("code",400);
                    map.put("msg","商品编号"+jstOrder.getCommodity_id()+"的商品库存不足,请联系商家进行完善");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    log.info("事务已经回滚");
                    return map;
                }
                if(jstOrder.getQuantity() > ci.getStock()){
                    map.put("code",400);
                    map.put("msg","商品编号"+jstOrder.getCommodity_id()+"的商品数量不足,请联系商家进行完善");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    log.info("事务已经回滚");
                    return map;
                }
                if(ci.getAssort_id()==0 || ci.getAssort_id()==null){
                    map.put("code",400);
                    map.put("msg","商品编号"+jstOrder.getCommodity_id()+"的商品没有配置分类,请联系商家进行完善");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    log.info("事务已经回滚");
                    return map;
                }
//                if(ci.getArea_id()==0 || ci.getArea_id()==null){
//                    map.put("code",400);
//                    map.put("msg",jstOrder.getCommodity_id()+"商品没有配置区市,请联系商家进行完善");
//                    return map;
//                }
                if(ci.getMerchant_id()==0 || ci.getMerchant_id()==null){
                    map.put("code",400);
                    map.put("msg","商品编号"+jstOrder.getCommodity_id()+"的商品没有配置商户,请联系商家进行完善");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    log.info("事务已经回滚");
                    return map;
                }
                if (ci.getMerchant_id()!=0){
                    MerchantInfo mi = merchantInfoMapper.selectByPrimaryKey(ci.getMerchant_id());
                    if(mi.getDelivery_id()==0 || mi.getDelivery_id()==null){
                        map.put("code",400);
                        map.put("msg","商品编号"+jstOrder.getCommodity_id()+"的商户没有配置配送员,请联系商家进行完善");
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        log.info("事务已经回滚");
                        return map;
                    }
                    if(mi.getStatus()==0 || mi.getStatus()==null){
                        map.put("code",400);
                        map.put("msg","商品编号"+jstOrder.getCommodity_id()+"的商户已下架,请联系商家进行完善");
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        log.info("事务已经回滚");
                        return map;
                    }
                    if(mi.getAudit_status()==0 || mi.getAudit_status()==null){
                        map.put("code",400);
                        map.put("msg","商品编号"+jstOrder.getCommodity_id()+"的商户还未通过审核,请联系商家进行完善");
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        log.info("事务已经回滚");
                        return map;
                    }
                }
                JstOrder jstOrder2 = new JstOrder();
                jstOrder2.setUser_id(jstOrder.getUser_id());
                jstOrder2.setCommodity_id(jstOrder.getCommodity_id());
                jstOrder2.setPay_status(1);
                List<JstOrder> jo = jstOrderMapper.orderList(jstOrder2);
                if(jo.size() > 0){
                    map.put("code",400);
                    map.put("msg","商品编号"+jstOrder.getCommodity_id()+"的订单已存在请勿重复下单");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    log.info("事务已经回滚");
                    return map;
                }

                JstOrder jstOrder3 = new JstOrder();
                jstOrder3.setUser_id(jstOrder.getUser_id());
                jstOrder3.setSpread_status(1);
                List<JstOrder> j = jstOrderMapper.orderList(jstOrder3);
                if(j.size() > 0){
                    map.put("code",400);
                    map.put("msg","您有存在未支付的差价订单,请去支付完成后再进行下单");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    log.info("事务已经回滚");
                    return map;
                }

                jstOrder.setOrder_no(String.valueOf(System.currentTimeMillis() / 1000) + (int) (Math.random() * 9000 + 1000));
                int num = jstOrderMapper.insertSelective(jstOrder);
                if(num > 0){
                    ShoppingCart shoppingCart = new ShoppingCart();
                    shoppingCart.setUser_id(jstOrder.getUser_id());
                    shoppingCart.setCommodity_id(jstOrder.getCommodity_id());
                    shoppingCart.setCs_type(1);
                    ShoppingCart shop = shoppingCartMapper.selectByUserAndCommId(shoppingCart);
                    if(shop!=null && shop.getCs_type()==1){
                        shoppingCartMapper.deleteByPrimaryKey(shop.getId());
                    }
                }
                Map map1 = new HashMap();
                map1.put("id",jstOrder.getId());
                JstOrder jstOrder1 = jstOrderMapper.selectByPrimaryKey(jstOrder.getId());
                MerchantInfo mi1 = merchantInfoMapper.selectByPrimaryKey(jstOrder1.getMerchant_id());
                User user = userMapper.selectByPrimaryKey(mi1.getUser_id());
                if(jstOrder1.getDelivery_id()!=null && jstOrder1.getDelivery_id()!=0) {
                    Delivery delivery = deliveryMapper.selectByPrimaryKey(jstOrder1.getDelivery_id());
                    User user1 = userMapper.selectByPrimaryKey(delivery.getUser_id());
                    map1.put("delivery_openid",user1.getOpen_id());
                }
                map1.put("merchant_openid",user.getOpen_id());
                list.add(map1);
            }
            map.put("code",200);
            map.put("msg","success");
            map.put("data",list);
            return map;
        }catch (Exception e){
            map.put("code",500);
            map.put("msg","error");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.info("事务已经回滚");
            log.error("order_error{}",e);
            return map;
        }
    }

    public Map fahuo(Integer id){
        Map map = new HashMap();
        try {
            JstOrder jo = jstOrderMapper.selectByPrimaryKey(id);
            jo.setConfirm_receipt(1);
            jo.setPay_status(7);
            jo.setFahuo_time(new Date());
            jstOrderMapper.updateByPrimaryKeySelective(jo);
            map.put("code",200);
            map.put("msg","success");
            map.put("data",id);
        }catch (Exception e){
            log.error("fahuo:{}"+e);
            map.put("code",500);
            map.put("msg","error");
        }
        return map;
    }

    public Map arrived(Integer id){
        Map map = new HashMap();
        try {
            JstOrder jo = jstOrderMapper.selectByPrimaryKey(id);
            jo.setConfirm_receipt(2);
            jo.setArrived_time(new Date());
            int num = jstOrderMapper.updateByPrimaryKeySelective(jo);
            if(num > 0 ){
                confirmReceipt(id);
            }
            map.put("code",200);
            map.put("msg","success");
            map.put("data",id);
        }catch (Exception e){
            log.error("arrived:{}"+e);
            map.put("code",500);
            map.put("msg","error");
        }
        return map;
    }

    public int updateOrder(JstOrder record){
        return jstOrderMapper.updateByPrimaryKeySelective(record);
    }

    public int delOrder(Integer id){
        return jstOrderMapper.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public Map confirmReceipt(Integer id) throws Exception{
        Map map = new HashMap();
        try {
            JstOrder jstOrder = jstOrderMapper.selectByPrimaryKey(id);
            log.info("确认收货时间:"+new Date());
            jstOrder.setConfirm_time(new Date());
            jstOrder.setConfirm_receipt(3);
            jstOrder.setPay_status(8);
            // 佣金/配送费=(斤数+差价斤数)*抽成
            CommodityInfo ci = commodityInfoMapper.selectByPrimaryKey2(jstOrder.getCommodity_id());
            Assort assort = assortMapper.selectByPrimaryKey(ci.getAssort_id());
            BigDecimal commission = new BigDecimal(0);
            BigDecimal b2 = new BigDecimal("100");
            if(assort.getPercent()==1){
                commission = (jstOrder.getJin_num().add(jstOrder.getSpread_jin_num())).multiply(assort.getYongjin()).setScale(2,BigDecimal.ROUND_HALF_UP);
            }else {
                commission = (jstOrder.getJin_num().add(jstOrder.getSpread_jin_num())).multiply(assort.getYongjin()).divide(b2).setScale(2,BigDecimal.ROUND_HALF_UP);
            }

            jstOrder.setCommission(commission);
            log.info("佣金commission:"+commission);
            int num = jstOrderMapper.updateByPrimaryKeySelective(jstOrder);
            if(num > 0){
                MerchantInfo mi = merchantInfoMapper.selectByPrimaryKey(jstOrder.getMerchant_id());
                User user = userMapper.selectByPrimaryKey(mi.getUser_id());
                // 总价减掉佣金就是商户实际到手的
                BigDecimal amount = jstOrder.getTotal_price().subtract(commission).setScale(2,BigDecimal.ROUND_HALF_UP);
                BigDecimal userAmount = user.getAmount().add(amount).setScale(2,BigDecimal.ROUND_HALF_UP);
                user.setAmount(userAmount);
                userMapper.updateByPrimaryKeySelective(user);
                //消费记录
                SpendRecord sd = new SpendRecord();
                sd.setUser_id(mi.getUser_id());
                sd.setSpend_amount(amount);
                sd.setDes("您的商品订单"+jstOrder.getOrder_no()+"已完成,订单总价:"
                        +jstOrder.getTotal_price()+",本单佣金:"+commission+",实际到账:+"+amount);
                sd.setType(2);
                spendRecordMapper.insertSelective(sd);

                // 配送员
//                if(jstOrder.getDelivery_id()!=null && jstOrder.getDelivery_id()!=0) {
//                    Brokerage brokerage2 = brokerageMapper.selectByType(2);
//                    Delivery deliveryUid = deliveryMapper.selectByPrimaryKey(jstOrder.getDelivery_id());
//                    if (deliveryUid != null) {
//                        User user2 = userMapper.selectByPrimaryKey(deliveryUid.getUser_id());
//                        user2.setAmount(user2.getAmount().add(brokerage2.getBrokerage_amount()));
//                        userMapper.updateByPrimaryKeySelective(user2);
//                        //消费记录
//                        SpendRecord sd2 = new SpendRecord();
//                        sd2.setUser_id(deliveryUid.getUser_id());
//                        sd2.setSpend_amount(brokerage2.getBrokerage_amount());
//                        sd2.setDes("订单" + jstOrder.getOrder_no() + "已完成配送+" + brokerage2.getBrokerage_amount().setScale(2, BigDecimal.ROUND_HALF_UP));
//                        sd2.setType(2);
//                        spendRecordMapper.insertSelective(sd2);
//                    }
//                }
            }
            map.put("code",200);
            map.put("msg","success");
            log.info("confirmReceipt:收货成功");
        }catch (Exception e){
            log.error("confirmReceipt_error:{}"+e);
            map.put("code",500);
            map.put("msg","error");
        }
        return map;
    }

    //商户周月年统计
    public Map getMerchantCount(Integer merchantId) {
        Map result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        for(int i=1;i<=7;i++){
            data.put(String.valueOf(i),0);
        }
        int weekday = getWeekday();
        List<JstOrder> sr = new ArrayList<>();
        for(int i=1;i<=weekday;i++){
            List<JstOrder> s = jstOrderMapper.zhou(weekday-i,merchantId);
            data.put(String.valueOf(i),s);
            sr.addAll(s);
        }
        BigDecimal amount = new BigDecimal(0);
        for (JstOrder as :sr){
            amount=amount.add(as.getTotal_price());
        }

        List<JstOrder> yue = jstOrderMapper.yue(merchantId);
        BigDecimal yueAmount = new BigDecimal(0);
        if(yue.size()!=0){
            for (JstOrder as :yue){
                yueAmount=yueAmount.add(as.getTotal_price());
            }
        }

        List<JstOrder> nian = jstOrderMapper.nian(merchantId);
        BigDecimal nianAmount = new BigDecimal(0);
        if(nian.size()!=0){
            for (JstOrder as :nian){
                nianAmount=nianAmount.add(as.getTotal_price());
            }
        }

        result.put("code",200);
        result.put("monad",sr.size());
        result.put("amount",amount);
        result.put("yueMonad",yue.size());
        result.put("yueAmount",yueAmount);
        result.put("nianMonad",nian.size());
        result.put("nianAmount",nianAmount);
        return result;
    }

    public int getWeekday(){
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        int weekday=c.get(Calendar.DAY_OF_WEEK);
        if(weekday==1)
            return 7;
        else return weekday-1;
    }

    public List<DeliveryMerGroupDto> deliveryMerGroup(Integer deliveryId,Integer type){
        return jstOrderMapper.deliveryMerGroup(deliveryId,type);
    }
}
