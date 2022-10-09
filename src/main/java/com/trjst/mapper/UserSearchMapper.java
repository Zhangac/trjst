package com.trjst.mapper;

import com.trjst.model.UserSearch;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserSearchMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserSearch record);

    List<UserSearch> selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserSearch record);

}