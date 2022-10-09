package com.trjst.model;

import java.math.BigDecimal;
import java.util.Date;

public class MerchantInfo {

    private Integer id;

    private Integer area_id;

    private Integer delivery_id;

    private Integer user_id;

    private Integer market_id;

    private String merchant_name;

    private String address;

    private String name;

    private String des;

    private String operate_head;

    private String card;

    private String company_name;

    private String tel;

    private Integer audit_status;

    private Integer status;

    private Integer is_hot;

    private Date create_time;

    private String delivery_name;

    private Integer type;

    private String contract;

    private Integer is_show;

    private Integer is_ruzhu;

    private BigDecimal ruzhu_amount;

    public Integer getArea_id() {
        return area_id;
    }

    public void setArea_id(Integer area_id) {
        this.area_id = area_id;
    }

    public Integer getIs_ruzhu() {
        return is_ruzhu;
    }

    public void setIs_ruzhu(Integer is_ruzhu) {
        this.is_ruzhu = is_ruzhu;
    }

    public BigDecimal getRuzhu_amount() {
        return ruzhu_amount;
    }

    public void setRuzhu_amount(BigDecimal ruzhu_amount) {
        this.ruzhu_amount = ruzhu_amount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public Integer getIs_show() {
        return is_show;
    }

    public void setIs_show(Integer is_show) {
        this.is_show = is_show;
    }

    public String getDelivery_name() {
        return delivery_name;
    }

    public void setDelivery_name(String delivery_name) {
        this.delivery_name = delivery_name;
    }

    public Integer getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(Integer delivery_id) {
        this.delivery_id = delivery_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getMarket_id() {
        return market_id;
    }

    public void setMarket_id(Integer market_id) {
        this.market_id = market_id;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name == null ? null : merchant_name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getOperate_head() {
        return operate_head;
    }

    public void setOperate_head(String operate_head) {
        this.operate_head = operate_head == null ? null : operate_head.trim();
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card == null ? null : card.trim();
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name == null ? null : company_name.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getAudit_status() {
        return audit_status;
    }

    public void setAudit_status(Integer audit_status) {
        this.audit_status = audit_status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(Integer is_hot) {
        this.is_hot = is_hot;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}