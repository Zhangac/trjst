package com.trjst.mapper;

import com.trjst.model.Market;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MarketMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Market record);

    Market selectByPrimaryKey(Integer id);

    List<Market> marketList(Market record);

    int updateByPrimaryKeySelective(Market record);

    List getResultList(@Param("start") Integer start, @Param("length_number") Integer length_number);

    Integer getListCount();
}