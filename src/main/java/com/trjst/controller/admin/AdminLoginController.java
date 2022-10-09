package com.trjst.controller.admin;

import com.trjst.model.Admin;
import com.trjst.service.admin.AdminService;
import com.trjst.util.EmptyUtil;
import com.trjst.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Alex Chin
 * */
@Controller
public class AdminLoginController {
    @Autowired
    private AdminService adminService;

    //后台登录页面
    @RequestMapping("/adminlogin")
    public String admin(HttpServletRequest request, HttpServletResponse resp)
            throws IOException {
        return "login";
    }
    //后台登录
    @RequestMapping( value = "/adminloginajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8" )
    @ResponseBody
    public Map adminloginajax(HttpServletRequest request, String account, String password,
                              String code)  throws NullPointerException {

        Enumeration enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            System.out.println(paraName+": "+request.getParameter(paraName));
        }

        HttpSession session = request.getSession();
        Map returnmap = new HashMap();
        String passwordmd5 = MD5.MD5Encode("y7<LF5H2qgfIx]AD{6Yg"+MD5.MD5Encode(password, "UTF-8"), "UTF-8");

        String r_code=session.getAttribute("code").toString();
        System.out.println("获取验证码："+r_code);
//        if (r_code.equals("")&& r_code==null){
//            r_code="";
//            System.out.println("未获取验证码："+r_code);
//        }


        if (code.equalsIgnoreCase(r_code)) {
            Admin admin = adminService.findAdminByAccount(account);

            if(EmptyUtil.isNotEmpty(admin)) {
                if(admin.getPassword().equals(passwordmd5)) {
                    session.setAttribute("admin", admin);
                    returnmap.put("rcode", "100");
                    returnmap.put("rinfo", "成功");

                    System.out.println(returnmap);

                }else {
                    returnmap.put("rcode", "400");
                    returnmap.put("rinfo", "账号密码错误");
                }
            }else {
                returnmap.put("rcode", "400");
                returnmap.put("rinfo", "账号密码错误");
            }

        }else {
            returnmap.put("rcode", "400");
            returnmap.put("rinfo", "验证码错误");
        }
        return returnmap;
    }



}
