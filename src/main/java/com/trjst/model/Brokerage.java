package com.trjst.model;

import java.math.BigDecimal;
import java.util.Date;

public class Brokerage {
    private Integer id;

    private BigDecimal brokerage_amount;

    private Integer type;

    private Date create_time;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getBrokerage_amount() {
        return brokerage_amount;
    }

    public void setBrokerage_amount(BigDecimal brokerage_amount) {
        this.brokerage_amount = brokerage_amount;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}