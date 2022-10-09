package com.trjst.service.api;

//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trjst.dto.AdminDto;
import com.trjst.mapper.AdminMapper;
import com.trjst.mapper.DemoMapper;
import com.trjst.model.Admin;
import com.trjst.model.Demo;
import com.trjst.util.PageUtil2;
//import com.jst.util.PageUtils;
import com.trjst.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class DemoService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private DemoMapper demoMapper;

    public int demo2() throws Exception {
        Demo d = new Demo();
        d.setStatus(2);
        d.setId(1);
        int num = demoMapper.update(d);

        Demo demo = demoMapper.selectByPrimaryKey(1);
        if(demo.getStatus()==2){
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
        return num;
    }

    public Admin aa(Admin adminEntity){
//        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
//        wrapper.eq("account", adminEntity.getAccount());
//        Admin admin = adminMapper.selectOne(wrapper);
//        return admin;
        return null;
    }

    public R getAdminPage(AdminDto af){
//        IPage<Admin> page = new Page<>(af.getPage(), af.getRows());
//        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
//        wrapper.eq("status","1");
//        wrapper.orderByDesc("create_time");
//        List<Admin> list = adminMapper.selectList(wrapper);
//        List<Admin> listOut = PageUtil2.startPage(list, af.getPage(), af.getRows());
//        PageUtils pages = new PageUtils(listOut,list.size(),af.getPage(),af.getRows());
//        return R.ok().putData(pages);
        return null;
    }
}
