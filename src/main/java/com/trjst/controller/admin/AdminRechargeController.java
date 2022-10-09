package com.trjst.controller.admin;

import com.trjst.service.admin.AdminRechargeService;
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
public class AdminRechargeController {
    @Autowired
    private AdminRechargeService adminRechargeService;


    //列表
    @RequestMapping("/adminrechlist")
    public String adminrechlist(HttpServletRequest request) {
        return "recharge/list";
    }

    @RequestMapping(value = "/adminrechlistajax")
    @ResponseBody
    public String adminrechlistajax(HttpServletRequest request, Integer draw){
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length_number = Integer.valueOf(request.getParameter("length"));
        return adminRechargeService.getResultJson(start,length_number,draw);
    }

    //删除
    @RequestMapping(value = "/adminrechdelajax")
    @ResponseBody
    public Map adminrechdelajax(Integer id) {
        return adminRechargeService.daletePojo(id);
    }
}
