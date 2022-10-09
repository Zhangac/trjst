package com.trjst.service.admin;

import com.alibaba.fastjson.JSONObject;
import com.trjst.mapper.BannerMapper;
import com.trjst.mapper.RechargeMapper;
import com.trjst.mapper.UserMapper;
import com.trjst.model.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminRechargeService {

    @Autowired
    private RechargeMapper rechargeMapper;

    /**
     * 返回含banner列表的json
     * @param start,length_number,draw,type
     * @return
     * */
    public String getResultJson(Integer start, Integer length_number, Integer draw) {
        List resultList = rechargeMapper.getResultList(start, length_number);
        Integer countnumber = rechargeMapper.getListCount();
        JSONObject jobj = new JSONObject();
        jobj.put("draw", draw);
        jobj.put("recordsFiltered", countnumber);
        jobj.put("recordsTotal", countnumber);
        jobj.put("data", resultList);
        return jobj.toString();
    }

    /**
     *删除banner
     * @param id
     * @return
     * */
    public Map daletePojo(Integer id) {
        Map map = new HashMap<String, String>();
        try {
            rechargeMapper.deleteByPrimaryKey(id);
            map.put("code", "100");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;
    }
}
