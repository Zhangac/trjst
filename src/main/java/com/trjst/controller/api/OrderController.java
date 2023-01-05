package com.trjst.controller.api;

import com.trjst.dto.DeliveryMerGroupDto;
import com.trjst.model.JstOrder;
import com.trjst.service.api.OrderService;
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
@Api(tags="订单")
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "动态订单查询", notes = "动态订单查询")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public List<JstOrder> orderList(@RequestBody @Valid JstOrder req) throws IOException {
        return orderService.orderList(req);
    }

    @ApiOperation(value = "添加订单(包含购物车多下单)", notes = "添加订单(包含购物车多下单)")
    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public Map addOrder(@RequestBody @Valid List<JstOrder> req) throws Exception {
        return orderService.addOrderList(req);
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/updateOrder", method = RequestMethod.POST)
    public Map updateOrder(@RequestBody @Valid JstOrder req) throws IOException {
        int num = orderService.updateOrder(req);
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
    @RequestMapping(value = "/delOrder", method = RequestMethod.GET)
    public Map delOrder(@ApiParam(value = "id", required = true) @RequestParam("id") Integer id) throws IOException {
        Map map = new HashMap();
        try {
            orderService.delOrder(id);
            map.put("code",200);
            map.put("msg","success");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","error");
        }
        return map;
    }

    @ApiOperation(value = "确认收货", notes = "确认收货")
    @RequestMapping(value = "/confirmReceipt", method = RequestMethod.GET)
    public Map confirmReceipt(@ApiParam(value = "订单id", required = true) @RequestParam("id") Integer id) throws Exception {
        return orderService.confirmReceipt(id);
    }

    @ApiOperation(value = "发货", notes = "发货")
    @RequestMapping(value = "/fahuo", method = RequestMethod.GET)
    public Map fahuo(@ApiParam(value = "订单id", required = true) @RequestParam("id") Integer id) throws Exception {
        return orderService.fahuo(id);
    }

    @ApiOperation(value = "配送员点击送达接口", notes = "配送员点击送达接口")
    @RequestMapping(value = "/arrived", method = RequestMethod.GET)
    public Map arrived(@ApiParam(value = "订单id", required = true) @RequestParam("id") Integer id) throws Exception {
        return orderService.arrived(id);
    }

    @ApiOperation(value = "商户订单统计周月年", notes = "商户订单统计周月年")
    @RequestMapping(value = "/getMerchantCount", method = RequestMethod.GET)
    public Map getMerchantCount(@ApiParam(value = "商户id", required = true) @RequestParam("merchantId") Integer merchantId) throws Exception {
        return orderService.getMerchantCount(merchantId);
    }

    @ApiOperation(value = "配送员 以商户分组订单列表查询", notes = "配送员 以商户分组订单列表查询")
    @RequestMapping(value = "/deliveryMerGroup", method = RequestMethod.GET)
    public List<DeliveryMerGroupDto> deliveryMerGroup(@ApiParam(value = "配送员id", required = true) @RequestParam("deliveryId") Integer deliveryId,
                                                      @ApiParam(value = "订单类型", required = true) @RequestParam("type") Integer type) throws Exception {
        return orderService.deliveryMerGroup(deliveryId,type);
    }

    @ApiOperation(value = "用户退款", notes = "用户退款")
    @RequestMapping(value = "/userRefund", method = RequestMethod.GET)
    public Map userRefund(@ApiParam(value = "订单id", required = true) @RequestParam("id") Integer id) throws Exception {
        return orderService.userRefund(id);
    }

    @ApiOperation(value = "商户确认退款", notes = "商户确认退款")
    @RequestMapping(value = "/confirmRefund", method = RequestMethod.GET)
    public Map confirmRefund(@ApiParam(value = "订单id", required = true) @RequestParam("id") Integer id) throws Exception {
        return orderService.confirmRefund(id);
    }

}
