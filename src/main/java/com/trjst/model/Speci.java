package com.trjst.model;

import java.math.BigDecimal;
import java.util.Date;

public class Speci {
    private Integer speci_id;

    private Integer commodity_info_id;

    private String speci_name;

    private BigDecimal speci_price;

    private String speci_regu;

    private Date speci_time;

    private BigDecimal vip_price;

    public BigDecimal getVip_price() {
        return vip_price;
    }

    public void setVip_price(BigDecimal vip_price) {
        this.vip_price = vip_price;
    }

    public Integer getSpeci_id() {
        return speci_id;
    }

    public void setSpeci_id(Integer speci_id) {
        this.speci_id = speci_id;
    }

    public Integer getCommodity_info_id() {
        return commodity_info_id;
    }

    public void setCommodity_info_id(Integer commodity_info_id) {
        this.commodity_info_id = commodity_info_id;
    }

    public String getSpeci_name() {
        return speci_name;
    }

    public void setSpeci_name(String speci_name) {
        this.speci_name = speci_name == null ? null : speci_name.trim();
    }

    public BigDecimal getSpeci_price() {
        return speci_price;
    }

    public void setSpeci_price(BigDecimal speci_price) {
        this.speci_price = speci_price;
    }

    public String getSpeci_regu() {
        return speci_regu;
    }

    public void setSpeci_regu(String speci_regu) {
        this.speci_regu = speci_regu == null ? null : speci_regu.trim();
    }

    public Date getSpeci_time() {
        return speci_time;
    }

    public void setSpeci_time(Date speci_time) {
        this.speci_time = speci_time;
    }
}