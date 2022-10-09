package com.trjst.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
public class MpTemplateMsg {

    private String appid;
    private String template_id;
    private String url;
    private Map miniprogram;
    private Map data;

}