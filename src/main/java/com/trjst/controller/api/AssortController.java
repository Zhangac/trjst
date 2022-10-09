package com.trjst.controller.api;

import com.trjst.mapper.AssortMapper;
import com.trjst.model.Assort;
import com.trjst.service.api.AssortService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * zac
 * */
@RestController
@Api(tags="分类")
@RequestMapping("/assort")
@Slf4j
public class AssortController {

    @Autowired
    private AssortService assortService;

    @Autowired
    private AssortMapper assortMapper;

    @Async
    @ApiOperation(value = "分类列表", notes = "分类列表")
    @RequestMapping(value = "/aa", method = RequestMethod.GET)
    public Assort aa() throws IOException {
        return assortMapper.selectByPrimaryKey(1);
    }

    @ApiOperation(value = "分类列表", notes = "分类列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Assort> demo() throws IOException {
        return assortService.listAssort();
    }

    @Async
    @ApiOperation(value = "分类列表", notes = "分类列表")
    @RequestMapping(value = "/a", method = RequestMethod.GET)
    public String a() throws IOException {
        return "success";
    }
}
