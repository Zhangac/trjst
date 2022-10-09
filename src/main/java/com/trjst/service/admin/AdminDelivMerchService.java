package com.trjst.service.admin;

import com.alibaba.fastjson.JSONObject;
import com.trjst.mapper.AreaMapper;
import com.trjst.mapper.DelivMerchMapper;
import com.trjst.model.Area;
import com.trjst.model.DelivMerch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminDelivMerchService {

    @Autowired
    private DelivMerchMapper delivMerchMapper;

    /**
     * 返回含列表的json
     * @param start,length_number,draw
     * @return
     * */
    public String getResultJson(Integer start, Integer length_number, Integer draw,Integer delivery_id) {
        List resultList = delivMerchMapper.getResultList(start, length_number,delivery_id);
        Integer countnumber = delivMerchMapper.getListCount(delivery_id);
        JSONObject jobj = new JSONObject();
        jobj.put("draw", draw);
        jobj.put("recordsFiltered", countnumber);
        jobj.put("recordsTotal", countnumber);
        jobj.put("data", resultList);
        return jobj.toString();
    }

    /**
     * 通过id获取对象
     * @param id
     * @return
     * */
    public DelivMerch findPojoById(Integer id) {
        if(id!=null) {
            return delivMerchMapper.selectByPrimaryKey(id);
        }
        return new DelivMerch();
    }

    /**
     *编辑banner的url
     * @param id,banner_url
     * @return
     * */
    public Map edit(Integer id, Integer delivery_id,Integer merchant_id,String merchant_name) {
        Map map = new HashMap<String, String>();
        try {
            if (id == null) {
                DelivMerch dm = new DelivMerch();
                dm.setDelivery_id(delivery_id);
                dm.setMerchant_id(merchant_id);
                dm.setMerchant_name(merchant_name);
                delivMerchMapper.insertSelective(dm);
                map.put("code", "100");
            } else {
                DelivMerch dm = delivMerchMapper.selectByPrimaryKey(id);
                dm.setId(id);
                dm.setDelivery_id(delivery_id);
                dm.setMerchant_id(merchant_id);
                dm.setMerchant_name(merchant_name);
                delivMerchMapper.updateByPrimaryKeySelective(dm);
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
            delivMerchMapper.deleteByPrimaryKey(id);
            map.put("code", "100");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;
    }
}
