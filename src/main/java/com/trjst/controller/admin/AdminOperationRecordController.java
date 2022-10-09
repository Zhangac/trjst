package com.trjst.controller.admin;

import com.trjst.service.admin.AdminOperationRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class AdminOperationRecordController {

    @Autowired
    private AdminOperationRecordService adminOperationRecordService;

    //列表
    @RequestMapping("/adminoperationRecordlist")
    public String adminoperationRecordlist(HttpServletRequest request) {
        return "operationRecord/list";
    }

    @RequestMapping(value = "/adminoperationRecordlistajax")
    @ResponseBody
    public String adminBrokeragelistajax(HttpServletRequest request, Integer draw){
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length_number = Integer.valueOf(request.getParameter("length"));
        return adminOperationRecordService.getResultJson(start,length_number,draw);
    }

}
