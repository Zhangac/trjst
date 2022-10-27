package com.trjst.model;

import java.math.BigDecimal;
import java.util.Date;

public class ShoppingCart {
    private Integer id;

    private Integer commodity_id;

    private Integer user_id;

    private Integer num;

    private Date create_time;

    private Integer speci_id;

    private Integer cs_type;

    private String commodity_name;

    private String main_pic;

    private String des;

    private BigDecimal original_price;

    private BigDecimal activity_price;

    private Integer merchant_id;

    private String weight;
    private String speci_name;
    private String speci_regu;
    private BigDecimal speci_price;

    public String getSpeci_name() {
        return speci_name;
    }

    public void setSpeci_name(String speci_name) {
        this.speci_name = speci_name;
    }

    public String getSpeci_regu() {
        return speci_regu;
    }

    public void setSpeci_regu(String speci_regu) {
        this.speci_regu = speci_regu;
    }

    public BigDecimal getSpeci_price() {
        return speci_price;
    }

    public void setSpeci_price(BigDecimal speci_price) {
        this.speci_price = speci_price;
    }

    public Integer getSpeci_id() {
        return speci_id;
    }

    public void setSpeci_id(Integer speci_id) {
        this.speci_id = speci_id;
    }

    public Integer getCs_type() {
        return cs_type;
    }

    public void setCs_type(Integer cs_type) {
        this.cs_type = cs_type;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Integer getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(Integer merchant_id) {
        this.merchant_id = merchant_id;
    }

    public BigDecimal getActivity_price() {
        return activity_price;
    }

    public void setActivity_price(BigDecimal activity_price) {
        this.activity_price = activity_price;
    }

    public BigDecimal getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(BigDecimal original_price) {
        this.original_price = original_price;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}