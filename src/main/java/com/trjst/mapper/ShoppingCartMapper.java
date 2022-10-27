package com.trjst.mapper;

import com.trjst.model.ShoppingCart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ShoppingCartMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(ShoppingCart record);

    ShoppingCart selectByPrimaryKey(Integer id);

    ShoppingCart selectByUserAndCommId(ShoppingCart record);

    List<ShoppingCart> selectByUserId(@Param("user_id")Integer user_id,@Param("cs_type")Integer cs_type);

    List<ShoppingCart> selectByCommId(Integer commodity_id);

    int updateByPrimaryKeySelective(ShoppingCart record);

}