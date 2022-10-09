package com.trjst.mapper;

import com.trjst.model.OperationRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OperationRecordMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(OperationRecord record);

    OperationRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OperationRecord record);

    List getResultList(@Param("start") Integer start, @Param("length_number") Integer length_number);

    Integer getListCount();
}