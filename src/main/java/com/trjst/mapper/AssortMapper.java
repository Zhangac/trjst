package com.trjst.mapper;

import com.trjst.model.Assort;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AssortMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Assort record);

    Assort selectByPrimaryKey(Integer id);

    List<Assort> listAssort();

    int updateByPrimaryKeySelective(Assort record);

    List getResultList(@Param("start") Integer start, @Param("length_number") Integer length_number);

    Integer getListCount();
}