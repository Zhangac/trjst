package com.trjst.controller.admin;

import com.trjst.service.admin.AdminBrokerageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

@Controller
@Slf4j
public class AdminBrokerageController {

    @Autowired
    private AdminBrokerageService adminBrokerageService;


    //列表
    @RequestMapping("/adminBrokeragelist")
    public String adminBrokeragelist(HttpServletRequest request) {
        return "brokerage/list";
    }

    //vip列表
    @RequestMapping("/adminvipBrokeragelist")
    public String adminvipBrokeragelist(HttpServletRequest request) {
        return "brokerage/viplist";
    }

    //vip添加
    @RequestMapping("/adminVipBrokerageadd")
    public String adminVipBrokerageadd(HttpServletRequest request){
        return "brokerage/vipedit";
    }
    //vip编辑
    @RequestMapping("/adminVipBrokerageedit")
    public String adminVipBrokerageeditajax(HttpServletRequest request,Integer id){
        request.setAttribute("bk",adminBrokerageService.findPojoById(id));
        request.setAttribute("id",id);
        return "brokerage/vipedit";
    }

    @RequestMapping(value = "/adminVipBrokeragelistajax")
    @ResponseBody
    public String adminVipBrokeragelistajax(HttpServletRequest request, Integer draw){
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length_number = Integer.valueOf(request.getParameter("length"));
        return adminBrokerageService.getVipResultJson(start,length_number,draw);
    }

    @RequestMapping(value = "/adminBrokeragelistajax")
    @ResponseBody
    public String adminBrokeragelistajax(HttpServletRequest request, Integer draw){
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length_number = Integer.valueOf(request.getParameter("length"));
        return adminBrokerageService.getResultJson(start,length_number,draw);
    }
    //添加
    @RequestMapping("/adminBrokerageadd")
    public String adminBrokerageadd(HttpServletRequest request){
        return "brokerage/add";
    }
    //编辑
    @RequestMapping("/adminBrokerageedit")
    public String adminBrokerageeditajax(HttpServletRequest request,Integer id){
        request.setAttribute("bk",adminBrokerageService.findPojoById(id));
        request.setAttribute("id",id);
        return "brokerage/edit";
    }
    //编辑
    @RequestMapping(value = "/adminBrokerageeditajax")
    @ResponseBody
    public Map adminBrokerageeditajax(Integer id, BigDecimal brokerage_amount,Integer type,Integer sort){
        return adminBrokerageService.edit(id,brokerage_amount,type,sort);
    }

    //删除
    @RequestMapping(value = "/admdelBrokerageajax")
    @ResponseBody
    public Map admdelBrokerageajax(Integer id) {
        return adminBrokerageService.daletePojo(id);
    }

}
