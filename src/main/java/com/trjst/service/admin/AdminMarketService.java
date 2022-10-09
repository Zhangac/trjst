package com.trjst.service.admin;

import com.alibaba.fastjson.JSONObject;
import com.trjst.mapper.MarketMapper;
import com.trjst.model.Market;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminMarketService {

    @Autowired
    private MarketMapper marketMapper;

    /**
     * 返回含列表的json
     * @param start,length_number,draw,type
     * @return
     * */
    public String getResultJson(Integer start, Integer length_number, Integer draw) {
        List resultList = marketMapper.getResultList(start, length_number);
        Integer countnumber = marketMapper.getListCount();
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
    public Market findPojoById(Integer id) {
        if(id!=null) {
            return marketMapper.selectByPrimaryKey(id);
        }
        return new Market();
    }

    /**
     *编辑banner的url
     * @param id,banner_url
     * @return
     * */
    public Map edit(Integer id, Integer area_id,String market_name,
                    String market_img,String market_province,String market_city,
                    String market_region,String market_address,Integer market_status) {
        Map map = new HashMap<String, String>();
        try {
            if (id == null) {
                Market market = new Market();
                market.setArea_id(area_id);
                market.setMarket_name(market_name);
                market.setMarket_img(market_img);
                market.setMarket_province(market_province);
                market.setMarket_city(market_city);
                market.setMarket_region(market_region);
                market.setMarket_address(market_address);
                market.setMarket_status(1);
                marketMapper.insertSelective(market);
                map.put("code", "100");
            } else {
                Market market = marketMapper.selectByPrimaryKey(id);
                market.setId(id);
                market.setArea_id(area_id);
                market.setMarket_name(market_name);
                if(!market_img.equals(null) && !market_img.equals(""))
                {
                    market.setMarket_img(market_img);
                }
                market.setMarket_province(market_province);
                market.setMarket_city(market_city);
                market.setMarket_region(market_region);
                market.setMarket_address(market_address);
                market.setMarket_status(market_status);
                marketMapper.updateByPrimaryKeySelective(market);
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
            Market market = marketMapper.selectByPrimaryKey(id);
            market.setMarket_status(status);
            marketMapper.updateByPrimaryKeySelective(market);
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
            marketMapper.deleteByPrimaryKey(id);
            map.put("code", "100");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;
    }
}
