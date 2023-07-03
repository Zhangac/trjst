package com.trjst.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CommodityInfo {
    private Integer id;

    private Integer assort_id;

    private String commodity_name;

    private Integer market_id;

    private Integer merchant_id;

    private String weight;

    private String specifications;

    private String main_pic;

    private String list_pic;

    private String commodity_detail;

    private BigDecimal activity_price;

    private BigDecimal original_price;

    private Integer audit_status;

    private Integer status;

    private Integer stock;

    private Integer is_hot;

    private Date create_time;

    private String des;

    private Integer sold;

    private String reserved;


    private String market_province;
    private String market_city;
    private String market_region;
    private String market_address;
    private Integer area_id;
    private String area_name;

    private List<Imges> imgesList;
    private List<Imges> imgesDetail;
    private List<Speci> SpeciDetail;

    private Integer sort;
    private Integer topPing;//1置顶 0不置顶

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getTopPing() {
        return topPing;
    }

    public void setTopPing(Integer topPing) {
        this.topPing = topPing;
    }

    @Override
    public String toString() {
        return "CommodityInfo{" +
                "id=" + id +
                ", assort_id=" + assort_id +
                ", commodity_name='" + commodity_name + '\'' +
                ", market_id=" + market_id +
                ", merchant_id=" + merchant_id +
                ", weight='" + weight + '\'' +
                ", specifications='" + specifications + '\'' +
                ", main_pic='" + main_pic + '\'' +
                ", list_pic='" + list_pic + '\'' +
                ", commodity_detail='" + commodity_detail + '\'' +
                ", activity_price=" + activity_price +
                ", original_price=" + original_price +
                ", audit_status=" + audit_status +
                ", status=" + status +
                ", stock=" + stock +
                ", is_hot=" + is_hot +
                ", create_time=" + create_time +
                ", des='" + des + '\'' +
                ", sold=" + sold +
                ", reserved='" + reserved + '\'' +
                ", market_province='" + market_province + '\'' +
                ", market_city='" + market_city + '\'' +
                ", market_region='" + market_region + '\'' +
                ", market_address='" + market_address + '\'' +
                ", area_id=" + area_id +
                ", area_name='" + area_name + '\'' +
                ", imgesList=" + imgesList +
                ", imgesDetail=" + imgesDetail +
                '}';
    }

    public List<Speci> getSpeciDetail() {
        return SpeciDetail;
    }

    public void setSpeciDetail(List<Speci> speciDetail) {
        SpeciDetail = speciDetail;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public List<Imges> getImgesList() {
        return imgesList;
    }

    public void setImgesList(List<Imges> imgesList) {
        this.imgesList = imgesList;
    }

    public List<Imges> getImgesDetail() {
        return imgesDetail;
    }

    public void setImgesDetail(List<Imges> imgesDetail) {
        this.imgesDetail = imgesDetail;
    }

    public Integer getArea_id() {
        return area_id;
    }

    public void setArea_id(Integer area_id) {
        this.area_id = area_id;
    }

    public String getMarket_province() {
        return market_province;
    }

    public void setMarket_province(String market_province) {
        this.market_province = market_province;
    }

    public String getMarket_city() {
        return market_city;
    }

    public void setMarket_city(String market_city) {
        this.market_city = market_city;
    }

    public String getMarket_region() {
        return market_region;
    }

    public void setMarket_region(String market_region) {
        this.market_region = market_region;
    }

    public String getMarket_address() {
        return market_address;
    }

    public void setMarket_address(String market_address) {
        this.market_address = market_address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssort_id() {
        return assort_id;
    }

    public void setAssort_id(Integer assort_id) {
        this.assort_id = assort_id;
    }

    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name == null ? null : commodity_name.trim();
    }

    public Integer getMarket_id() {
        return market_id;
    }

    public void setMarket_id(Integer market_id) {
        this.market_id = market_id;
    }

    public Integer getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(Integer merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications == null ? null : specifications.trim();
    }

    public String getMain_pic() {
        return main_pic;
    }

    public void setMain_pic(String main_pic) {
        this.main_pic = main_pic == null ? null : main_pic.trim();
    }

    public String getList_pic() {
        return list_pic;
    }

    public void setList_pic(String list_pic) {
        this.list_pic = list_pic == null ? null : list_pic.trim();
    }

    public String getCommodity_detail() {
        return commodity_detail;
    }

    public void setCommodity_detail(String commodity_detail) {
        this.commodity_detail = commodity_detail == null ? null : commodity_detail.trim();
    }

    public BigDecimal getActivity_price() {
        return activity_price;
    }

    public void setActivity_price(BigDecimal activity_price) {
        this.activity_price = activity_price;
    }

    public BigDecimal getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(BigDecimal original_price) {
        this.original_price = original_price;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved == null ? null : reserved.trim();
    }
}