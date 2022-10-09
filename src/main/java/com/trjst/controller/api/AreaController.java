package com.trjst.controller.api;

import com.trjst.model.Area;
import com.trjst.service.api.AreaService;
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
@Api(tags="五区一市")
@RequestMapping("/area")
@Slf4j
public class AreaController {

    @Autowired
    private AreaService areaService;

    @ApiOperation(value = "五区一市列表", notes = "五区一市列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Area> demo() throws IOException {
        return areaService.listArea();
    }
}
