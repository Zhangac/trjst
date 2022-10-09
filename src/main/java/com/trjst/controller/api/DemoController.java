package com.trjst.controller.api;

import com.trjst.dto.AdminDto;
import com.trjst.mapper.DemoMapper;
import com.trjst.model.Admin;
import com.trjst.model.Demo;
import com.trjst.service.api.DemoService;
import com.trjst.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

/**
 * zac
 * */
@RestController
@Api(tags="test测试")
@RequestMapping("/test")
@Slf4j
public class DemoController {

    @Autowired
    private DemoService demoService;

    @Autowired
    private DemoMapper demoMapper;

    @ApiOperation(value = "测试", notes = "测试")
    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public Demo demo() throws IOException {
        return demoMapper.selectByPrimaryKey(1);
    }

    @ApiOperation(value = "测试2", notes = "测试2")
    @RequestMapping(value = "/demo2", method = RequestMethod.GET)
    public int demo2() throws Exception {
        return  demoService.demo2();
    }

    @ApiOperation(value = "测试2", notes = "测试2")
    @RequestMapping(value = "/demo2", method = RequestMethod.POST)
    public Admin demo2(@ApiParam(value = "账号", required = true) @RequestParam("account") String account) throws IOException {
        Admin ae = new Admin();
        ae.setAccount(account);
        Admin a = demoService.aa(ae);
        log.info("aaResp : {}", a);
        return a;
    }

    @PostMapping("/getDemoPage")
    @ApiOperation("分页获取")
    public R getDemoPage(@RequestBody @Valid AdminDto af) {
        return demoService.getAdminPage(af);
    }
}
