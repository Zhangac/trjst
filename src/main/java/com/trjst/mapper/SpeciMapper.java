package com.trjst.mapper;

import com.trjst.model.Speci;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SpeciMapper {

    int deleteByPrimaryKey(Integer speci_id);

    int insertSelective(Speci record);

    Speci selectByPrimaryKey(Integer speci_id);

    List<Speci> selectByCommId(@Param("commodity_info_id") Integer commodity_info_id);

    int updateByPrimaryKeySelective(Speci record);
}