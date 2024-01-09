package com.trjst.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class JstOrder {
    private Integer id;

    private Integer user_id;

    private Integer merchant_id;

    private Integer commodity_id;

    private String order_no;

    private String pay_no;

    private BigDecimal order_price;

    private BigDecimal pay_price;

    private Integer pay_status;

    //@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pay_time;

    private BigDecimal commission;

    private Integer confirm_receipt;

    //@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date confirm_time;

    private BigDecimal reserve_price;

    private BigDecimal spread_price;

    private BigDecimal total_price;

    private Date create_time;

    private String commodity_name;

    private String main_pic;

    private String des;

    private String phone;

    private String address;

    private String name;

    private Integer type;

    private Integer delivery_id;

    private String goods_desc;

    private BigDecimal unit_price;

    private Integer quantity;

    private Integer spread_status;

    private String spread_no;

    private Date fahuo_time;

    private Date arrived_time;

    private String delivery_name;

    private String jin_num;

    private BigDecimal spread_jin_num;

    private String start_time;

    private String end_time;

    private String detailed_address;

    private String delivery_phone;

    private String spread_remark;

    private Integer pay_type;

    private Integer spread_pay_type;

    private BigDecimal order_psy_yongjin;

    private String ass_yuliu;

    private String tk_reason;

    private Integer salesman_id;

    private String area_name;

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public Integer getSalesman_id() {
        return salesman_id;
    }

    public void setSalesman_id(Integer salesman_id) {
        this.salesman_id = salesman_id;
    }

    public String getTk_reason() {
        return tk_reason;
    }

    public void setTk_reason(String tk_reason) {
        this.tk_reason = tk_reason;
    }

    public String getJin_num() {
        return jin_num;
    }

    public void setJin_num(String jin_num) {
        this.jin_num = jin_num;
    }

    public String getAss_yuliu() {
        return ass_yuliu;
    }

    public void setAss_yuliu(String ass_yuliu) {
        this.ass_yuliu = ass_yuliu;
    }

    public BigDecimal getOrder_psy_yongjin() {
        return order_psy_yongjin;
    }

    public void setOrder_psy_yongjin(BigDecimal order_psy_yongjin) {
        this.order_psy_yongjin = order_psy_yongjin;
    }

    public Integer getPay_type() {
        return pay_type;
    }

    public void setPay_type(Integer pay_type) {
        this.pay_type = pay_type;
    }

    public Integer getSpread_pay_type() {
        return spread_pay_type;
    }

    public void setSpread_pay_type(Integer spread_pay_type) {
        this.spread_pay_type = spread_pay_type;
    }

    public String getSpread_remark() {
        return spread_remark;
    }

    public void setSpread_remark(String spread_remark) {
        this.spread_remark = spread_remark;
    }

    public String getDelivery_phone() {
        return delivery_phone;
    }

    public void setDelivery_phone(String delivery_phone) {
        this.delivery_phone = delivery_phone;
    }

    public String getDetailed_address() {
        return detailed_address;
    }

    public void setDetailed_address(String detailed_address) {
        this.detailed_address = detailed_address;
    }

    public BigDecimal getSpread_jin_num() {
        return spread_jin_num;
    }

    public void setSpread_jin_num(BigDecimal spread_jin_num) {
        this.spread_jin_num = spread_jin_num;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }


    public String getDelivery_name() {
        return delivery_name;
    }

    public void setDelivery_name(String delivery_name) {
        this.delivery_name = delivery_name;
    }

    public Date getFahuo_time() {
        return fahuo_time;
    }

    public void setFahuo_time(Date fahuo_time) {
        this.fahuo_time = fahuo_time;
    }

    public Date getArrived_time() {
        return arrived_time;
    }

    public void setArrived_time(Date arrived_time) {
        this.arrived_time = arrived_time;
    }

    public String getSpread_no() {
        return spread_no;
    }

    public void setSpread_no(String spread_no) {
        this.spread_no = spread_no;
    }

    public Integer getSpread_status() {
        return spread_status;
    }

    public void setSpread_status(Integer spread_status) {
        this.spread_status = spread_status;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(Integer delivery_id) {
        this.delivery_id = delivery_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getGoods_desc() {
        return goods_desc;
    }

    public void setGoods_desc(String goods_desc) {
        this.goods_desc = goods_desc;
    }

    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name;
    }

    public String getMain_pic() {
        return main_pic;
    }

    public void setMain_pic(String main_pic) {
        this.main_pic = main_pic;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
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

    public Integer getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(Integer merchant_id) {
        this.merchant_id = merchant_id;
    }

    public Integer getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(Integer commodity_id) {
        this.commodity_id = commodity_id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no == null ? null : order_no.trim();
    }

    public String getPay_no() {
        return pay_no;
    }

    public void setPay_no(String pay_no) {
        this.pay_no = pay_no == null ? null : pay_no.trim();
    }

    public BigDecimal getOrder_price() {
        return order_price;
    }

    public void setOrder_price(BigDecimal order_price) {
        this.order_price = order_price;
    }

    public BigDecimal getPay_price() {
        return pay_price;
    }

    public void setPay_price(BigDecimal pay_price) {
        this.pay_price = pay_price;
    }

    public Integer getPay_status() {
        return pay_status;
    }

    public void setPay_status(Integer pay_status) {
        this.pay_status = pay_status;
    }

    public Date getPay_time() {
        return pay_time;
    }

    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public Integer getConfirm_receipt() {
        return confirm_receipt;
    }

    public void setConfirm_receipt(Integer confirm_receipt) {
        this.confirm_receipt = confirm_receipt;
    }

    public Date getConfirm_time() {
        return confirm_time;
    }

    public void setConfirm_time(Date confirm_time) {
        this.confirm_time = confirm_time;
    }

    public BigDecimal getReserve_price() {
        return reserve_price;
    }

    public void setReserve_price(BigDecimal reserve_price) {
        this.reserve_price = reserve_price;
    }

    public BigDecimal getSpread_price() {
        return spread_price;
    }

    public void setSpread_price(BigDecimal spread_price) {
        this.spread_price = spread_price;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}