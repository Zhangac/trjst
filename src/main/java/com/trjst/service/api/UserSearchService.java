package com.trjst.service.api;

import com.trjst.mapper.UserSearchMapper;
import com.trjst.model.UserSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSearchService {

    @Autowired
    private UserSearchMapper userSearchMapper;

    public List<UserSearch> searchByUserId(Integer userId){
        return userSearchMapper.selectByPrimaryKey(userId);
    }

    public int addSearch(UserSearch record){
        return userSearchMapper.insertSelective(record);
    }

    public int delSearch(Integer id){
        return userSearchMapper.deleteByPrimaryKey(id);
    }
}
