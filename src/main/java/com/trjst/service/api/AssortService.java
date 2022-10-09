package com.trjst.service.api;

import com.trjst.mapper.AssortMapper;
import com.trjst.model.Assort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssortService {

    @Autowired
    private AssortMapper assortMapper;

    public List<Assort> listAssort(){
        return assortMapper.listAssort();
    }
}
