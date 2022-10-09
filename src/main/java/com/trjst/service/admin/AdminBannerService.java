package com.trjst.service.admin;

import com.alibaba.fastjson.JSONObject;
import com.trjst.mapper.BannerMapper;
import com.trjst.model.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminBannerService {

    @Autowired
    private BannerMapper bannerMapper;

    /**
     * 返回含banner列表的json
     * @param start,length_number,draw,type
     * @return
     * */
    public String getResultJson(Integer start, Integer length_number, Integer draw,Integer type) {
        List resultList = bannerMapper.getResultList(start, length_number,type);
        Integer countnumber = bannerMapper.getListCount(type);
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
    public Banner findPojoById(Integer id) {
        if(id!=null) {
            return bannerMapper.selectByPrimaryKey(id);
        }
        return new Banner();
    }

    /**
     *编辑banner的url
     * @param id,banner_url
     * @return
     * */
    public Map editBannerInfo(Integer id, String address,Integer sort) {
        Map map = new HashMap<String, String>();
        try {
            if (id == null) {
                Banner banner = new Banner();
                banner.setAddress(address);
                banner.setSort(sort);
                banner.setStatus(1);
                bannerMapper.insertSelective(banner);
                map.put("code", "100");
            } else {
                Banner banner = bannerMapper.selectByPrimaryKey(id);
                banner.setId(id);
                if(!address.equals(null) && !address.equals(""))
                {
                    banner.setAddress(address);
                }
                banner.setSort(sort);
                bannerMapper.updateByPrimaryKeySelective(banner);
                map.put("code", "100");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;

    }
    /**
     *设置banner的启用状态
     * @param id,status
     * @return
     * */
    public Map setStatus(Integer status, Integer id) {
        Map map = new HashMap<String, String>();
        try {
            Banner banner = bannerMapper.selectAll(id);
            banner.setStatus(status);
            bannerMapper.updateByPrimaryKeySelective(banner);
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
            bannerMapper.deleteByPrimaryKey(id);
            map.put("code", "100");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;
    }
}
