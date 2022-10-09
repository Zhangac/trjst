package com.trjst.mapper;

import com.trjst.model.Checklist;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ChecklistMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Checklist record);

    Checklist selectByPrimaryKey(Integer id);

    List<Checklist> selectByUserId(Integer user_id);

    int updateByPrimaryKeySelective(Checklist record);

}