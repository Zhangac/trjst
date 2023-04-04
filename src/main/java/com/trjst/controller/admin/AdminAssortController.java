package com.trjst.controller.admin;

import com.trjst.service.admin.AdminAssortService;
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
public class AdminAssortController {

    @Autowired
    private AdminAssortService adminAssortService;

    //列表
    @RequestMapping("/adminassortlist")
    public String adminassortlist(HttpServletRequest request,Integer level) {
        request.setAttribute("level",level);
        return "assort/list";
    }


    @RequestMapping(value = "/adminassortlistajax")
    @ResponseBody
    public String adminassortlistajax(HttpServletRequest request, Integer draw){
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length_number = Integer.valueOf(request.getParameter("length"));
        return adminAssortService.getResultJson(start,length_number,draw);
    }

    //添加
    @RequestMapping("/adminassortadd")
    public String adminassortadd(HttpServletRequest request){
        return "assort/edit";
    }

    //编辑
    @RequestMapping("/adminassortedit")
    public String adminassortedit(HttpServletRequest request,Integer id){
        request.setAttribute("assort",adminAssortService.findPojoById(id));
        request.setAttribute("id",id);
        return "assort/edit";
    }
    //编辑
    @RequestMapping(value = "/adminassorteditajax")
    @ResponseBody
    public Map adminassorteditajax(Integer id, String name, Integer sort,
                                   BigDecimal yongjin,String yuliu,Integer percent,
                                   BigDecimal psy_yongjin,Integer psy_percent){
        return adminAssortService.edit(id,name,sort,yongjin,yuliu,percent,psy_yongjin,psy_percent);
    }

    //删除
    @RequestMapping(value = "/admdelassortajax")
    @ResponseBody
    public Map admdelassortajax(Integer id) {
        return adminAssortService.daletePojo(id);
    }


}
