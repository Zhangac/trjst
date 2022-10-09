package com.trjst.vo;

import java.math.BigDecimal;

public class Pub {

    private Integer order_id;

    private BigDecimal money;

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
}
