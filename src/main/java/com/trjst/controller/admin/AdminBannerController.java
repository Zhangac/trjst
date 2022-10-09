package com.trjst.controller.admin;

import com.trjst.service.admin.AdminBannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@Slf4j
public class AdminBannerController {
    @Autowired
    private AdminBannerService bannerService;


    //主页轮播列表
    @RequestMapping("/admhomebannerlist")
    public String admhomebannerlist(HttpServletRequest request) {
        return "banner/bannerlist";
    }


    @RequestMapping(value = "/admbannerlistajax")
    @ResponseBody
    public String admbannerlistajax(HttpServletRequest request, Integer draw,Integer type){
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length_number = Integer.valueOf(request.getParameter("length"));
        return bannerService.getResultJson(start,length_number,draw,type);
    }



    //添加
    @RequestMapping("/admbanneradd")
    public String admbanneradd(HttpServletRequest request){
        return "banner/banneredit";
    }

    //编辑
    @RequestMapping("/admbanneredit")
    public String admbanneredit(HttpServletRequest request,Integer id){
        request.setAttribute("banner",bannerService.findPojoById(id));
        request.setAttribute("id",id);
        return "banner/banneredit";
    }
    //编辑
    @RequestMapping(value = "/admbannereditajax")
    @ResponseBody
    public Map admbannereditajax(Integer id, String pic,Integer sort){
        log.info("pic:{}"+pic);
        return bannerService.editBannerInfo(id,pic,sort);
    }
    //启用
    @RequestMapping(value = "/setbanneronajax")
    @ResponseBody
    public Map setbanneronajax(Integer id){
        Integer status = 1;
        return bannerService.setStatus(status,id);
    }
    //停用
    @RequestMapping(value = "/setbanneroffajax")
    @ResponseBody
    public Map setbanneroffajax(Integer id){
        Integer status = 0;
        return bannerService.setStatus(status,id);
    }

    //删除
    @RequestMapping(value = "/admdeletebannerajax")
    @ResponseBody
    public Map admdeletebannerajax(Integer id) {
        return bannerService.daletePojo(id);
    }


}
