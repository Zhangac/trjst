package com.trjst.service.admin;

import com.alibaba.fastjson.JSONObject;
import com.trjst.mapper.AssortMapper;
import com.trjst.mapper.CommodityInfoMapper;
import com.trjst.model.Assort;
import com.trjst.model.CommodityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminAssortService {

    @Autowired
    private AssortMapper assortMapper;

    @Autowired
    private CommodityInfoMapper commodityInfoMapper;

    /**
     * 返回含列表的json
     * @param start,length_number,draw,type
     * @return
     * */
    public String getResultJson(Integer start, Integer length_number, Integer draw) {
        List resultList = assortMapper.getResultList(start, length_number);
        Integer countnumber = assortMapper.getListCount();
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
    public Assort findPojoById(Integer id) {
        if(id!=null) {
            return assortMapper.selectByPrimaryKey(id);
        }
        return new Assort();
    }

    /**
     *编辑banner的url
     * @param id,banner_url
     * @return
     * */
    public Map edit(Integer id, String name, Integer sort, BigDecimal yongjin, String yuliu) {
        Map map = new HashMap<String, String>();
        try {
            if (id == null) {
                Assort area = new Assort();
                area.setName(name);
                area.setSort(sort);
                area.setYongjin(yongjin);
                area.setYuliu(yuliu);
                assortMapper.insertSelective(area);
                map.put("code", "100");
            } else {
                Assort area = assortMapper.selectByPrimaryKey(id);
                area.setId(id);
                area.setName(name);
                area.setSort(sort);
                area.setYongjin(yongjin);
                area.setYuliu(yuliu);
                assortMapper.updateByPrimaryKeySelective(area);
                map.put("code", "100");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;

    }

    /**
     *删除分类
     * @param id
     * @return
     * */
    public Map daletePojo(Integer id) {
        Map map = new HashMap<String, String>();
        try {
            CommodityInfo record = new CommodityInfo();
            record.setAssort_id(id);
            List<CommodityInfo> list = commodityInfoMapper.commodityList2(record);
            if(list.size() > 0) {
                for (CommodityInfo ci : list) {
                    ci.setStatus(0);
                    ci.setAssort_id(0);
                    commodityInfoMapper.updateByPrimaryKeySelective(ci);
                }
            }
            assortMapper.deleteByPrimaryKey(id);
            map.put("code", "100");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;
    }
}
