package com.trjst.mapper;

import com.trjst.model.DelivMerch;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DelivMerchMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(DelivMerch record);

    DelivMerch selectByPrimaryKey(Integer id);

    DelivMerch selectByMerId(Integer mer_id);

    List<DelivMerch> selectByDeliId(Integer delivery_id);

    int updateByPrimaryKeySelective(DelivMerch record);

    List getResultList(@Param("start") Integer start, @Param("length_number") Integer length_number,@Param("delivery_id") Integer delivery_id);

    Integer getListCount(@Param("delivery_id") Integer delivery_id);
}