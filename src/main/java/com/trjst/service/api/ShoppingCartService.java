package com.trjst.service.api;

import com.trjst.mapper.ShoppingCartMapper;
import com.trjst.model.ShoppingCart;
import com.trjst.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    public List<ShoppingCart> selectByUserId(Integer userId,Integer cs_type){
        List<ShoppingCart> sc = shoppingCartMapper.selectByUserId(userId,cs_type);
        if (sc.size() > 0){
            Iterator<ShoppingCart> iterator = sc.listIterator();
            while (iterator.hasNext()){
                ShoppingCart s = iterator.next();
                String speciName = s.getSpeci_name();
                if(StringUtil.isEmpty(speciName)){
                    iterator.remove();
                }
            }
        }
        return sc;
    }

    public Map addShoppingCart(ShoppingCart record){
        Map map = new HashMap();
        ShoppingCart sc = shoppingCartMapper.selectByUserAndCommId(record);
        if(sc!=null){
            map.put("code",400);
            map.put("msg","已加入购物车");
        }else {
            int num = shoppingCartMapper.insertSelective(record);
            if(num > 0){
                map.put("code",200);
                map.put("msg","success");
            }else {
                map.put("code",500);
                map.put("msg","error");
            }
        }
        return map;
    }

    public int updateShoppingCart(ShoppingCart record){
        return shoppingCartMapper.updateByPrimaryKeySelective(record);
    }

    public int delShoppingCart(Integer id){
        return shoppingCartMapper.deleteByPrimaryKey(id);
    }
}
