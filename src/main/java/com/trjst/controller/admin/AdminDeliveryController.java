package com.trjst.controller.admin;

import com.trjst.service.admin.AdminDeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@Slf4j
public class AdminDeliveryController {

    @Autowired
    private AdminDeliveryService adminDeliveryService;


    //列表
    @RequestMapping("/admindeliverylist")
    public String admindeliverylist(HttpServletRequest request) {
        return "delivery/list";
    }

    //待审核列表
    @RequestMapping("/admindeliverydslist")
    public String admindeliverydslist(HttpServletRequest request,Integer status) {
        request.setAttribute("status",status);
        return "delivery/auditstatuslist";
    }

    @RequestMapping(value = "/admindeliverylistajax")
    @ResponseBody
    public String admindeliverylistajax(HttpServletRequest request, Integer draw,String dvname,
                                  Integer status){
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length_number = Integer.valueOf(request.getParameter("length"));
        return adminDeliveryService.getResultJson(start,length_number,draw,dvname,status);
    }

    //审核
    @RequestMapping(value = "/admindeliveryshajax")
    @ResponseBody
    public Map admindeliveryshajax(Integer id, Integer status){
        status = 1;
        return adminDeliveryService.shenhe(id,status);
    }

    //删除
    @RequestMapping(value = "/admindeliverydelajax")
    @ResponseBody
    public Map admindeliverydelajax(Integer id){
        return adminDeliveryService.daletePojo(id);
    }

}
