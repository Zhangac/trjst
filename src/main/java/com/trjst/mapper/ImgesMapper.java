package com.trjst.mapper;

import com.trjst.model.Imges;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ImgesMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Imges record);

    Imges selectByPrimaryKey(Integer id);

    List<Imges> selectByFkList(Imges record);

    int updateByPrimaryKeySelective(Imges record);

    List getResultList(@Param("start") Integer start,
                       @Param("length_number") Integer length_number,
                       @Param("fk_id") Integer fk_id,
                       @Param("type") Integer type);

    Integer getListCount(Integer fk_id,Integer type);

}