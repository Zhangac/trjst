package com.trjst.controller.api;

import com.trjst.model.Withdraw;
import com.trjst.model.WithdrawSave;
import com.trjst.service.api.WithdrawService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * zac
 * */
@RestController
@Api(tags="提现")
@RequestMapping("/withdraw")
@Slf4j
public class WithdrawController {

    @Autowired
    private WithdrawService withdrawService;

    @ApiOperation(value = "个人提现记录", notes = "个人提现记录")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Withdraw> withdrawByUserId(@ApiParam(value = "用户id", required = true) @RequestParam("userId") Integer userId) throws IOException {
        return withdrawService.withdrawByUserId(userId);
    }

    @ApiOperation(value = "申请提现", notes = "申请提现")
    @RequestMapping(value = "/addwithdraw", method = RequestMethod.POST)
    public Map addWithdraw(@RequestBody @Valid Withdraw req) throws IOException {
        return withdrawService.addWithdraw(req);
    }

    @ApiOperation(value = "用户提现信息查询", notes = "用户提现信息查询")
    @RequestMapping(value = "/userId", method = RequestMethod.GET)
    public WithdrawSave withdrawSaveByUserId(@ApiParam(value = "用户id", required = true) @RequestParam("userId") Integer userId) throws IOException {
        return withdrawService.withdrawSaveUserId(userId);
    }

    @ApiOperation(value = "提现信息保存", notes = "提现信息保存")
    @RequestMapping(value = "/withdrawSave", method = RequestMethod.POST)
    public Map withdrawSave(@RequestBody @Valid WithdrawSave req) throws IOException {
        return withdrawService.withdrawSave(req);
    }

    @ApiOperation(value = "提现信息修改", notes = "提现信息修改")
    @RequestMapping(value = "/withdrawUpdate", method = RequestMethod.POST)
    public Map withdrawUpdate(@RequestBody @Valid WithdrawSave req) throws IOException {
        return withdrawService.withdrawUpdate(req);
    }
}
