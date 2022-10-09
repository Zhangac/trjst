package com.trjst.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.trjst.mapper.UserMapper;
import com.trjst.model.User;
import com.trjst.service.api.UserService;
import com.trjst.util.HttpClientUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * zac
 * */
@RestController
@Api(tags="用户")
@RequestMapping("/user")
@Slf4j
public class UserController {

    // 1.剩余功能：优惠券、积分、积分赠送、邀请、订单/商户等/配送员报表问题
    // 2.个人余额消费（是否设置支付密码）、线上提现问题
    // 3.酒店、KTV等核销码问题(可以放放，是线下协商收货还是扫码核销)

    @Value("${wx.url}")
    private String url;

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.secret}")
    private String secret;

    @Value("${wx.grant_type}")
    private String grant_type;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "获取个人信息", notes = "获取个人信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Map userInfo(@ApiParam(value = "用户id", required = true) @RequestParam("userId") Integer userId) throws IOException {
        User user =  userService.userById(userId);
        Map map = new HashMap();
        if(user != null){
            map.put("code",200);
            map.put("msg","success");
            map.put("data",user);
        }else {
            map.put("code",500);
            map.put("msg","error");
            map.put("data",null);
        }
        return map;
    }

    @ApiOperation(value = "添加", notes = "添加")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public Map addUser(@RequestBody @Valid User req) throws IOException {
        return userService.addUser(req);
    }

    @ApiOperation(value = "通用修改", notes = "通用修改")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public Map updateUser(@RequestBody @Valid User req) throws IOException {
        int num = userService.updateUser(req);
        Map map = new HashMap();
        if(num > 0){
            map.put("code",200);
            map.put("msg","success");
        }else {
            map.put("code",500);
            map.put("msg","error");
        }
        return map;
    }

    @ApiOperation(value = "获取openid", notes = "获取openid")
    @RequestMapping(value = "/openid", method = RequestMethod.GET)
    public Map getOpenId(@ApiParam(value = "code", required = true) @RequestParam("code") String code) {
        log.info("wxLogin - code: " + code);
        Map map = new HashMap();
        try {
            Map<String, String> param = new HashMap<>();
            param.put("appid", appid);
            param.put("secret", secret);
            param.put("js_code", code);
            param.put("grant_type", grant_type);
            String wxResult = HttpClientUtil.doGet(url, param);

            JSONObject json = JSONObject.parseObject(wxResult);
            log.info("json:{}"+json);
            if(json!=null && !json.equals("")){
                String openid = json.getString("openid");
                log.info("openid:{}"+openid);
                if(openid!=null && !openid.equals("")){
                    List<User> users = userMapper.userByAll();
                    for (User u : users) {
                        if (openid.equals(u.getOpen_id())) {
                            map.put("code", 200);
                            map.put("msg", JSONObject.parseObject(wxResult));
                            map.put("status", 1);
                            return map;
                        }
                    }
                }
            }

            map.put("code", 200);
            map.put("msg", JSONObject.parseObject(wxResult));
            map.put("status", 0);
            return map;
        } catch (Exception e) {
            map.put("code", 500);
            map.put("msg", "error");
            log.error("获取openid:{}"+e);
            return map;
        }
    }
}
