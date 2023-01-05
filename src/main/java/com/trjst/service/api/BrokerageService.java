package com.trjst.service.api;

import com.trjst.mapper.BrokerageMapper;
import com.trjst.model.Brokerage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrokerageService {

    @Autowired
    private BrokerageMapper brokerageMapper;

    public List<Brokerage> brokerageList(){
        return brokerageMapper.selectByAll();
    }

    public List<Brokerage> brokerageListType(Integer type){
        return brokerageMapper.selectByType(type);
    }
}
