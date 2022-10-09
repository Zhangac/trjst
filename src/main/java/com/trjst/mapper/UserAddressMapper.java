package com.trjst.mapper;

import com.trjst.model.UserAddress;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserAddressMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserAddress record);

    UserAddress selectByPrimaryKey(Integer id);

    List<UserAddress> selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(UserAddress record);
}