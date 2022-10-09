package com.trjst.mapper;

import com.trjst.model.Banner;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BannerMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(Integer id);

    List<Banner> listBanner();

    Banner selectAll(Integer id);

    int updateByPrimaryKeySelective(Banner record);

    List getResultList(@Param("start") Integer start, @Param("length_number") Integer length_number, @Param("type") Integer type);

    Integer getListCount(Integer type);

}