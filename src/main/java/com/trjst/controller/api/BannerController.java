package com.trjst.controller.api;

import com.trjst.model.Banner;
import com.trjst.service.api.BannerService;
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
@Api(tags="轮播")
@RequestMapping("/banner")
@Slf4j
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @ApiOperation(value = "轮播列表", notes = "轮播列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Banner> listBanner() throws IOException {
        return bannerService.listBanner();
    }
}
