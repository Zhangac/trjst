package com.trjst.controller.admin;

import com.trjst.mapper.DelivMerchMapper;
import com.trjst.mapper.DeliveryMapper;
import com.trjst.model.DelivMerch;
import com.trjst.model.MerchantInfo;
import com.trjst.service.admin.AdminDelivMerchService;
import com.trjst.service.admin.AdminDeliveryService;
import com.trjst.service.admin.AdminMerchantInfoService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@Slf4j
public class AdminMerchantInfoController {

    @Autowired
    private AdminMerchantInfoService adminMerchantInfoService;

    @Autowired
    private DeliveryMapper deliveryMapper;

    @Autowired
    private DelivMerchMapper delivMerchMapper;

    @Autowired
    private AdminDelivMerchService adminDelivMerchService;


    //设置配送员
    @RequestMapping("/adminpsyadd")
    public String adminpsyadd(HttpServletRequest request,int[] ids) {
        List list = new ArrayList();
        for (int id :ids){
            System.out.println(id);
            list.add(id);
        }
        log.info("ids:{}"+ids);
        request.setAttribute("ids",list);
        request.setAttribute("deli",deliveryMapper.selectAll());
        return "merchantinfo/add";
    }

    //设置配送员ajax
    @RequestMapping(value = "/adminpsyaddajax")
    @ResponseBody
    //public Map adminpsyaddajax(String shids, String checkID){
    public Map adminpsyaddajax(@RequestParam(value="shids") String shids, @RequestParam(value="checkID[]") Integer[] checkID){
        Map map = new HashMap<>();
        try {
            String as = shids.substring(1, shids.length()-1);
            List<String> idList = Arrays.asList(as.split(","));
            List<Integer> checklist = Arrays.asList(checkID);
            for(String ids :idList){
                log.info("ids:{}"+ids);
                MerchantInfo mi = adminMerchantInfoService.findPojoById(Integer.parseInt(ids.trim()));
                mi.setDelivery_id(checklist.get(0));
                adminMerchantInfoService.edit2(mi);

                DelivMerch delivMerch = delivMerchMapper.selectByMerId(mi.getId());

                DelivMerch d = new DelivMerch();
                if(delivMerch!=null){
                    d.setId(delivMerch.getId());
                }
                d.setMerchant_name(mi.getMerchant_name());
                d.setMerchant_id(Integer.parseInt(ids.trim()));
                d.setDelivery_id(checklist.get(0));
                adminDelivMerchService.edit(d.getId(),d.getDelivery_id(),d.getMerchant_id(),d.getMerchant_name());
            }
            map.put("code", "100");
        }catch (Exception e){
            log.error("adminpsyaddajax:{}"+e);
            map.put("code", "500");
        }
        return map;
    }

    //列表
    @RequestMapping("/adminmilist")
    public String adminmilist(HttpServletRequest request) {
        return "merchantinfo/list";
    }

    //列表
    @RequestMapping("/adminmiaslist")
    public String adminmiaslist(HttpServletRequest request,Integer audit_status) {
        request.setAttribute("audit_status",audit_status);
        return "merchantinfo/auditstatuslist";
    }


    @RequestMapping(value = "/adminmilistajax")
    @ResponseBody
    public String adminmilistajax(HttpServletRequest request, Integer draw, String merchant_name,
                                  Integer audit_status,Integer status,Integer is_hot){
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length_number = Integer.valueOf(request.getParameter("length"));
        return adminMerchantInfoService.getResultJson(start,length_number,draw,merchant_name,audit_status,status,is_hot);
    }

    //编辑
    @RequestMapping("/adminmiedit")
    public String adminmiedit(HttpServletRequest request,Integer id){
        request.setAttribute("mi",adminMerchantInfoService.findPojoById(id));
        request.setAttribute("id",id);
        return "merchantinfo/edit";
    }

    //审核
    @RequestMapping(value = "/adminmieditajax")
    @ResponseBody
    public Map adminmieditajax(Integer id, Integer audit_status){
        audit_status = 1;
        return adminMerchantInfoService.edit(id,audit_status);
    }

    //上下架
    @RequestMapping(value = "/adminmixjajax")
    @ResponseBody
    public Map adminmixjajax(Integer id, Integer status){
        return adminMerchantInfoService.xiajia(id,status);
    }


    //推荐
    @RequestMapping(value = "/adminmitjajax")
    @ResponseBody
    public Map adminmitjajax(Integer id, Integer is_hot){
        return adminMerchantInfoService.tuijian(id,is_hot);
    }

    //是否审核0不是 1是  是否不交入住费用就可以上传商品 可以显示但不能进行交易
    @RequestMapping(value = "/adminshowajax")
    @ResponseBody
    public Map adminshowajax(Integer id, Integer is_show){
        return adminMerchantInfoService.isshow(id,is_show);
    }

    //del
    @RequestMapping(value = "/adminmidelajax")
    @ResponseBody
    public Map adminmidelajax(Integer id){
        return adminMerchantInfoService.daletePojo(id);
    }

}
