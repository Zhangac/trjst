package com.trjst.service.api;

import com.trjst.mapper.AreaMapper;
import com.trjst.model.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService {

    @Autowired
    private AreaMapper areaMapper;

    public List<Area> listArea(){
        return areaMapper.listArea();
    }
}
