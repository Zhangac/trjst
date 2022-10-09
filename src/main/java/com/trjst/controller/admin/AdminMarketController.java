package com.trjst.controller.admin;

import com.trjst.service.admin.AdminMarketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@Slf4j
public class AdminMarketController {

    @Autowired
    private AdminMarketService adminMarketService;

    //列表
    @RequestMapping("/adminmarketlist")
    public String adminmarketlist(HttpServletRequest request) {
        return "market/list";
    }


    @RequestMapping(value = "/adminmarketlistajax")
    @ResponseBody
    public String adminmarketlistajax(HttpServletRequest request, Integer draw){
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length_number = Integer.valueOf(request.getParameter("length"));
        return adminMarketService.getResultJson(start,length_number,draw);
    }

    //添加
    @RequestMapping("/adminmarketadd")
    public String adminmarketadd(HttpServletRequest request){
        return "market/edit";
    }

    //编辑
    @RequestMapping("/adminmarketedit")
    public String adminmarketedit(HttpServletRequest request,Integer id){
        request.setAttribute("market",adminMarketService.findPojoById(id));
        request.setAttribute("id",id);
        return "market/edit";
    }
    //编辑
    @RequestMapping(value = "/adminmarketeditajax")
    @ResponseBody
    public Map adminmarketeditajax(Integer id, Integer area_id,String market_name,
                                 String market_img,String market_province,String market_city,
                                 String market_region,String market_address,Integer market_status){
        return adminMarketService.edit(id,area_id,market_name,market_img,market_province,
                market_city,market_region,market_address,market_status);
    }
    //启用
    @RequestMapping(value = "/setmarketonajax")
    @ResponseBody
    public Map setmarketonajax(Integer id){
        Integer status = 1;
        return adminMarketService.setStatus(status,id);
    }
    //停用
    @RequestMapping(value = "/setmarketoffajax")
    @ResponseBody
    public Map setmarketoffajax(Integer id){
        Integer status = 0;
        return adminMarketService.setStatus(status,id);
    }

    //删除
    @RequestMapping(value = "/admdeletemarketajax")
    @ResponseBody
    public Map admdeletemarketajax(Integer id) {
        return adminMarketService.daletePojo(id);
    }


}
