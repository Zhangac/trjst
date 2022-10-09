package com.trjst.model;

import java.math.BigDecimal;
import java.util.Date;

public class Collect {
    private Integer id;

    private Integer assort_id;

    private Integer commodity_id;

    private Integer user_id;

    private Integer type;

    private Date create_time;

    private String commodity_name;

    private String main_pic;

    private String des;

    private BigDecimal original_price;

    private BigDecimal activity_price;

    private String assort_name;

    public String getAssort_name() {
        return assort_name;
    }

    public void setAssort_name(String assort_name) {
        this.assort_name = assort_name;
    }

    public Integer getAssort_id() {
        return assort_id;
    }

    public void setAssort_id(Integer assort_id) {
        this.assort_id = assort_id;
    }

    public BigDecimal getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(BigDecimal original_price) {
        this.original_price = original_price;
    }

    public BigDecimal getActivity_price() {
        return activity_price;
    }

    public void setActivity_price(BigDecimal activity_price) {
        this.activity_price = activity_price;
    }

    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name;
    }

    public String getMain_pic() {
        return main_pic;
    }

    public void setMain_pic(String main_pic) {
        this.main_pic = main_pic;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(Integer commodity_id) {
        this.commodity_id = commodity_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}