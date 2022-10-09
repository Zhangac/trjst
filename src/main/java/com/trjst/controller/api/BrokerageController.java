package com.trjst.controller.api;

import com.trjst.model.Brokerage;
import com.trjst.service.api.BrokerageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

}
