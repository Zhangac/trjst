package com.trjst.service.admin;

import com.alibaba.fastjson.JSONObject;
import com.trjst.mapper.*;
import com.trjst.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AdminMerchantInfoService {

    @Autowired
    private MerchantInfoMapper merchantInfoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommodityInfoMapper commodityInfoMapper;

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private DelivMerchMapper delivMerchMapper;

    /**
     * 返回含banner列表的json
     * */
    public String getResultJson(Integer start, Integer length_number, Integer draw,String merchant_name,
                                Integer audit_status,Integer status,Integer is_hot) {
        List resultList = merchantInfoMapper.getResultList(start, length_number,merchant_name,audit_status,status,is_hot);
        Integer countnumber = merchantInfoMapper.getListCount(merchant_name,audit_status,status,is_hot);
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
    public MerchantInfo findPojoById(Integer id) {
        if(id!=null) {
            return merchantInfoMapper.selectByPrimaryKey(id);
        }
        return new MerchantInfo();
    }

    public Map edit(Integer id, Integer audit_status) {
        Map map = new HashMap<String, String>();
        try {
            MerchantInfo bk = merchantInfoMapper.selectByPrimaryKey(id);
            bk.setId(id);
            bk.setAudit_status(audit_status);
            merchantInfoMapper.updateByPrimaryKeySelective(bk);
            User user = userMapper.selectByPrimaryKey(bk.getUser_id());
            user.setIs_mech(1);
            userMapper.updateByPrimaryKeySelective(user);
            map.put("code", "100");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;
    }

    public int edit2(MerchantInfo mi) {
        return merchantInfoMapper.updateByPrimaryKeySelective(mi);
    }

    /**
     *上下架
     * */
    public Map xiajia(Integer id, Integer status) {
        Map map = new HashMap<String, String>();
        try {
            if(status == 0){
                MerchantInfo bk = merchantInfoMapper.selectByPrimaryKey(id);
                User user = userMapper.selectByPrimaryKey(bk.getUser_id());
                user.setIs_mech(0);
                userMapper.updateByPrimaryKeySelective(user);
                log.info("用户is_mech修改成功");
                DelivMerch dm = delivMerchMapper.selectByMerId(id);
                if (dm!=null){
                    delivMerchMapper.deleteByPrimaryKey(dm.getId());
                    log.info("配送员匹配商户删除成功");
                }
                CommodityInfo record = new CommodityInfo();
                record.setMerchant_id(id);
                List<CommodityInfo> list = commodityInfoMapper.commodityList2(record);
                if(list.size() > 0) {
                    for (CommodityInfo ci : list) {
                        ci.setStatus(0);
                        commodityInfoMapper.updateByPrimaryKeySelective(ci);
                    }
                }
                bk.setId(id);
                bk.setStatus(status);
                merchantInfoMapper.updateByPrimaryKeySelective(bk);
                map.put("code", "100");
            }else {
                MerchantInfo bk = merchantInfoMapper.selectByPrimaryKey(id);
                User user = userMapper.selectByPrimaryKey(bk.getUser_id());
                user.setIs_mech(1);
                userMapper.updateByPrimaryKeySelective(user);
                log.info("用户is_mech修改成功");
                CommodityInfo record = new CommodityInfo();
                record.setMerchant_id(id);
                List<CommodityInfo> list = commodityInfoMapper.commodityList2(record);
                if(list.size() > 0) {
                    for (CommodityInfo ci : list) {
                        ci.setStatus(1);
                        commodityInfoMapper.updateByPrimaryKeySelective(ci);
                    }
                }
                bk.setId(id);
                bk.setStatus(status);
                merchantInfoMapper.updateByPrimaryKeySelective(bk);
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
            MerchantInfo bk = merchantInfoMapper.selectByPrimaryKey(id);
            bk.setId(id);
            bk.setIs_hot(is_hot);
            merchantInfoMapper.updateByPrimaryKeySelective(bk);
            map.put("code", "100");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;
    }

    /**
     *是否审核0不是 1是  是否不交入住费用就可以上传商品 可以显示但不能进行交易
     * */
    public Map isshow(Integer id, Integer is_show) {
        Map map = new HashMap<String, String>();
        try {
            MerchantInfo bk = merchantInfoMapper.selectByPrimaryKey(id);
            bk.setId(id);
            bk.setIs_show(is_show);
            merchantInfoMapper.updateByPrimaryKeySelective(bk);
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
            MerchantInfo bk = merchantInfoMapper.selectByPrimaryKey(id);
            User user = userMapper.selectByPrimaryKey(bk.getUser_id());
            user.setIs_mech(0);
            userMapper.updateByPrimaryKeySelective(user);
            log.info("用户is_mech修改成功");
            DelivMerch dm = delivMerchMapper.selectByMerId(id);
            if (dm!=null){
                delivMerchMapper.deleteByPrimaryKey(dm.getId());
                log.info("配送员匹配商户删除成功");
            }
            CommodityInfo record = new CommodityInfo();
            record.setMerchant_id(id);
            List<CommodityInfo> list = commodityInfoMapper.commodityList2(record);
            if(list.size() > 0) {
                for (CommodityInfo ci : list) {
                    Collect c = new Collect();
                    c.setCommodity_id(ci.getId());
                    List<Collect> cList = collectMapper.collectListByUserId(c);
                    if(list.size() > 0) {
                        for (Collect cl : cList) {
                            collectMapper.deleteByPrimaryKey(cl.getId());
                        }
                    }
                    commodityInfoMapper.deleteByPrimaryKey(ci.getId());
                }
            }
            log.info("商品和收藏删除成功");
            merchantInfoMapper.deleteByPrimaryKey(id);
            log.info("商户删除成功");
            map.put("code", "100");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;
    }
}
