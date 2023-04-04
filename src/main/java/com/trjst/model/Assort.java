package com.trjst.model;

import java.math.BigDecimal;
import java.util.Date;

public class Assort {
    private Integer id;

    private Integer sort;

    private String name;

    private Date create_time;

    private BigDecimal yongjin;

    private String yuliu;

    private Integer percent;

    private Integer type;

    private BigDecimal psy_yongjin;

    private Integer psy_percent;

    public BigDecimal getPsy_yongjin() {
        return psy_yongjin;
    }

    public void setPsy_yongjin(BigDecimal psy_yongjin) {
        this.psy_yongjin = psy_yongjin;
    }

    public Integer getPsy_percent() {
        return psy_percent;
    }

    public void setPsy_percent(Integer psy_percent) {
        this.psy_percent = psy_percent;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public BigDecimal getYongjin() {
        return yongjin;
    }

    public void setYongjin(BigDecimal yongjin) {
        this.yongjin = yongjin;
    }

    public String getYuliu() {
        return yuliu;
    }

    public void setYuliu(String yuliu) {
        this.yuliu = yuliu;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}