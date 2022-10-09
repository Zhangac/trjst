package com.trjst.controller.api;

import com.trjst.model.Collect;
import com.trjst.service.api.CollectService;
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
@Api(tags="收藏")
@RequestMapping("/collect")
@Slf4j
public class CollectController {

    @Autowired
    private CollectService collectService;

    @ApiOperation(value = "收藏分类列表", notes = "收藏分类列表")
    @RequestMapping(value = "/caList", method = RequestMethod.GET)
    public List<Collect> collectAssortList(@ApiParam(value = "用户id", required = true) @RequestParam("userId") Integer userId) throws IOException {
        return collectService.collectAssortList(userId);
    }

    @ApiOperation(value = "收藏分类商品列表", notes = "收藏分类商品列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Collect> collectList(@ApiParam(value = "用户id", required = true) @RequestParam("userId") Integer userId/*,
                                     @ApiParam(value = "分类id(暂时不用)") @RequestParam("assort_id") Integer assort_id*/) throws IOException {
        return collectService.collectList(userId,null);
    }

    @ApiOperation(value = "添加（只传user_id和commodity_id）", notes = "添加（只传user_id和commodity_id）")
    @RequestMapping(value = "/addCollect", method = RequestMethod.POST)
    public Map addCollect(@RequestBody @Valid Collect req) throws IOException {
        return collectService.addCollect(req);
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delCollect", method = RequestMethod.GET)
    public Map delCollect(@ApiParam(value = "id", required = true) @RequestParam("id") Integer id) throws IOException {
        int num = collectService.delCollect(id);
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
