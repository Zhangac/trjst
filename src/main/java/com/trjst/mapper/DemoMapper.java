package com.trjst.mapper;

import com.trjst.model.Demo;
import org.springframework.stereotype.Component;

@Component
public interface DemoMapper {

    Demo selectByPrimaryKey(Integer id);
    int update(Demo demo);

}