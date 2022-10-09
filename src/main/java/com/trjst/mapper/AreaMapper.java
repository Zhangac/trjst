package com.trjst.mapper;

import com.trjst.model.Area;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AreaMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    Area selectAll(Integer id);

    List<Area> listArea();

    int updateByPrimaryKeySelective(Area record);

    List getResultList(@Param("start") Integer start, @Param("length_number") Integer length_number);

    Integer getListCount();

}