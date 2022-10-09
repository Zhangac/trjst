package com.trjst.model;

import java.math.BigDecimal;
import java.util.Date;

public class Delivery {
    private Integer id;

    private Integer user_id;

    private Integer area_id;

    private String name;

    private String phone;

    private String id_card;

    private String num_plate;

    private Integer status;

    private Date create_time;

    private String js_cert;

    private String xs_cert;

    private String address;

    private String sc_card;

    private String car_image;

    private String contract;

    private Integer is_ruzhu;

    private BigDecimal ruzhu_amount;

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

    public Integer getArea_id() {
        return area_id;
    }

    public void setArea_id(Integer area_id) {
        this.area_id = area_id;
    }

    public String getJs_cert() {
        return js_cert;
    }

    public void setJs_cert(String js_cert) {
        this.js_cert = js_cert;
    }

    public String getXs_cert() {
        return xs_cert;
    }

    public void setXs_cert(String xs_cert) {
        this.xs_cert = xs_cert;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSc_card() {
        return sc_card;
    }

    public void setSc_card(String sc_card) {
        this.sc_card = sc_card;
    }

    public String getCar_image() {
        return car_image;
    }

    public void setCar_image(String car_image) {
        this.car_image = car_image;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getNum_plate() {
        return num_plate;
    }

    public void setNum_plate(String num_plate) {
        this.num_plate = num_plate;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card == null ? null : id_card.trim();
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