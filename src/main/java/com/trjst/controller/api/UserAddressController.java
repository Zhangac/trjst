package com.trjst.controller.api;

import com.trjst.model.UserAddress;
import com.trjst.service.api.UserAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(tags="用户订单地址")
@RequestMapping("/userAddress")
@Slf4j
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @ApiOperation(value = "获取用户上一次地址", notes = "获取用户上一次地址")
    @RequestMapping(value = "/getUserAddress", method = RequestMethod.GET)
    public UserAddress spendRecordList(@ApiParam(value = "user_id", required = true) @RequestParam("user_id") Integer user_id) throws IOException {
        List<UserAddress> list = userAddressService.selectByUserId(user_id);
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @ApiOperation(value = "添加", notes = "添加")
    @RequestMapping(value = "/addUserAddress", method = RequestMethod.POST)
    public Map addUser(@RequestBody @Valid UserAddress req) throws IOException {
        int num = userAddressService.addUserAddress(req);
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

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/updateUserAddress", method = RequestMethod.POST)
    public Map updateUser(@RequestBody @Valid UserAddress req) throws IOException {
        int num = userAddressService.updateUserAddress(req);
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

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delUserAddress", method = RequestMethod.GET)
    public Map delCollect(@ApiParam(value = "id", required = true) @RequestParam("id") Integer id) throws IOException {
        int num = userAddressService.delUserAddress(id);
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
}
