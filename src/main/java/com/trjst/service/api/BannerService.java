package com.trjst.service.api;

import com.trjst.mapper.BannerMapper;
import com.trjst.model.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    public List<Banner> listBanner(){
        return bannerMapper.listBanner();
    }
}
