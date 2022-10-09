package com.trjst.service.api;

import com.trjst.mapper.*;
import com.trjst.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class WithdrawService {

    @Autowired
    private WithdrawMapper withdrawMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SpendRecordMapper spendRecordMapper;

    @Autowired
    private JstOrderMapper jstOrderMapper;

    @Autowired
    private MerchantInfoMapper merchantInfoMapper;

    @Autowired
    private WithdrawSaveMapper withdrawSaveMapper;

    public List<Withdraw> withdrawByUserId(Integer userId){
        return withdrawMapper.selectByUserId(userId);
    }

    public WithdrawSave withdrawSaveUserId(Integer userId){
        return withdrawSaveMapper.selectByUserId(userId);
    }

    public Map withdrawSave(WithdrawSave record){
        Map map = new HashMap();

        if(withdrawSaveMapper.selectByUserId(record.getUser_id())!=null){
            map.put("code",400);
            map.put("msg","用户已存在记录不能添加请修改");
            return map;
        }
        int num = withdrawSaveMapper.insertSelective(record);
        if(num > 0){
            map.put("code",200);
            map.put("msg","success");
            return map;
        }else {
            map.put("code",500);
            map.put("msg","error");
            return map;
        }
    }

    public Map withdrawUpdate(WithdrawSave record){
        Map map = new HashMap();

        int num = withdrawSaveMapper.updateByPrimaryKeySelective(record);
        if(num > 0){
            map.put("code",200);
            map.put("msg","success");
            return map;
        }else {
            map.put("code",500);
            map.put("msg","error");
            return map;
        }
    }

    public Map addWithdraw(Withdraw record){
        Map map = new HashMap();
        try {
            // 判断是否第一次提现
            List<Withdraw> wm = withdrawMapper.selectByUserId(record.getUser_id());
            if (wm.size() != 0){
                log.info("不是第一次提现");
                long cf_time = wm.get(0).getCreate_time().getTime();
                long curr_time = new Date().getTime();
                long day = curr_time - cf_time;//判断两个时间距离
                long chaday = day / 86400000;
                if (chaday >= 16) {
                    log.info("2可以提现");
                    // 如果提现金额小于等于用户总金额
                    User user = userMapper.selectByPrimaryKey(record.getUser_id());
                    if (record.getWithdraw_amount().compareTo(user.getAmount()) <= 0){
                        //提现手续费千分之六
                        BigDecimal tx = record.getWithdraw_amount().multiply(new
                                BigDecimal(0.006)).setScale(2, RoundingMode.HALF_UP);
                        record.setGet_amount(record.getWithdraw_amount().subtract(tx));
                        int num = withdrawMapper.insertSelective(record);
                        if(num > 0){
                            user.setAmount(user.getAmount().subtract(record.getWithdraw_amount()));
                            int num2 = userMapper.updateByPrimaryKeySelective(user);
                            if(num2 > 0){
                                //消费记录
                                SpendRecord sd = new SpendRecord();
                                sd.setUser_id(record.getUser_id());
                                sd.setSpend_amount(record.getWithdraw_amount());
                                sd.setDes("申请提现-"+record.getWithdraw_amount());
                                sd.setType(1);
                                spendRecordMapper.insertSelective(sd);
                                map.put("code",200);
                                map.put("msg","申请成功，手续费千分之六，预计到账金额"+record.getWithdraw_amount().subtract(tx));
                                return map;
                            }else {
                                map.put("code",500);
                                map.put("msg","error");
                                return map;
                            }
                        }else {
                            map.put("code",500);
                            map.put("msg","error");
                            return map;
                        }
                    }else {
                        map.put("code",400);
                        map.put("msg","没有足够金额");
                        return map;
                    }
                }else {
                    log.info("2还没到可提现时间段");
                    map.put("code",400);
                    map.put("msg","还没到可提现时间段");
                    return map;
                }
            }else {
                log.info("第一次提现");
                // 1.第一次提现 判断有没有订单
                MerchantInfo mi = merchantInfoMapper.selectByUserId(record.getUser_id());
                if (mi != null) {
                    JstOrder jo = new JstOrder();
                    jo.setMerchant_id(mi.getId());
                    List<JstOrder> orderList = jstOrderMapper.orderByMi(jo);
                    if (orderList.size() != 0) {
                        if(orderList.get(0).getConfirm_time()==null){
                            log.info("您的第一笔订单还未收货，请收货完成后再进行操作");
                            map.put("code",400);
                            map.put("msg","您的第一笔订单还未收货，请收货完成后再进行操作");
                            return map;
                        }
                        long cf_time = orderList.get(0).getConfirm_time().getTime();
                        long curr_time = new Date().getTime();
                        long day = curr_time - cf_time;//判断两个时间距离
                        long chaday = day / 86400000;
                        if (chaday >= 16) {
                            log.info("可以提现");
                            User user = userMapper.selectByPrimaryKey(record.getUser_id());
                            // 如果提现金额小于等于用户总金额
                            if (record.getWithdraw_amount().compareTo(user.getAmount()) <= 0){
                                //提现手续费千分之六
                                BigDecimal tx = record.getWithdraw_amount().multiply(new BigDecimal
                                        (0.006)).setScale(2, RoundingMode.HALF_UP);
                                record.setGet_amount(record.getWithdraw_amount().subtract(tx));
                                int num = withdrawMapper.insertSelective(record);
                                if(num > 0){
                                    user.setAmount(user.getAmount().subtract(record.getWithdraw_amount()));
                                    int num2 = userMapper.updateByPrimaryKeySelective(user);
                                    if(num2 > 0){
                                        //消费记录
                                        SpendRecord sd = new SpendRecord();
                                        sd.setUser_id(record.getUser_id());
                                        sd.setSpend_amount(record.getWithdraw_amount());
                                        sd.setDes("申请提现-"+record.getWithdraw_amount());
                                        sd.setType(1);
                                        spendRecordMapper.insertSelective(sd);
                                        map.put("code",200);
                                        map.put("msg","申请成功，手续费千分之六，预计到账金额"+record.getWithdraw_amount().subtract(tx));
                                        return map;
                                    }else {
                                        map.put("code",500);
                                        map.put("msg","error");
                                        return map;
                                    }
                                }else {
                                    map.put("code",500);
                                    map.put("msg","error");
                                    return map;
                                }
                            }else {
                                map.put("code",400);
                                map.put("msg","没有足够金额");
                                return map;
                            }
                        } else {
                            log.info("还没到可提现时间段");
                            map.put("code",400);
                            map.put("msg","还没到可提现时间段");
                            return map;
                        }
                    } else {
                        log.info("商户没有订单");
                        map.put("code",400);
                        map.put("msg","商户没有订单");
                        return map;
                    }
                } else {
                    log.info("商户号不存在");
                    map.put("code",400);
                    map.put("msg","商户号不存在");
                    return map;
                }
            }
        }catch (Exception e){
            log.error("系统错误.",e);
            map.put("code",500);
            map.put("msg","系统错误");
            return map;
        }
    }

}
