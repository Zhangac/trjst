package com.trjst.controller.api;

import com.trjst.model.ShoppingCart;
import com.trjst.service.api.ShoppingCartService;
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
@Api(tags="购物车")
@RequestMapping("/shoppingCart")
@Slf4j
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @ApiOperation(value = "个人购物车列表", notes = "个人购物车列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<ShoppingCart> shoppingCartList(@ApiParam(value = "用户id", required = true) @RequestParam("userId") Integer userId) throws IOException {
        return shoppingCartService.selectByUserId(userId);
    }

    @ApiOperation(value = "添加", notes = "添加")
    @RequestMapping(value = "/addShoppingCart", method = RequestMethod.POST)
    public Map addShoppingCart(@RequestBody @Valid ShoppingCart req) throws IOException {
        return shoppingCartService.addShoppingCart(req);
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/updateShoppingCart", method = RequestMethod.POST)
    public Map updateShoppingCart(@RequestBody @Valid ShoppingCart req) throws IOException {
        int num = shoppingCartService.updateShoppingCart(req);
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
    @RequestMapping(value = "/delShoppingCart", method = RequestMethod.GET)
    public Map delShoppingCart(@ApiParam(value = "id", required = true) @RequestParam("id") int[] ids) throws IOException {
        System.out.println(ids);
        Map map = new HashMap();
        try {
            for(Integer id : ids){
                shoppingCartService.delShoppingCart(id);
            }
            map.put("code",200);
            map.put("msg","success");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","error");
        }
        return map;
    }
}
