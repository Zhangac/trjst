package com.trjst.vo;

import java.math.BigDecimal;
import java.util.List;

public class OrderPay {

    public List<Pub> pub;

    private BigDecimal total_money;

    private Integer type;

    private Integer user_id;

    public Integer merchant_id;

    public Integer getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(Integer merchant_id) {
        this.merchant_id = merchant_id;
    }

    public OrderPay(){

    }

    public List<Pub> getPub() {
        return pub;
    }

    public void setPub(List<Pub> pub) {
        this.pub = pub;
    }

    public BigDecimal getTotal_money() {
        return total_money;
    }

    public void setTotal_money(BigDecimal total_money) {
        this.total_money = total_money;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

}
