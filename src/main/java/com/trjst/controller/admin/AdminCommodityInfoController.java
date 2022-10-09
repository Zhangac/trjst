package com.trjst.controller.admin;

import com.trjst.model.CommodityInfo;
import com.trjst.service.admin.AdminCommodityInfoService;
import com.trjst.service.admin.AdminImgesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@Slf4j
public class AdminCommodityInfoController {

    @Autowired
    private AdminCommodityInfoService adminCommodityInfoService;

    @Autowired
    private AdminImgesService adminImgesService;

    //imges列表删除
    @RequestMapping(value = "/adminimagedelajax")
    @ResponseBody
    public Map adminimagedelajax(Integer id){
        return adminImgesService.daletePojo(id);
    }

    //imge添加
    @RequestMapping("/adminimageadd")
    public String adminimageadd(HttpServletRequest request,Integer fk_id,Integer type) {
        request.setAttribute("fk_id",fk_id);
        request.setAttribute("type",type);
        return "imges/add";
    }

    //imge添加ajax
    @RequestMapping(value = "/adminimageaddajax")
    @ResponseBody
    public Map adminimageaddajax(Integer fk_id, String pic,Integer type){
        log.info("pic:{}",pic);
        log.info("fk_id:{}",fk_id);
        log.info("type:{}",type);
        return adminImgesService.add(fk_id,pic,type);
    }

    //imges列表
    @RequestMapping("/adminimagelist")
    public String adminimagelist(HttpServletRequest request,Integer fk_id,Integer type) {
        request.setAttribute("fk_id",fk_id);
        request.setAttribute("type",type);
        return "imges/list";
    }

    //imges列表ajax
    @RequestMapping(value = "/adminimgeslistajax")
    @ResponseBody
    public String adminimgeslistajax(HttpServletRequest request, Integer draw,Integer fk_id,Integer type){
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length_number = Integer.valueOf(request.getParameter("length"));
        return adminImgesService.getResultJson(start,length_number,draw,fk_id,type);
    }

    //列表
    @RequestMapping("/admincommlist")
    public String admincommlist(HttpServletRequest request) {
        return "commodity/list";
    }

    //列表
    @RequestMapping("/admincommaslist")
    public String admincommaslist(HttpServletRequest request,Integer audit_status) {
        request.setAttribute("audit_status",audit_status);
        return "commodity/commlist";
    }


    @RequestMapping(value = "/admincommlistajax")
    @ResponseBody
    public String admincommlistajax(HttpServletRequest request, Integer draw, String commodity_name,
                                  Integer audit_status,Integer status,Integer is_hot){
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length_number = Integer.valueOf(request.getParameter("length"));
        return adminCommodityInfoService.getResultJson(start,length_number,draw,commodity_name,audit_status,status,is_hot);
    }

    //编辑
    @RequestMapping("/admincommedit")
    public String admincommedit(HttpServletRequest request,Integer id){
        CommodityInfo ci  = adminCommodityInfoService.findPojoById(id);
        request.setAttribute("mi",ci);
        request.setAttribute("id",id);
        return "commodity/edit";
    }

    //编辑or添加Ajax
    @PostMapping("/admincommeditAjax")
    @ResponseBody
    public Map admincommeditAjax(@ModelAttribute CommodityInfo req){
        return adminCommodityInfoService.addOrUpdateCommodityInfo(req);
    }

    //审核
    @RequestMapping(value = "/admincommeditajax")
    @ResponseBody
    public Map admincommeditajax(Integer id, Integer audit_status){
        audit_status = 1;
        return adminCommodityInfoService.edit(id,audit_status);
    }

    //上架
    @RequestMapping(value = "/admincommxjajax")
    @ResponseBody
    public Map admincommxjajax(Integer id, Integer status){
        return adminCommodityInfoService.xiajia(id,status);
    }


    //推荐
    @RequestMapping(value = "/admincommtjajax")
    @ResponseBody
    public Map admincommtjajax(Integer id, Integer is_hot){
        return adminCommodityInfoService.tuijian(id,is_hot);
    }

    //删除
    @RequestMapping(value = "/admincommdelajax")
    @ResponseBody
    public Map admincommdelajax(Integer id){
        return adminCommodityInfoService.daletePojo(id);
    }

}
