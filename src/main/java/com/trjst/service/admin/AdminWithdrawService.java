package com.trjst.service.admin;

import com.alibaba.fastjson.JSONObject;
import com.trjst.mapper.SpendRecordMapper;
import com.trjst.mapper.UserMapper;
import com.trjst.mapper.WithdrawMapper;
import com.trjst.model.SpendRecord;
import com.trjst.model.User;
import com.trjst.model.Withdraw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminWithdrawService {

    @Autowired
    private WithdrawMapper withdrawMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SpendRecordMapper spendRecordMapper;

    /**
     * 返回含列表的json
     * @param start,length_number,draw,type
     * @return
     * */
    public String getResultJson(Integer start, Integer length_number, Integer draw) {
        List resultList = withdrawMapper.getResultList(start, length_number);
        Integer countnumber = withdrawMapper.getListCount();
        JSONObject jobj = new JSONObject();
        jobj.put("draw", draw);
        jobj.put("recordsFiltered", countnumber);
        jobj.put("recordsTotal", countnumber);
        jobj.put("data", resultList);
       // System.out.println(jobj.toString());
        return jobj.toString();
    }

    /**
     *设置状态
     * @param id,status
     * @return
     * */
    public Map setStatus(Integer status, Integer id) {
        Map map = new HashMap<String, String>();
        try {
            Withdraw market = withdrawMapper.selectAllById(id);
            if(status==3) {
                User user = userMapper.selectByPrimaryKey(market.getUser_id());
                user.setAmount(user.getAmount().add(market.getWithdraw_amount()));
                userMapper.updateByPrimaryKeySelective(user);
                //消费记录
                SpendRecord sd = new SpendRecord();
                sd.setUser_id(market.getUser_id());
                sd.setSpend_amount(market.getWithdraw_amount());
                sd.setDes("提现失败退回+"+market.getWithdraw_amount());
                sd.setType(2);
                spendRecordMapper.insertSelective(sd);
            }
            market.setStatus(status);
            withdrawMapper.updateByPrimaryKeySelective(market);
            map.put("code", "100");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;
    }
    /**
     *删除banner
     * @param id
     * @return
     * */
    public Map daletePojo(Integer id) {
        Map map = new HashMap<String, String>();
        try {
            withdrawMapper.deleteByPrimaryKey(id);
            map.put("code", "100");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;
    }
}
