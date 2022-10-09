package com.trjst.model;

import java.util.Date;

public class Market {
    private Integer id;

    private Integer area_id;

    private String market_name;

    private String market_img;

    private String market_province;

    private String market_city;

    private String market_region;

    private String market_address;

    private Integer market_status;

    private Date create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArea_id() {
        return area_id;
    }

    public void setArea_id(Integer area_id) {
        this.area_id = area_id;
    }

    public String getMarket_name() {
        return market_name;
    }

    public void setMarket_name(String market_name) {
        this.market_name = market_name == null ? null : market_name.trim();
    }

    public String getMarket_img() {
        return market_img;
    }

    public void setMarket_img(String market_img) {
        this.market_img = market_img == null ? null : market_img.trim();
    }

    public String getMarket_province() {
        return market_province;
    }

    public void setMarket_province(String market_province) {
        this.market_province = market_province == null ? null : market_province.trim();
    }

    public String getMarket_city() {
        return market_city;
    }

    public void setMarket_city(String market_city) {
        this.market_city = market_city == null ? null : market_city.trim();
    }

    public String getMarket_region() {
        return market_region;
    }

    public void setMarket_region(String market_region) {
        this.market_region = market_region == null ? null : market_region.trim();
    }

    public String getMarket_address() {
        return market_address;
    }

    public void setMarket_address(String market_address) {
        this.market_address = market_address == null ? null : market_address.trim();
    }

    public Integer getMarket_status() {
        return market_status;
    }

    public void setMarket_status(Integer market_status) {
        this.market_status = market_status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}