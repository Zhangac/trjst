package com.trjst.model;

import java.math.BigDecimal;
import java.util.Date;

public class User {
    private Integer id;

    private String open_id;

    private String nick_name;

    private String head;

    private String sex;

    private String adderss;

    private String phone;

    private Integer is_mech;

    private BigDecimal amount;

    private Date create_time;

    private Integer is_vip;

    private String id_card;

    private String user_name;

    private String just_imge;

    private String back_imge;

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getJust_imge() {
        return just_imge;
    }

    public void setJust_imge(String just_imge) {
        this.just_imge = just_imge;
    }

    public String getBack_imge() {
        return back_imge;
    }

    public void setBack_imge(String back_imge) {
        this.back_imge = back_imge;
    }

    public Integer getIs_vip() {
        return is_vip;
    }

    public void setIs_vip(Integer is_vip) {
        this.is_vip = is_vip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id == null ? null : open_id.trim();
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name == null ? null : nick_name.trim();
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head == null ? null : head.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAdderss() {
        return adderss;
    }

    public void setAdderss(String adderss) {
        this.adderss = adderss == null ? null : adderss.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIs_mech() {
        return is_mech;
    }

    public void setIs_mech(Integer is_mech) {
        this.is_mech = is_mech;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}