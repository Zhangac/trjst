package com.trjst.service.admin;

import com.alibaba.fastjson.JSONObject;
import com.trjst.mapper.*;
import com.trjst.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminCommodityInfoService {

    @Autowired
    private CommodityInfoMapper commodityInfoMapper;

    @Autowired
    private AssortMapper assortMapper;

    @Autowired
    private ImgesMapper imgesMapper;

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    /**
     * 返回含banner列表的json
     * */
    public String getResultJson(Integer start, Integer length_number, Integer draw,String commodity_name,
                                Integer audit_status,Integer status,Integer is_hot,Integer area_id) {
        List resultList = commodityInfoMapper.getResultList(start, length_number,commodity_name,audit_status,status,is_hot,area_id);
        Integer countnumber = commodityInfoMapper.getListCount(commodity_name,audit_status,status,is_hot,area_id);
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
    public CommodityInfo findPojoById(Integer id) {
        if(id!=null) {
            return commodityInfoMapper.selectById(id);
        }
        return new CommodityInfo();
    }

    /**
     * 审核
     * */
    public Map edit(Integer id, Integer audit_status) {
        Map map = new HashMap<String, String>();
        try {
            CommodityInfo bk = commodityInfoMapper.selectById(id);
            bk.setId(id);
            bk.setAudit_status(audit_status);
            commodityInfoMapper.updateByPrimaryKeySelective(bk);
            map.put("code", "100");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;
    }

    /**
     *上下架
     * */
    public Map xiajia(Integer id, Integer status) {
        Map map = new HashMap<String, String>();
        try {

            CommodityInfo bk = commodityInfoMapper.selectById(id);
            if(status==1){
                if(bk.getAssort_id()==0){
                    map.put("code", "101");
                }else {
                    bk.setId(id);
                    bk.setStatus(status);
                    commodityInfoMapper.updateByPrimaryKeySelective(bk);
                    map.put("code", "100");
                }
            }else {
                bk.setId(id);
                bk.setStatus(status);
                commodityInfoMapper.updateByPrimaryKeySelective(bk);
                map.put("code", "100");
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;
    }

    /**
     *推荐
     * */
    public Map tuijian(Integer id, Integer is_hot) {
        Map map = new HashMap<String, String>();
        try {
            CommodityInfo bk = commodityInfoMapper.selectById(id);
            bk.setId(id);
            bk.setIs_hot(is_hot);
            commodityInfoMapper.updateByPrimaryKeySelective(bk);
            map.put("code", "100");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;
    }

    public Map daletePojo(Integer id) {
        Map map = new HashMap<String, String>();
        try {
            int num = commodityInfoMapper.deleteByPrimaryKey(id);
            if (num > 0){
                // 删除商品图片
                Imges imges = new Imges();
                imges.setFk_id(id);
                List<Imges> imgesList = imgesMapper.selectByFkList(imges);
                if (imgesList.size() > 0 ){
                    for (Imges i : imgesList){
                        imgesMapper.deleteByPrimaryKey(i.getId());
                    }
                }

                // 删除收藏里的商品
                Collect record = new Collect();
                record.setCommodity_id(id);
                List<Collect> collectList = collectMapper.collectListByUserId(record);
                if (collectList.size() > 0 ){
                    for (Collect i : collectList){
                        collectMapper.deleteByPrimaryKey(i.getId());
                    }
                }

                // 删除购物车里的商品
                List<ShoppingCart> sc = shoppingCartMapper.selectByCommId(id);
                if (sc.size() > 0 ){
                    for (ShoppingCart i : sc){
                        shoppingCartMapper.deleteByPrimaryKey(i.getId());
                    }
                }
            }
            map.put("code", "100");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;
    }

    @Transactional(rollbackFor = Exception.class)
    public Map addOrUpdateCommodityInfo(CommodityInfo record) {
        Map map = new HashMap();
        if(record.getId()!=null){
            Assort assort = assortMapper.selectByPrimaryKey(record.getAssort_id());
            if(assort!=null) {
                record.setSpecifications(assort.getYuliu());
                commodityInfoMapper.updateByPrimaryKeySelective(record);
                map.put("code",100);
                map.put("msg","success");
            }else {
                map.put("code",201);
                map.put("msg","没有此分类，请重新输入分类id");
            }
        }else {
            Assort assort = assortMapper.selectByPrimaryKey(record.getAssort_id());
            if(assort!=null) {
                record.setSpecifications(assort.getYuliu());
                commodityInfoMapper.insertSelective(record);
                map.put("code",100);
                map.put("msg","success");
            }else {
                map.put("code",201);
                map.put("msg","没有此分类，请重新输入分类id");
            }
        }
        return map;
    }
}
