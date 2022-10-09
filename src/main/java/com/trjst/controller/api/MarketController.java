package com.trjst.controller.api;

import com.trjst.model.Market;
import com.trjst.service.api.MarketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * zac
 * */
@RestController
@Api(tags="市场")
@RequestMapping("/market")
@Slf4j
public class MarketController {

    @Autowired
    private MarketService marketService;

    @ApiOperation(value = "列表", notes = "列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public List<Market> commodityList(@RequestBody @Valid Market req) throws IOException {
        return marketService.marketList(req);
    }

}
