package com.trjst.controller.api;

import com.trjst.model.UserSearch;
import com.trjst.service.api.UserSearchService;
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
@Api(tags="用户搜索记录")
@RequestMapping("/userSearch")
@Slf4j
public class UserSearchController {

    @Autowired
    private UserSearchService userSearchService;

    @ApiOperation(value = "个人记录", notes = "个人记录")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<UserSearch> searchByUserId(@ApiParam(value = "用户id", required = true) @RequestParam("userId") Integer userId) throws IOException {
        return userSearchService.searchByUserId(userId);
    }

    @ApiOperation(value = "添加", notes = "添加")
    @RequestMapping(value = "/addSearch", method = RequestMethod.POST)
    public Map addSearch(@RequestBody @Valid UserSearch req) throws IOException {
        int num = userSearchService.addSearch(req);
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

    @ApiOperation(value = "删除（暂时不用）", notes = "删除（暂时不用）")
    @RequestMapping(value = "/delSearch", method = RequestMethod.GET)
    public Map delSearch(@ApiParam(value = "id", required = true) @RequestParam("id") Integer id) throws IOException {
        Map map = new HashMap();
        try {
            userSearchService.delSearch(id);
            map.put("code",200);
            map.put("msg","success");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","error");
        }
        return map;
    }
}
