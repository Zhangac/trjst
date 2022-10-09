package com.trjst.service.admin;

import com.alibaba.fastjson.JSONObject;
import com.trjst.mapper.OperationRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminOperationRecordService {

    @Autowired
    private OperationRecordMapper operationRecordMapper;

    /**
     * 返回含banner列表的json
     * */
    public String getResultJson(Integer start, Integer length_number, Integer draw) {
        List resultList = operationRecordMapper.getResultList(start, length_number);
        Integer countnumber = operationRecordMapper.getListCount();
        JSONObject jobj = new JSONObject();
        jobj.put("draw", draw);
        jobj.put("recordsFiltered", countnumber);
        jobj.put("recordsTotal", countnumber);
        jobj.put("data", resultList);
        return jobj.toString();
    }
}
