package com.trjst.controller.admin;

import com.trjst.service.admin.AdminWithdrawService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@Slf4j
public class AdminWithdrawController {

    @Autowired
    private AdminWithdrawService adminWithdrawService;

    //列表
    @RequestMapping("/adminwithlist")
    public String adminwithlist(HttpServletRequest request) {
        return "withdraw/list";
    }


    @RequestMapping(value = "/adminwithlistajax")
    @ResponseBody
    public String adminwithlistajax(HttpServletRequest request, Integer draw,String logmax
            ,String logmin,String phone,String name){
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length_number = Integer.valueOf(request.getParameter("length"));
        return adminWithdrawService.getResultJson(start,length_number,draw,logmax,logmin
                ,phone,name);
    }

    //通过
    @RequestMapping(value = "/setwithonajax")
    @ResponseBody
    public Map setwithonajax(Integer id){
        Integer status = 2;
        return adminWithdrawService.setStatus(status,id);
    }
    //不通过
    @RequestMapping(value = "/setwithoffajax")
    @ResponseBody
    public Map setwithoffajax(Integer id){
        Integer status = 3;
        return adminWithdrawService.setStatus(status,id);
    }

    //删除
    @RequestMapping(value = "/admdeletewithajax")
    @ResponseBody
    public Map admdeletewithajax(Integer id) {
        return adminWithdrawService.daletePojo(id);
    }


}
