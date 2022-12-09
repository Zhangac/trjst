package com.trjst.controller.admin.model;

import java.util.Date;

public class AdminReturnModel {

    private Integer id;

    private String account;

    private Integer level;

    private String levelname;

    private Integer status;

    private Date create_time;

    private Integer item_status;

//    private String item_name;

    private Integer patent_status;

//    private String patent_name;

    private Integer copyright_status;

//    private String copyright_name;

    private Integer company_status;

//    private String company_name;

    private Integer needs_status;

//    private String needs_name;

    private Integer area;

    private String area_name;

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public Integer getItem_status() {
        return item_status;
    }

    public void setItem_status(Integer item_status) {
        this.item_status = item_status;
    }

    public Integer getPatent_status() {
        return patent_status;
    }

    public void setPatent_status(Integer patent_status) {
        this.patent_status = patent_status;
    }

    public Integer getCopyright_status() {
        return copyright_status;
    }

    public void setCopyright_status(Integer copyright_status) {
        this.copyright_status = copyright_status;
    }

    public Integer getCompany_status() {
        return company_status;
    }

    public void setCompany_status(Integer company_status) {
        this.company_status = company_status;
    }

    public Integer getNeeds_status() {
        return needs_status;
    }

    public void setNeeds_status(Integer needs_status) {
        this.needs_status = needs_status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getLevelname() { return levelname; }

    public void setLevelname(String levelname) { this.levelname = levelname; }

}
