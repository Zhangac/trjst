package com.trjst.controller.api;

import cn.hutool.core.util.XmlUtil;
import com.trjst.service.api.PayService;
import com.trjst.vo.OrderPay;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * pay
 */
@RestController
@Api(description = "小程序支付")
public class PayController {

    @Autowired
    private PayService payService;

    @RequestMapping( value = "/wxpay",method = RequestMethod.POST)
    @ApiOperation(value = "微信订单支付接口（含合并支付type1基础订单 2充值订单 3入驻押金订单）")
    public Map wxpay(@RequestBody OrderPay req, HttpServletRequest request, HttpServletResponse resp)throws Exception{
        return payService.wxpaymoney(request,resp,req);
    }

    @RequestMapping( value = "/wxPaySpread2",method = RequestMethod.POST)
    @ApiOperation(value = "微信订单差价支付含合并支付（新）")
    public Map wxPaySpread2(@RequestBody OrderPay req, HttpServletRequest request, HttpServletResponse resp)throws Exception{
        return payService.wxPaySpread2(request,resp,req);
    }

    @RequestMapping( value = "/wxPaySpread",method = {RequestMethod.GET}, produces = "application/json; charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "微信差价支付接口（老）")
    public Map wxPaySpread(HttpServletRequest request, HttpServletResponse resp,
                           @ApiParam(name = "order_id", value = "订单id", required = true)
                           @RequestParam(value = "order_id") Integer order_id,
                           @ApiParam(name = "money", value = "支付金额", required = true)
                           @RequestParam(value = "money") BigDecimal money,
                           @ApiParam(name = "jin_num", value = "多少斤/多少件/多少箱等", required = true)
                           @RequestParam(value = "jin_num") BigDecimal jin_num)throws Exception{
        return payService.wxPaySpread(request,resp,order_id,money,jin_num);
    }

    /*@RequestMapping( value = "/wxpayquery",method = {RequestMethod.POST}, produces = "application/json; charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "微信支付成功回调接口")
    public void wxpayquery(HttpServletRequest request, HttpServletResponse resp)throws Exception{
        Map map = new HashMap();
        JSONObject json = payService.wxpaysuccess(request,resp);
        String info = json.getString("code");
        if(info.equals("100")){
            map.put("return_code", "SUCCESS");
            map.put("return_msg", "OK");
        }
        PrintWriter writer = resp.getWriter();
        writer.write(XmlUtil.mapToXmlStr(map, "xml"));
        writer.flush();
        writer.close();
    }

    @RequestMapping( value = "/wxpayquery2",method = {RequestMethod.POST}, produces = "application/json; charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "微信差价支付成功回调接口")
    public void wxpayquery2(HttpServletRequest request, HttpServletResponse resp)throws Exception{
        Map map = new HashMap();
        JSONObject json = payService.wxpaysuccess2(request,resp);
        String info = json.getString("code");
        if(info.equals("100")){
            map.put("return_code", "SUCCESS");
            map.put("return_msg", "OK");
        }
        PrintWriter writer = resp.getWriter();
        writer.write(XmlUtil.mapToXmlStr(map, "xml"));
        writer.flush();
        writer.close();
    }*/
}
