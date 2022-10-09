package com.trjst.controller.api;

import com.trjst.model.Checklist;
import com.trjst.service.api.ChecklistService;
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
@Api(tags="个人清单")
@RequestMapping("/check")
@Slf4j
public class CheckListController {

    @Autowired
    private ChecklistService checklistService;

    @ApiOperation(value = "清单列表", notes = "清单列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Checklist> getCheckList(@ApiParam(value = "user_id", required = true) @RequestParam("user_id") Integer user_id) throws IOException {
        return checklistService.selectByUserId(user_id);
    }

    @ApiOperation(value = "添加", notes = "添加")
    @RequestMapping(value = "/addCheckList", method = RequestMethod.POST)
    public Map addCheckList(@RequestBody @Valid Checklist req) throws IOException {
        int num = checklistService.addCheckList(req);
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

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/updateCheckList", method = RequestMethod.POST)
    public Map updateCheckList(@RequestBody @Valid Checklist req) throws IOException {
        int num = checklistService.updateCheckList(req);
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

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delCheckList", method = RequestMethod.GET)
    public Map delCheckList(@ApiParam(value = "id", required = true) @RequestParam("id") Integer id) throws IOException {
        int num = checklistService.delCheckList(id);
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
}
