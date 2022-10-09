package com.trjst.service.admin;

import com.alibaba.fastjson.JSONObject;
import com.trjst.mapper.AreaMapper;
import com.trjst.model.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminAreaService {

    @Autowired
    private AreaMapper areaMapper;

    /**
     * 返回含列表的json
     * @param start,length_number,draw,type
     * @return
     * */
    public String getResultJson(Integer start, Integer length_number, Integer draw) {
        List resultList = areaMapper.getResultList(start, length_number);
        Integer countnumber = areaMapper.getListCount();
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
    public Area findPojoById(Integer id) {
        if(id!=null) {
            return areaMapper.selectByPrimaryKey(id);
        }
        return new Area();
    }

    /**
     *编辑banner的url
     * @param id,banner_url
     * @return
     * */
    public Map edit(Integer id, String name,Integer sort, String pic) {
        Map map = new HashMap<String, String>();
        try {
            if (id == null) {
                Area area = new Area();
                area.setName(name);
                area.setSort(sort);
                area.setStatus(1);
                area.setHead(pic);
                areaMapper.insertSelective(area);
                map.put("code", "100");
            } else {
                Area area = areaMapper.selectAll(id);
                area.setId(id);
                area.setName(name);
                area.setSort(sort);
                if(!pic.equals(null) && !pic.equals(""))
                {
                    area.setHead(pic);
                }
                areaMapper.updateByPrimaryKeySelective(area);
                map.put("code", "100");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;

    }
    /**
     *设置启用状态
     * @param id,status
     * @return
     * */
    public Map setStatus(Integer status, Integer id) {
        Map map = new HashMap<String, String>();
        try {
            Area area = areaMapper.selectAll(id);
            area.setStatus(status);
            areaMapper.updateByPrimaryKeySelective(area);
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
            areaMapper.deleteByPrimaryKey(id);
            map.put("code", "100");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;
    }
}
