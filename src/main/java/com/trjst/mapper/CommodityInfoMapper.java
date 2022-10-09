package com.trjst.mapper;

import com.trjst.model.CommodityInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommodityInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(CommodityInfo record);

    List<CommodityInfo> commodityList(CommodityInfo record);

    List<CommodityInfo> commodityList2(CommodityInfo record);

    CommodityInfo selectByPrimaryKey(Integer id);

    CommodityInfo selectByPrimaryKey2(Integer id);

    CommodityInfo selectById(Integer id);

    int updateByPrimaryKeySelective(CommodityInfo record);

    List getResultList(@Param("start") Integer start, @Param("length_number") Integer length_number,
                       @Param("commodity_name")String commodity_name, @Param("audit_status")Integer audit_status,
                       @Param("status")Integer status, @Param("is_hot")Integer is_hot);

    Integer getListCount(@Param("commodity_name")String commodity_name,@Param("audit_status")Integer audit_status,
                         @Param("status")Integer status,@Param("is_hot")Integer is_hot);

}