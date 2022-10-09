package com.trjst.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
public class WxSendDto {

    //private Map map;


    private String access_token;
    private String touser;
    private String page;
    private String form_id;
    private Map data;
    private String emphasis_keyword;

//    private WeappTemplateMsg weapp_template_msg;
//    private MpTemplateMsg mp_template_msg;


}