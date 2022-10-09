package com.trjst.service.api;

import com.trjst.mapper.UserAddressMapper;
import com.trjst.model.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    public List<UserAddress> selectByUserId(Integer user_id){
        return userAddressMapper.selectByUserId(user_id);
    }

    public int addUserAddress(UserAddress record){
        return userAddressMapper.insertSelective(record);
    }

    public int updateUserAddress(UserAddress record){
        return userAddressMapper.updateByPrimaryKeySelective(record);
    }

    public int delUserAddress(Integer id){
        return userAddressMapper.deleteByPrimaryKey(id);
    }
}
