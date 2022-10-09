package com.trjst.mapper;

import com.trjst.model.WithdrawSave;
import org.springframework.stereotype.Component;

@Component
public interface WithdrawSaveMapper {

    int insertSelective(WithdrawSave record);

    WithdrawSave selectByPrimaryKey(Integer id);

    WithdrawSave selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(WithdrawSave record);
}