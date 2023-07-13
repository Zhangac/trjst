package com.trjst.controller.api;

import com.trjst.model.CommodityInfo;
import com.trjst.service.api.CommodityInfoService;
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
@Api(tags="商品")
@RequestMapping("/commodity")
@Slf4j
public class CommodityInfoController {

    @Autowired
    private CommodityInfoService commodityInfoService;

    @ApiOperation(value = "商品动态列表", notes = "商品动态列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public List<CommodityInfo> commodityList(@RequestBody @Valid CommodityInfo req) throws IOException {
        return commodityInfoService.commodityInfoList(req);
    }

    @ApiOperation(value = "商品动态列表2", notes = "商品动态列表2")
    @RequestMapping(value = "/list2", method = RequestMethod.POST)
    public List<CommodityInfo> commodityList2(@RequestBody @Valid CommodityInfo req) throws IOException {
        return commodityInfoService.commodityInfoList2(req);
    }

    @ApiOperation(value = "添加", notes = "添加")
    @RequestMapping(value = "/addCommodity", method = RequestMethod.POST)
    public Map addCommodity(@RequestBody @Valid CommodityInfo req) throws IOException {
        int num = commodityInfoService.addCommodityInfo(req);
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

    @ApiOperation(value = "修改(上下架通用)", notes = "修改(上下架通用)")
    @RequestMapping(value = "/updateCommodity", method = RequestMethod.POST)
    public Map updateCommodity(@RequestBody @Valid CommodityInfo req) throws IOException {
        int num = commodityInfoService.updateCommodityInfo(req);
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

    @ApiOperation(value = "上下架", notes = "上下架")
    @RequestMapping(value = "/onStatus", method = RequestMethod.POST)
    public Map onStatus(@RequestBody @Valid CommodityInfo req) throws IOException {
        return commodityInfoService.onStatus(req);
    }


    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delCommodity", method = RequestMethod.GET)
    public Map delCommodity(@ApiParam(value = "id", required = true) @RequestParam("id") Integer id) throws IOException {
        int num = commodityInfoService.delCommodityInfo(id);
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

    @ApiOperation(value = "单规格删除", notes = "单规格删除")
    @RequestMapping(value = "/delSpeci", method = RequestMethod.GET)
    public Map delSpeci(@ApiParam(value = "id", required = true) @RequestParam("id") Integer id) throws IOException {
        int num = commodityInfoService.delSpeci(id);
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
