package com.trjst.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
public class WeappTemplateMsg {

    private String template_id;
    private String page;
    private String form_id;
    private Map data;
    private String emphasis_keyword;

}