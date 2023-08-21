package com.trjst.service.admin;

import com.alibaba.fastjson.JSONObject;
import com.trjst.mapper.DelivMerchMapper;
import com.trjst.mapper.DeliveryMapper;
import com.trjst.mapper.MerchantInfoMapper;
import com.trjst.mapper.UserMapper;
import com.trjst.model.DelivMerch;
import com.trjst.model.Delivery;
import com.trjst.model.MerchantInfo;
import com.trjst.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminDeliveryService {

    @Autowired
    private DeliveryMapper deliveryMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DelivMerchMapper delivMerchMapper;

    @Autowired
    private MerchantInfoMapper merchantInfoMapper;

    /**
     * 返回含banner列表的json
     * */
    public String getResultJson(Integer start, Integer length_number, Integer draw,
                                String name, Integer status) {
        List resultList = deliveryMapper.getResultList(start, length_number,name,status);
        Integer countnumber = deliveryMapper.getListCount(name,status);
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
    public Delivery findPojoById(Integer id) {
        if(id!=null) {
            return deliveryMapper.selectByPrimaryKey(id);
        }
        return new Delivery();
    }

    /**
     * 审核
     * @param id
     * @param status
     * @return
     */
    public Map shenhe(Integer id, Integer status) {
        Map map = new HashMap<String, String>();
        try {
            Delivery bk = deliveryMapper.selectByPrimaryKey(id);
            bk.setId(id);
            bk.setStatus(status);
            deliveryMapper.updateByPrimaryKeySelective(bk);
            User user = userMapper.selectByPrimaryKey(bk.getUser_id());
            if(3==user.getIs_mech()){
                user.setIs_mech(4);
            }else {
                user.setIs_mech(2);
            }
//            user.setIs_mech(2);
            userMapper.updateByPrimaryKeySelective(user);
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
            //修改用户is_mech
            Delivery bk = deliveryMapper.selectByPrimaryKey(id);
            User user = userMapper.selectByPrimaryKey(bk.getUser_id());
            user.setIs_mech(0);
            userMapper.updateByPrimaryKeySelective(user);
            //删除商户匹配配送员
            List<DelivMerch> dm = delivMerchMapper.selectByDeliId(id);
            if(dm.size() > 0){
                for (DelivMerch d :dm){
                    delivMerchMapper.deleteByPrimaryKey(d.getId());
                }
            }
            //修改商户配送员为0
            MerchantInfo mi = new MerchantInfo();
            mi.setDelivery_id(id);
            List<MerchantInfo> miList = merchantInfoMapper.merchantInfoByName(mi);
            if(miList.size()>0){
                for (MerchantInfo merchantInfo :miList){
                    merchantInfo.setDelivery_id(0);
                    merchantInfoMapper.updateByPrimaryKeySelective(merchantInfo);
                }
            }
            //删除配送员
            deliveryMapper.deleteByPrimaryKey(id);
            map.put("code", "100");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;
    }
}
