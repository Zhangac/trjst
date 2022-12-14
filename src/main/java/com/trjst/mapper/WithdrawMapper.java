package com.trjst.mapper;

import com.trjst.model.Withdraw;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WithdrawMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Withdraw record);

    List<Withdraw> selectByUserId(Integer userId);

    Withdraw selectAllById(Integer id);

    int updateByPrimaryKeySelective(Withdraw record);

    List getResultList(@Param("start") Integer start, @Param("length_number") Integer length_number);

    Integer getListCount();
}