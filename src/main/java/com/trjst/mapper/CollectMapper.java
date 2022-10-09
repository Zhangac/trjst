package com.trjst.mapper;

import com.trjst.model.Collect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CollectMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);

    List<Collect> collectList(@Param("userId") Integer userId, @Param("assort_id") Integer assort_id);

    List<Collect> collectAssortList(Integer userId);

    List<Collect> collectListByUserId(Collect record);

    int updateByPrimaryKeySelective(Collect record);

}