package com.trjst.service.admin;

import com.alibaba.fastjson.JSONObject;
import com.trjst.mapper.SpeciMapper;
import com.trjst.model.Speci;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminSpeciService {

    @Autowired
    private SpeciMapper speciMapper;

    /**
     * 返回列表的json
     * @param start,length_number,draw,fk_id
     * @return
     * */
    public String getResultJson(Integer start, Integer length_number, Integer draw,Integer commodity_info_id) {
        List resultList = speciMapper.getResultList(start, length_number,commodity_info_id);
        Integer countnumber = speciMapper.getListCount(commodity_info_id);
        JSONObject jobj = new JSONObject();
        jobj.put("draw", draw);
        jobj.put("recordsFiltered", countnumber);
        jobj.put("recordsTotal", countnumber);
        jobj.put("data", resultList);
       // System.out.println(jobj.toString());
        return jobj.toString();
    }

    /**
     * 通过id获取对象
     * @param id
     * @return
     * */
    public Speci findPojoById(Integer id) {
        if(id!=null) {
            return speciMapper.selectByPrimaryKey(id);
        }
        return new Speci();
    }

    public Map daletePojo(Integer id) {
        Map map = new HashMap<String, String>();
        try {
            speciMapper.deleteByPrimaryKey(id);
            map.put("code", "100");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;
    }

    public Map add(Integer commodity_info_id, String speci_name,
                   BigDecimal speci_price,String speci_regu,Integer speci_id) {
        Map map = new HashMap<String, String>();
        try {
            if(speci_id==null){
                Speci s = new Speci();
                s.setCommodity_info_id(commodity_info_id);
                s.setSpeci_name(speci_name);
                s.setSpeci_regu(speci_regu);
                s.setSpeci_price(speci_price);
                speciMapper.insertSelective(s);
            }else {
                Speci s = new Speci();
                s.setSpeci_name(speci_name);
                s.setSpeci_regu(speci_regu);
                s.setSpeci_price(speci_price);
                s.setSpeci_id(speci_id);
                speciMapper.updateByPrimaryKeySelective(s);
            }

            map.put("code", "100");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;

    }
}
