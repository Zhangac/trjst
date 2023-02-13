package com.trjst.service.admin;

import com.alibaba.fastjson.JSONObject;
import com.trjst.mapper.BannerMapper;
import com.trjst.mapper.UserMapper;
import com.trjst.model.Banner;
import com.trjst.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 返回含banner列表的json
     * @param start,length_number,draw,type
     * @return
     * */
    public String getResultJson(Integer start, Integer length_number, Integer draw,Integer type) {
        List resultList = userMapper.getResultList(start, length_number,type);
        Integer countnumber = userMapper.getListCount(type);
        JSONObject jobj = new JSONObject();
        jobj.put("draw", draw);
        jobj.put("recordsFiltered", countnumber);
        jobj.put("recordsTotal", countnumber);
        jobj.put("data", resultList);
        return jobj.toString();
    }

    //业务员
    public String getResultYwyJson(Integer start, Integer length_number, Integer draw,Integer is_mech,Integer check_status) {
        List resultList = userMapper.getResultListYwy(start, length_number,is_mech,check_status);
        JSONObject jobj = new JSONObject();
        jobj.put("draw", draw);
        jobj.put("recordsFiltered", resultList.size());
        jobj.put("recordsTotal", resultList.size());
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
            userMapper.deleteByPrimaryKey(id);
            map.put("code", "100");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;
    }

    public Map updateYwyUser(Integer id) {
        Map map = new HashMap();
        try {
            User u = new User();
            u.setId(id);
            u.setCheck_status(1);
            userMapper.updateByPrimaryKeySelective(u);
            map.put("code", "100");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;
    }
}
