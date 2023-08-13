package com.trjst.mapper;

import com.trjst.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    List<User> userByAll();

    List<User> selectByIsMech(Integer is_mech);

    List<User> getVipInfo(Integer is_vip);

    int updateByPrimaryKeySelective(User record);

    List getResultList(@Param("start") Integer start, @Param("length_number") Integer length_number, @Param("type") Integer type);
    List getResultListYwy(@Param("start") Integer start, @Param("length_number") Integer length_number
            , @Param("is_mech") Integer is_mech, @Param("check_status") Integer check_status);

    Integer getListCount(Integer type);

}