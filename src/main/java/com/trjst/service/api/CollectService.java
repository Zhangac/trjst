package com.trjst.service.api;

import com.trjst.mapper.CollectMapper;
import com.trjst.mapper.CommodityInfoMapper;
import com.trjst.model.Collect;
import com.trjst.model.CommodityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CollectService {

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private CommodityInfoMapper commodityInfoMapper;

    public List<Collect> collectAssortList(Integer userId){
        return collectMapper.collectAssortList(userId);
    }

    public List<Collect> collectList(Integer userId,Integer assort_id){
        return collectMapper.collectList(userId,assort_id);
    }

    public Map addCollect(Collect record){
        Map map = new HashMap();
        List<Collect> list = collectMapper.collectListByUserId(record);
        if(list.size() == 0) {
            // 查询商品分类
            CommodityInfo ci = commodityInfoMapper.selectByPrimaryKey2(record.getCommodity_id());
            record.setAssort_id(ci.getAssort_id());
            int num = collectMapper.insertSelective(record);
            if (num > 0) {
                map.put("code", 200);
                map.put("msg", "success");
                return map;
            } else {
                map.put("code", 500);
                map.put("msg", "error");
                return map;
            }
        }else {
            map.put("code", 400);
            map.put("msg", "已收藏过了");
            return map;
        }

    }

    public int delCollect(Integer id){
        return collectMapper.deleteByPrimaryKey(id);
    }
}
