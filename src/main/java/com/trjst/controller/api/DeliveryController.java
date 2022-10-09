package com.trjst.controller.api;

import com.trjst.model.Delivery;
import com.trjst.model.MerchantInfo;
import com.trjst.service.api.DeliveryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * zac
 * */
@RestController
@Api(tags="配送员")
@RequestMapping("/delivery")
@Slf4j
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @ApiOperation(value = "获取个人信息", notes = "获取个人信息")
    @RequestMapping(value = "/userId", method = RequestMethod.GET)
    public Delivery merchantInfoByUserId(@ApiParam(value = "用户id", required = true) @RequestParam("userId") Integer userId) throws IOException {
        return deliveryService.selectByUserId(userId);
    }

    @ApiOperation(value = "成为配送员", notes = "成为配送员")
    @RequestMapping(value = "/addDelivery", method = RequestMethod.POST)
    public Map addDelivery(@RequestBody @Valid Delivery req) throws IOException {
        return deliveryService.addDelivery(req);
    }

    @ApiOperation(value = "配送员修改", notes = "配送员修改")
    @RequestMapping(value = "/updateCommodity", method = RequestMethod.POST)
    public Map updateDelivery(@RequestBody @Valid Delivery req) throws IOException {
        int num = deliveryService.updateDelivery(req);
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
