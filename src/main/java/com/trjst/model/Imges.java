package com.trjst.model;

import java.util.Date;

public class Imges {
    private Integer id;

    private Integer fk_id;

    private String address;

    private Integer type;

    private Date create_time;

    public Imges(){

    }

    public Imges(Integer fk_id) {
        this.fk_id = fk_id;
    }

    public Imges(Integer fk_id, Integer type) {
        this.fk_id = fk_id;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Imges{" +
                "id=" + id +
                ", fk_id=" + fk_id +
                ", address='" + address + '\'' +
                ", type=" + type +
                ", create_time=" + create_time +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFk_id() {
        return fk_id;
    }

    public void setFk_id(Integer fk_id) {
        this.fk_id = fk_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}