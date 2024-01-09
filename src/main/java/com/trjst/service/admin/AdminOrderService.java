package com.trjst.service.admin;

import com.alibaba.fastjson.JSONObject;
import com.trjst.mapper.JstOrderMapper;
import com.trjst.model.JstOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminOrderService {

    @Autowired
    private JstOrderMapper jstOrderMapper;


    public List selectListdown(String order_no, Integer pay_status,String delivery_name,String logmax
            ,String logmin,String phone) {
        List resultList = jstOrderMapper.getOrderDown(order_no,pay_status,delivery_name,logmax,logmin,phone);
        return resultList;
    }

    /**
     * 返回含banner列表的json
     * */
    public String getResultJson(Integer start, Integer length_number, Integer draw,String order_no,
                                Integer pay_status,String delivery_name,String logmax
                                ,String logmin,String phone,Integer area_id) {
        List resultList = jstOrderMapper.getResultList(start, length_number,order_no,pay_status,delivery_name,logmax,logmin,phone,area_id);
        Integer countnumber = jstOrderMapper.getListCount(order_no,pay_status,delivery_name,logmax,logmin,phone,area_id);
        JSONObject jobj = new JSONObject();
        jobj.put("draw", draw);
        jobj.put("recordsFiltered", countnumber);
        jobj.put("recordsTotal", countnumber);
        jobj.put("data", resultList);
        return jobj.toString();
    }

    public Map daletePojo(Integer id) {
        Map map = new HashMap<String, String>();
        try {
            jstOrderMapper.deleteByPrimaryKey(id);
            map.put("code", "100");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;
    }

    public Map edit(JstOrder jstOrder) {
        Map map = new HashMap<String, String>();
        try {
            if (jstOrder.getId() == null) {
                jstOrderMapper.insertSelective(jstOrder);
                map.put("code", "100");
            } else {
                JstOrder dm = jstOrderMapper.selectByPrimaryKey(jstOrder.getId());
                jstOrderMapper.updateByPrimaryKeySelective(dm);
                map.put("code", "100");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;

    }

    /**
     * 汇总
     * */
    public String getHzCountList(Integer start, Integer length_number, Integer draw,
                               Integer area_id, String logmax,String logmin) {
        List resultList = jstOrderMapper.getHzResultList(start, length_number,area_id,logmax,logmin);
        Integer countnumber = jstOrderMapper.getHzListCount(area_id,logmax,logmin);
        JSONObject jobj = new JSONObject();
        jobj.put("draw", draw);
        jobj.put("recordsFiltered", countnumber);
        jobj.put("recordsTotal", countnumber);
        jobj.put("data", resultList);
        return jobj.toString();
    }

}
