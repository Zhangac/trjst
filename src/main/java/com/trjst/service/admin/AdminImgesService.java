package com.trjst.service.admin;

import com.alibaba.fastjson.JSONObject;
import com.trjst.mapper.ImgesMapper;
import com.trjst.model.Banner;
import com.trjst.model.Imges;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminImgesService {

    @Autowired
    private ImgesMapper imgesMapper;

    /**
     * 返回列表的json
     * @param start,length_number,draw,fk_id
     * @return
     * */
    public String getResultJson(Integer start, Integer length_number, Integer draw,Integer fk_id,Integer type) {
        List resultList = imgesMapper.getResultList(start, length_number,fk_id,type);
        Integer countnumber = imgesMapper.getListCount(fk_id,type);
        JSONObject jobj = new JSONObject();
        jobj.put("draw", draw);
        jobj.put("recordsFiltered", countnumber);
        jobj.put("recordsTotal", countnumber);
        jobj.put("data", resultList);
       // System.out.println(jobj.toString());
        return jobj.toString();
    }

    public Map daletePojo(Integer id) {
        Map map = new HashMap<String, String>();
        try {
            imgesMapper.deleteByPrimaryKey(id);
            map.put("code", "100");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;
    }

    public Map add(Integer fk_id, String pic,Integer type) {
        Map map = new HashMap<String, String>();
        try {
            Imges imges = new Imges();
            imges.setType(type);
            imges.setFk_id(fk_id);
            imges.setAddress(pic);
            imgesMapper.insertSelective(imges);
            map.put("code", "100");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;

    }
}
