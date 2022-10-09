package com.trjst.model;

import java.math.BigDecimal;
import java.util.Date;

public class Withdraw {
    private Integer id;

    private Integer user_id;

    private BigDecimal withdraw_amount;

    private Integer type;

    private String name;

    private String account;

    private String phone;

    private Integer status;

    private Date create_time;

    private String account_bank;

    private String bank;

    private BigDecimal get_amount;

    public BigDecimal getGet_amount() {
        return get_amount;
    }

    public void setGet_amount(BigDecimal get_amount) {
        this.get_amount = get_amount;
    }

    public String getAccount_bank() {
        return account_bank;
    }

    public void setAccount_bank(String account_bank) {
        this.account_bank = account_bank;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
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

    public BigDecimal getWithdraw_amount() {
        return withdraw_amount;
    }

    public void setWithdraw_amount(BigDecimal withdraw_amount) {
        this.withdraw_amount = withdraw_amount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}