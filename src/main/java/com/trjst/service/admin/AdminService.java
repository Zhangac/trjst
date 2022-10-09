package com.trjst.service.admin;

import com.trjst.mapper.AdminMapper;
import com.trjst.mapper.AssortMapper;
import com.trjst.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

;

/**
 * Alex Chin
 * */
@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AssortMapper assortMapper;

    /**
     * 通过账号名找到admin
     * @param account
     * @return
     * */
    public Admin findAdminByAccount(String account){
//        Object obj = assortMapper.selectByPrimaryKey(1);
//        System.out.println(obj);
        return adminMapper.findAdminByAccount(account);
    }

    /**
     * 返回admin列表
     * */
    public List getAdminList(Integer start, Integer number) {
        return adminMapper.selectAdminList(start,number);
    }

    /**
     * 返回列表元素个数
     * */
    public Integer getAdminListCount() {
        return adminMapper.getAdminListCount();
    }

    /**
     * 通过id找到admin
     * @param admin_id
     * @return
     * */
    public com.trjst.model.Admin findAdminById(Integer admin_id) {

        return adminMapper.findAdminById(admin_id);
    }

    /**
     * 更新admin
     * */
    public void updateAdmin(Admin admin) {
        adminMapper.updateSelective(admin);
    }

    /**
     * 添加admin
     * */
    public void insertAdmin(Admin admin) {
        adminMapper.insertSelective(admin);
    }
}
