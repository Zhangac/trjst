package com.trjst.controller.api;

import com.trjst.mapper.SpendRecordMapper;
import com.trjst.model.SpendRecord;
import com.trjst.service.api.SpendRecordService;
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
import java.util.Map;

/**
 * zac
 * */
@RestController
@Api(tags="消费记录")
@RequestMapping("/spendRecord")
@Slf4j
public class SpendRecordController {

    @Autowired
    private SpendRecordService spendRecordService;

    @Autowired
    private SpendRecordMapper spendRecordMapper;

    @ApiOperation(value = "用户消费记录列表", notes = "用户消费记录列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<SpendRecord> spendRecordList(@ApiParam(value = "user_id", required = true) @RequestParam("user_id") Integer user_id) throws IOException {
        return spendRecordService.selectByUserId(user_id);
    }

    @ApiOperation(value = "count", notes = "count")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Map count(@ApiParam(value = "userId", required = true) @RequestParam("userId") Integer userId) throws IOException {
        return spendRecordService.getStrangeNumCurWeek(userId);
    }

    @ApiOperation(value = "count2", notes = "count")
    @RequestMapping(value = "/count2", method = RequestMethod.GET)
    public SpendRecord count2(@ApiParam(value = "order_id", required = true) @RequestParam("order_id") String order_id) throws IOException {
        SpendRecord srd = spendRecordMapper.selectByOrderId(order_id);
        if(srd!=null) {
            log.info("已添加消费记录,重复的体现");
        }else {
            log.info("消费记录没有填加,此时可以添加");
        }
        return srd;
    }
}
