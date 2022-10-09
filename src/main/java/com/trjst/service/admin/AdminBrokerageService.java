package com.trjst.service.admin;

import com.alibaba.fastjson.JSONObject;
import com.trjst.mapper.BrokerageMapper;
import com.trjst.model.Brokerage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminBrokerageService {

    @Autowired
    private BrokerageMapper brokerageMapper;

    /**
     * 返回含banner列表的json
     * */
    public String getResultJson(Integer start, Integer length_number, Integer draw) {
        List resultList = brokerageMapper.getResultList(start, length_number);
        Integer countnumber = brokerageMapper.getListCount();
        JSONObject jobj = new JSONObject();
        jobj.put("draw", draw);
        jobj.put("recordsFiltered", countnumber);
        jobj.put("recordsTotal", countnumber);
        jobj.put("data", resultList);
        return jobj.toString();
    }

    /**
     * 通过id获取对象
     * */
    public Brokerage findPojoById(Integer id) {
        if(id!=null) {
            return brokerageMapper.selectByPrimaryKey(id);
        }
        return new Brokerage();
    }

    /**
     *编辑banner的url
     * */
    public Map edit(Integer id, BigDecimal brokerage_amount,Integer type) {
        Map map = new HashMap<String, String>();
        try {
            if(id ==null){
                Brokerage bk = new Brokerage();
                bk.setBrokerage_amount(brokerage_amount);
                bk.setType(type);
                brokerageMapper.insertSelective(bk);
                map.put("code", "100");
            }else {
                Brokerage bk = brokerageMapper.selectByPrimaryKey(id);
                bk.setId(id);
                bk.setBrokerage_amount(brokerage_amount);
                bk.setType(type);
                brokerageMapper.updateByPrimaryKeySelective(bk);
                map.put("code", "100");
            }

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
            brokerageMapper.deleteByPrimaryKey(id);
            map.put("code", "100");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;
    }
}
