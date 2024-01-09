package com.trjst.controller.admin;

import com.trjst.mapper.DeliveryMapper;
import com.trjst.model.JstOrder;
import com.trjst.service.admin.AdminOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class AdminOrderController {

    @Autowired
    private AdminOrderService adminOrderService;

    @Autowired
    private DeliveryMapper deliveryMapper;

    //列表
    @RequestMapping("/adminorderlist")
    public String adminorderlist(HttpServletRequest request,Integer area) {
        request.setAttribute("area",area);
        return "order/list";
    }

    //汇总列表
    @RequestMapping("/adminordercountlist")
    public String adminordercountlist(HttpServletRequest request,Integer area) {
        request.setAttribute("area",area);
        return "order/countlist";
    }

    //汇总列表ajax
    @RequestMapping(value = "/getHzCountList")
    @ResponseBody
    public String getHzCountList(HttpServletRequest request, Integer draw,
                                 Integer area_id,String logmax,String logmin){
        log.info("area_id={}",area_id);
        log.info("logmax="+logmax);
        log.info("logmin="+logmin);
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length_number = Integer.valueOf(request.getParameter("length"));
        return adminOrderService.getHzCountList(start,length_number,draw,area_id,logmax,logmin);
    }


    @RequestMapping(value = "/adminorderlistajax")
    @ResponseBody
    public String adminorderlistajax(HttpServletRequest request, Integer draw, String order_no,
                                    Integer pay_status, String delivery_name,String logmax
                                    ,String logmin,String phone,Integer area_id){
        System.out.println("logmax="+logmax);
        System.out.println("logmin="+logmin);
        log.info("area_id={}",area_id);
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length_number = Integer.valueOf(request.getParameter("length"));
        return adminOrderService.getResultJson(start,length_number,draw,order_no,pay_status,delivery_name,logmax,logmin,phone,area_id);
    }


    //删除
    @RequestMapping(value = "/adminorderdelajax")
    @ResponseBody
    public Map adminorderdelajax(Integer id){
        return adminOrderService.daletePojo(id);
    }

    //更改配送员
    @RequestMapping("/adminggpsy")
    public String adminpsyadd(HttpServletRequest request,Integer id) {
        request.setAttribute("ids",id);
        request.setAttribute("deli",deliveryMapper.selectAll());
        return "order/ggpsy";
    }

    //更改配送员ajax
    @RequestMapping(value = "/adminggpsyajax")
    @ResponseBody
    public Map adminggpsyajax(@RequestParam(value="shids") Integer shids, @RequestParam(value="checkID[]") Integer[] checkID){
        Map map = new HashMap<>();
        try {
            List<Integer> checklist = Arrays.asList(checkID);
            JstOrder jstOrder = new JstOrder();
            jstOrder.setId(shids);
            jstOrder.setDelivery_id(checklist.get(0));
            adminOrderService.edit(jstOrder);
            map.put("code", "100");
        }catch (Exception e){
            log.error("adminpsyaddajax:{}"+e);
            map.put("code", "500");
        }
        return map;
    }
    WriteExcel excleImpl = new WriteExcel();
    // 获取url链接上的参数
    @RequestMapping(value = "/adminOrderExcel/download_excel")
    @ResponseBody
    public String dowm(HttpServletResponse response, HttpServletRequest resRequest, String order_no,
                       Integer pay_status,String delivery_name,String logmax
                        ,String logmin,String phone) {
        response.setContentType("application/binary;charset=UTF-8");
        log.info("order_no=" + order_no);
        log.info("pay_status=" + pay_status);
        log.info("delivery_name=" + delivery_name);
        String order = resRequest.getParameter("order[0][column]");// 排序的列号
        String orderDir = resRequest.getParameter("order[0][dir]");// 排序的顺序asc or desc
        String orderColumn = resRequest.getParameter("columns[" + order + "][name]");// 排序的列。注意页面上的列name属性设置的名字要和表中列的名字一致，否则，会导致SQL拼接错误
        try {
            ServletOutputStream out = response.getOutputStream();
            try {
                // 设置文件头：最后一个参数是设置下载文件名(这里我们叫：张三.xls)
                response.setHeader("Content-Disposition",
                        "attachment;fileName=" + URLEncoder.encode("订单列表" + ".xls", "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
//            String[] titles = {"商品名称","订单编号","支付金额", "支付时间","下单时间", "配送员姓名",
//                    "收货人手机号", "收货人地址", "收货人名称" , "单价","数量","斤/件/箱(数)"};
            String[] titles = {"配送员","商品","收货手机号", "收货地址","详细地址", "收货名称",
                    "斤/件/箱(数)" , "数量","单价", "总价","支付金额", "支付时间","创建时间"};
            List result = adminOrderService.selectListdown(order_no,pay_status,delivery_name,logmax,logmin,phone);
            log.info(result.toString());
            excleImpl.exportOrder(titles, out, result);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "导出信息失败";
        }
    }
}
