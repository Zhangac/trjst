package com.trjst.service.api;

import com.trjst.mapper.UserMapper;
import com.trjst.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User userById(Integer userId){
        return userMapper.selectByPrimaryKey(userId);
    }

    public Map addUser(User record){
        Map map = new HashMap();
        if(!record.getOpen_id().equals(null) && !record.getOpen_id().equals("")) {
            List<User> users = userMapper.userByAll();
            for (User u : users) {
                if (record.getOpen_id().equals(u.getOpen_id())) {
                    map.put("code", 400);
                    map.put("msg", "用户已添加,openid：" + u.getOpen_id());
                    map.put("data", u.getId());
                    return map;
                }
            }
        }else{
            map.put("code", 201);
            map.put("msg", "openid不能为空");
            return map;
        }
        int id = userMapper.insertSelective(record);
        if(id > 0){
            map.put("code",200);
            map.put("msg","success");
            map.put("id",record.getId());
            return map;
        }else {
            map.put("code",500);
            map.put("msg","error");
            return map;
        }
    }

    public int updateUser(User record){
        return userMapper.updateByPrimaryKeySelective(record);
    }

}
