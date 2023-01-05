package com.trjst.controller.api;

import com.trjst.model.Brokerage;
import com.trjst.service.api.BrokerageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * zac
 * */
@RestController
@Api(tags="入驻费用")
@RequestMapping("/brokerage")
@Slf4j
public class BrokerageController {

    @Autowired
    private BrokerageService brokerageService;

    @ApiOperation(value = "列表", notes = "列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Brokerage> brokerageList() throws IOException {
        return brokerageService.brokerageList();
    }

    @ApiOperation(value = "根据type查询1普通商户 2配送员 3酒店 4药店 5超市 6会员 7业务员等", notes = "根据type查询1普通商户 2配送员 3酒店 4药店 5超市 6会员 7业务员等")
    @RequestMapping(value = "/listType", method = RequestMethod.GET)
    public List<Brokerage> brokerageListType(@ApiParam(value = "type", required = true) @RequestParam("type") Integer type) throws IOException {
        return brokerageService.brokerageListType(type);
    }

}
