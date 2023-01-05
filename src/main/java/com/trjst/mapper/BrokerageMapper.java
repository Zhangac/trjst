package com.trjst.mapper;

import com.trjst.model.Brokerage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BrokerageMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Brokerage record);

    Brokerage selectByPrimaryKey(Integer id);

    List<Brokerage> selectByType(@Param("type") Integer type);

    List<Brokerage> selectByAll();

    int updateByPrimaryKeySelective(Brokerage record);

    List getResultList(@Param("start") Integer start, @Param("length_number") Integer length_number);

    Integer getListCount();

    List getVipResultList(@Param("start") Integer start, @Param("length_number") Integer length_number);

    Integer getVipListCount();

}