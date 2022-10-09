package com.trjst.mapper;

import com.trjst.model.Delivery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DeliveryMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Delivery record);

    Delivery selectByPrimaryKey(Integer id);

    Delivery selectByUserId(Integer userId);

    List<Delivery> selectAll();

    int updateByPrimaryKeySelective(Delivery record);

    List getResultList(@Param("start") Integer start, @Param("length_number") Integer length_number,
                       @Param("name")String name, @Param("status")Integer status);

    Integer getListCount(@Param("name")String name, @Param("status")Integer status);
}