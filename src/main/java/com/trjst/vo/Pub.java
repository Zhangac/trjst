package com.trjst.vo;

import java.math.BigDecimal;

public class Pub {

    private Integer order_id;

    private BigDecimal money;

    private BigDecimal jin_num;


    public Pub(){

    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getJin_num() {
        return jin_num;
    }

    public void setJin_num(BigDecimal jin_num) {
        this.jin_num = jin_num;
    }
}
