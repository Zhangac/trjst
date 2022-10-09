package com.trjst.controller.admin;

import com.trjst.service.admin.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@Slf4j
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;


    //用户列表
    @RequestMapping("/adminuserlist")
    public String adminuserlist(HttpServletRequest request) {
        return "user/list";
    }

    @RequestMapping(value = "/adminuserlistajax")
    @ResponseBody
    public String adminuserlistajax(HttpServletRequest request, Integer draw,Integer type){
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length_number = Integer.valueOf(request.getParameter("length"));
        return adminUserService.getResultJson(start,length_number,draw,type);
    }

    //删除
    @RequestMapping(value = "/admindelajax")
    @ResponseBody
    public Map admindelajax(Integer id) {
        return adminUserService.daletePojo(id);
    }
}
