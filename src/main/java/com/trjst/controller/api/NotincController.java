package com.trjst.controller.api;

import com.trjst.dto.WxSendDto;
import com.trjst.service.api.PayService;
import com.trjst.util.HttpClientTool;
import com.trjst.util.HttpClientUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * pay
 */
@Slf4j
@RestController
@Api(description = "微信消息推送")
public class NotincController {

    @Autowired
    private PayService payService;


    @RequestMapping( value = "/wxsend",method = {RequestMethod.POST}, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "微信消息推送")
    public JSONObject wxsend(@RequestBody WxSendDto dto, HttpServletRequest request, HttpServletResponse resp)throws Exception{
        JSONObject jsonObject = JSONObject.fromObject(dto);
        System.out.println(jsonObject);
        //String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token="+dto.getAccess_token();
        //String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+dto.getAccess_token();
        //String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="+dto.getAccess_token();
        String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token="+dto.getAccess_token();
        JSONObject result = HttpClientTool.httpsRequest(url,"POST",jsonObject.toString());
        log.info(result.toString());
        return result;
    }

    @RequestMapping( value = "/token",method = RequestMethod.GET)
    @ApiOperation(value = "token")
    public String wxPaySpread(HttpServletRequest request, HttpServletResponse resp,
                           @ApiParam(name = "grant_type", value = "grant_type", required = true)
                           @RequestParam(value = "grant_type") String grant_type,
                           @ApiParam(name = "appid", value = "appid", required = true)
                           @RequestParam(value = "appid") String appid,
                           @ApiParam(name = "secret", value = "secret", required = true)
                           @RequestParam(value = "secret") String secret)throws Exception{
        Map map = new HashMap();
        map.put("grant_type",grant_type);
        map.put("appid",appid);
        map.put("secret",secret);
        log.info(map.toString());
        String result = HttpClientUtil.doGet("https://api.weixin.qq.com/cgi-bin/token",map);
        log.info(result);
        return result;
    }
}
