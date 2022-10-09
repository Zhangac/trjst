package com.trjst.service.api;

import com.trjst.mapper.ChecklistMapper;
import com.trjst.model.Checklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChecklistService {

    @Autowired
    private ChecklistMapper checklistMapper;

    public List<Checklist> selectByUserId(Integer user_id){
        return checklistMapper.selectByUserId(user_id);
    }

    public int addCheckList(Checklist record){
        return checklistMapper.insertSelective(record);
    }

    public int updateCheckList(Checklist record){
        return checklistMapper.updateByPrimaryKeySelective(record);
    }

    public int delCheckList(Integer id){
        return checklistMapper.deleteByPrimaryKey(id);
    }
}
