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

    //业务员列表
    @RequestMapping("/adminuserywylist")
    public String adminuserywylist(HttpServletRequest request) {
        return "yw/list";
    }

    @RequestMapping(value = "/adminuserywylistajax")
    @ResponseBody
    public String adminuserywylistajax(HttpServletRequest request, Integer draw,
                                       Integer is_mech,Integer check_status){
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length_number = Integer.valueOf(request.getParameter("length"));
        return adminUserService.getResultYwyJson(start,length_number,draw,is_mech,check_status);
    }

    //删除
    @RequestMapping(value = "/admindelajax")
    @ResponseBody
    public Map admindelajax(Integer id) {
        return adminUserService.daletePojo(id);
    }

    //审核
    @RequestMapping(value = "/updateYwyUser")
    @ResponseBody
    public Map updateYwyUser(Integer id) {
        return adminUserService.updateYwyUser(id);
    }
}
