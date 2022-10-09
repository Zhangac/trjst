package com.trjst.service.api;

import com.trjst.mapper.MarketMapper;
import com.trjst.model.Market;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketService {

    @Autowired
    private MarketMapper marketMapper;

    public List<Market> marketList(Market record){
        return marketMapper.marketList(record);
    }
}
