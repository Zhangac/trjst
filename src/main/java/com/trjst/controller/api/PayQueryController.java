package com.trjst.controller.api;

import com.trjst.service.api.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;

/**
 * pay
 */
@Controller
@Api(description = "小程序支付回调")
public class PayQueryController {

    @Autowired
    private PayService payService;

    @RequestMapping( value = "/wxpayquery")
    @ApiOperation(value = "微信支付成功回调接口")
    public void wxpayquery(HttpServletRequest request, HttpServletResponse response)throws Exception{
        String resXml = "";
        JSONObject json = payService.wxpaysuccess(request,response);
        String info = json.getString("code");
        if(info.equals("100")){
            resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                    + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
        }
        BufferedOutputStream out = new BufferedOutputStream(
                response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/wxpayquery2")
    @ApiOperation(value = "微信差价支付成功回调接口")
    public void wxpayquery2(HttpServletRequest request, HttpServletResponse response)throws Exception{
        String resXml = "";
        JSONObject json = payService.wxpaysuccess2(request,response);
        String info = json.getString("code");
        if(info.equals("100")){
            resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                    + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
        }
        BufferedOutputStream out = new BufferedOutputStream(
                response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }
}
