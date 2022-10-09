package com.trjst.controller.api;

import com.trjst.mapper.BrokerageMapper;
import com.trjst.model.Brokerage;
import com.trjst.model.MerchantInfo;
import com.trjst.service.api.MerchantInfoService;
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
@Api(tags="商户")
@RequestMapping("/merchantInfo")
@Slf4j
public class MerchantInfoController {

    @Autowired
    private MerchantInfoService merchantInfoService;

    @Autowired
    private BrokerageMapper brokerageMapper;

    @ApiOperation(value = "商户模糊搜索", notes = "商户模糊搜索")
    @RequestMapping(value = "/merchant_name", method = RequestMethod.GET)
    public List<MerchantInfo> merchantInfoByName(@ApiParam(value = "merchant_name", required = true) @RequestParam("merchant_name") String merchant_name,
                                                 @ApiParam(value = "area_id", required = true) @RequestParam("area_id") Integer area_id) throws IOException {
        return merchantInfoService.merchantInfoByName(merchant_name,area_id);
    }

    @ApiOperation(value = "merchantId获取商户信息", notes = "merchantId获取商户信息")
    @RequestMapping(value = "/merchantId", method = RequestMethod.GET)
    public MerchantInfo merchantInfoById(@ApiParam(value = "商户id", required = true) @RequestParam("merchantId") Integer merchantId) throws IOException {
        return merchantInfoService.merchantInfoById(merchantId);
    }

    @ApiOperation(value = "获取个人商户信息", notes = "获取个人商户信息")
    @RequestMapping(value = "/userId", method = RequestMethod.GET)
    public MerchantInfo merchantInfoByUserId(@ApiParam(value = "用户id", required = true) @RequestParam("userId") Integer userId) throws IOException {
        return merchantInfoService.merchantInfoByUserId(userId);
    }

    @ApiOperation(value = "根据市场id获取商户列表", notes = "根据市场id获取商户列表")
    @RequestMapping(value = "/market_id", method = RequestMethod.GET)
    public List<MerchantInfo> selectByMarketId(@ApiParam(value = "market_id", required = true) @RequestParam("market_id") Integer market_id) throws IOException {
        return merchantInfoService.selectByMarketId(market_id);
    }

    @ApiOperation(value = "成为商户", notes = "成为商户")
    @RequestMapping(value = "/addMerchantInfo", method = RequestMethod.POST)
    public Map addMerchantInfo(@RequestBody @Valid MerchantInfo req) throws IOException {
        return merchantInfoService.addMerchantInfo(req);
    }

    @ApiOperation(value = "修改商户信息", notes = "修改商户信息")
    @RequestMapping(value = "/updateCommodity", method = RequestMethod.POST)
    public Map updateCommodity(@RequestBody @Valid MerchantInfo req) throws IOException {
        int num = merchantInfoService.updateMerchantInfo(req);
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


    @ApiOperation(value = "获取所有入驻类型（1普通商户 2配送员 3酒店 4药店 5超市等）", notes = "获取所有入驻类型")
    @RequestMapping(value = "/brokerageList", method = RequestMethod.GET)
    public List<Brokerage> selectByMarketId() throws IOException {
        return brokerageMapper.selectByAll();
    }
}
