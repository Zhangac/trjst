package com.trjst.service.api;

import com.trjst.mapper.MerchantInfoMapper;
import com.trjst.mapper.UserMapper;
import com.trjst.model.MerchantInfo;
import com.trjst.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MerchantInfoService {

    @Autowired
    private MerchantInfoMapper merchantInfoMapper;

    public List<MerchantInfo> merchantInfoByName(String merchant_name,Integer area_id){
        MerchantInfo mi = new MerchantInfo();
        mi.setMerchant_name(merchant_name);
        mi.setArea_id(area_id);
        return merchantInfoMapper.merchantInfoByName(mi);
    }

    public MerchantInfo merchantInfoById(Integer merchantId){
        return merchantInfoMapper.selectByPrimaryKey(merchantId);
    }

    public MerchantInfo merchantInfoByUserId(Integer userId){
        return merchantInfoMapper.selectByUserId(userId);
    }

    public List<MerchantInfo> selectByMarketId(Integer market_id){
        return merchantInfoMapper.selectByMarketId(market_id);
    }

    public Map addMerchantInfo(MerchantInfo record){
        Map map = new HashMap();
        if(merchantInfoMapper.selectByUserId(record.getUser_id())!=null){
            map.put("code",400);
            map.put("msg","您已经申请过了，请勿重复申请");
            return map;
        }
        int num =  merchantInfoMapper.insertSelective(record);
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

    public int updateMerchantInfo(MerchantInfo record){
        return merchantInfoMapper.updateByPrimaryKeySelective(record);
    }

}
