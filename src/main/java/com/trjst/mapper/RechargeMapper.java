package com.trjst.mapper;

import com.trjst.model.Recharge;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RechargeMapper {
    int deleteByPrimaryKey(Integer id);


    int insertSelective(Recharge record);

    Recharge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Recharge record);

    List getResultList(@Param("start") Integer start, @Param("length_number") Integer length_number);

    Integer getListCount();
}