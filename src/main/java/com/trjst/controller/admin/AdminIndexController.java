package com.trjst.controller.admin;

import com.trjst.model.Admin;
import com.trjst.util.EmptyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Alex Chin
 * */
@Controller
public class AdminIndexController {

    //后台主页
    @RequestMapping("/adminindex")
    public String admin(HttpServletRequest request, HttpServletResponse resp)
    {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if(EmptyUtil.isEmpty(admin)){
            return "login";
        }else {
            request.setAttribute("admin", admin);
            request.setAttribute("name", admin.getAccount());
            return "index";
        }

    }
    //欢迎页面
    @RequestMapping("/adminwelcome")
    public String landlordwelcome(HttpServletRequest request, HttpServletResponse resp)
    {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        return "welcome";
    }
}
