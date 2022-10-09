package com.trjst.mapper;

//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trjst.model.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AdminMapper {

    int insertSelective(Admin record);

    void updateSelective(Admin admin);

    Admin findAdminById(Integer admin_id);

    Admin findAdminByAccount(@Param("account") String account);

    List<Admin> selectAdminList(@Param("start") Integer start, @Param("number") Integer number);

    Integer getAdminListCount();
}