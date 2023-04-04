package com.trjst.controller.api;

import com.trjst.mapper.UserMapper;
import com.trjst.model.User;
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
@Api(tags="会员相关接口")
@RequestMapping("/vip")
@Slf4j
public class IsVipController {

    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "获取所以配送员和业务员 2业务员 3配送员", notes = "获取所以配送员和业务员  2业务员 3配送员")
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public List<User> getList(@ApiParam(value = "type", required = true) @RequestParam("type") Integer type) throws IOException {
        return userMapper.selectByIsMech(type);
    }

}
