package com.trjst.controller.admin;

import com.trjst.service.admin.AdminAreaService;
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
public class AdminAreaController {

    @Autowired
    private AdminAreaService adminAreaService;

    //列表
    @RequestMapping("/adminarealist")
    public String adminarealist(HttpServletRequest request) {
        return "area/list";
    }


    @RequestMapping(value = "/adminarealistajax")
    @ResponseBody
    public String adminarealistajax(HttpServletRequest request, Integer draw){
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length_number = Integer.valueOf(request.getParameter("length"));
        return adminAreaService.getResultJson(start,length_number,draw);
    }

    //添加
    @RequestMapping("/adminareaadd")
    public String adminareaadd(HttpServletRequest request){
        return "area/edit";
    }

    //编辑
    @RequestMapping("/adminareaedit")
    public String adminareaedit(HttpServletRequest request,Integer id){
        request.setAttribute("area",adminAreaService.findPojoById(id));
        request.setAttribute("id",id);
        return "area/edit";
    }
    //编辑
    @RequestMapping(value = "/adminareaeditajax")
    @ResponseBody
    public Map adminareaeditajax(Integer id, String name,Integer sort, String pic){
        return adminAreaService.edit(id,name,sort,pic);
    }
    //启用
    @RequestMapping(value = "/setareaonajax")
    @ResponseBody
    public Map setareaonajax(Integer id){
        Integer status = 1;
        return adminAreaService.setStatus(status,id);
    }
    //停用
    @RequestMapping(value = "/setareaoffajax")
    @ResponseBody
    public Map setareaoffajax(Integer id){
        Integer status = 0;
        return adminAreaService.setStatus(status,id);
    }

    //删除
    @RequestMapping(value = "/admdeleteareaajax")
    @ResponseBody
    public Map admdeleteareaajax(Integer id) {
        return adminAreaService.daletePojo(id);
    }


}
