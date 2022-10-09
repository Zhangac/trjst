package com.trjst.dto;

public class DeliveryMerGroupDto {

    private Integer id;

    private Integer pay_status;

    private Integer confirm_receipt;

    private Integer type;

    private Integer delivery_id;

    private Integer merchant_id;

    private String merchant_name;

    private String address;

    private String name;

    private String des;

    private String operate_head;

    public Integer getPay_status() {
        return pay_status;
    }

    public void setPay_status(Integer pay_status) {
        this.pay_status = pay_status;
    }

    public Integer getConfirm_receipt() {
        return confirm_receipt;
    }

    public void setConfirm_receipt(Integer confirm_receipt) {
        this.confirm_receipt = confirm_receipt;
    }

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

    public Integer getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(Integer delivery_id) {
        this.delivery_id = delivery_id;
    }

    public Integer getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(Integer merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.operate_head = operate_head;
    }
}
