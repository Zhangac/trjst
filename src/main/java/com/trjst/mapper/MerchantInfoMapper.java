package com.trjst.mapper;

import com.trjst.model.MerchantInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MerchantInfoMapper {

   List<MerchantInfo> merchantInfoByName(MerchantInfo record);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(MerchantInfo record);

    MerchantInfo selectByPrimaryKey(Integer id);

    MerchantInfo selectByUserId(Integer userId);

    List<MerchantInfo> selectAll();

    List<MerchantInfo> selectByMarketId(Integer market_id);

    int updateByPrimaryKeySelective(MerchantInfo record);

    List getResultList(@Param("start") Integer start, @Param("length_number") Integer length_number,
                       @Param("merchant_name")String merchant_name,@Param("audit_status")Integer audit_status,
                       @Param("status")Integer status,@Param("is_hot")Integer is_hot);

    Integer getListCount(@Param("merchant_name")String merchant_name,@Param("audit_status")Integer audit_status,
                         @Param("status")Integer status,@Param("is_hot")Integer is_hot);

}