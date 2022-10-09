package com.trjst.controller.admin;

import com.trjst.mapper.MerchantInfoMapper;
import com.trjst.service.admin.AdminDelivMerchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@Slf4j
public class AdminDelivMerchController {

    @Autowired
    private AdminDelivMerchService adminDelivMerchService;

    @Autowired
    private MerchantInfoMapper merchantInfoMapper;

    //列表
    @RequestMapping("/adminDelivMerchlist")
    public String adminDelivMerchlist(HttpServletRequest request,Integer id) {
        request.setAttribute("delivery_id",id);
        return "delivMerch/list";
    }


    @RequestMapping(value = "/adminDelivMerchlistajax")
    @ResponseBody
    public String adminDelivMerchlistajax(HttpServletRequest request, Integer draw,Integer delivery_id){
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length_number = Integer.valueOf(request.getParameter("length"));
        return adminDelivMerchService.getResultJson(start,length_number,draw,delivery_id);
    }

    //添加
    @RequestMapping("/adminDelivMerchadd")
    public String adminDelivMerchadd(HttpServletRequest request,Integer delivery_id){
        request.setAttribute("delivery_id",delivery_id);
        request.setAttribute("mi",merchantInfoMapper.selectAll());
        return "delivMerch/edit";
    }

    //编辑or添加
    @RequestMapping("/adminDelivMerchedit")
    public String adminDelivMerchedit(HttpServletRequest request,Integer id){
        request.setAttribute("dm",adminDelivMerchService.findPojoById(id));
        request.setAttribute("id",id);
        return "delivMerch/edit";
    }
    //编辑
    @RequestMapping(value = "/adminDelivMercheditajax")
    @ResponseBody
    public Map adminDelivMercheditajax(Integer id, Integer delivery_id,Integer merchant_id,String merchant_name){
        return adminDelivMerchService.edit(id,delivery_id,merchant_id,merchant_name);
    }

    //删除
    @RequestMapping(value = "/admdeleteDelivMerchajax")
    @ResponseBody
    public Map admdeleteDelivMerchajax(Integer id) {
        return adminDelivMerchService.daletePojo(id);
    }


}
