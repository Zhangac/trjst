package com.trjst.service.api;

import com.trjst.mapper.DeliveryMapper;
import com.trjst.mapper.UserMapper;
import com.trjst.model.Delivery;
import com.trjst.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryMapper deliveryMapper;


    public Delivery selectByUserId(Integer userId){
        return deliveryMapper.selectByUserId(userId);
    }

    public Map addDelivery(Delivery record){
        Map map = new HashMap();
        if(deliveryMapper.selectByUserId(record.getUser_id())!=null){
            map.put("code",400);
            map.put("msg","您已经申请过了，请勿重复申请");
            return map;
        }
        int num =  deliveryMapper.insertSelective(record);
        if(num > 0){
            map.put("code",200);
            map.put("msg","success");
            return map;
        }else {
            map.put("code",500);
            map.put("msg","error");
            return map;
        }
    }

    public int updateDelivery(Delivery record){
        return deliveryMapper.updateByPrimaryKeySelective(record);
    }

}
