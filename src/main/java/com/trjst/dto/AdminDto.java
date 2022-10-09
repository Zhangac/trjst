package com.trjst.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import java.util.Date;

@Data
@ToString
public class AdminDto {

    @ApiModelProperty(value = "用户Id",name = "id", example = "12345")
    private Integer id;

    @ApiModelProperty(value = "账号",name = "account", example = "12345")
    private String account;

    @ApiModelProperty(value = "密码",name = "password", example = "")
    private String password;

    @ApiModelProperty(value = "级别",name = "level", example = "1")
    private Integer level;

    @ApiModelProperty(value = "状态",name = "status", example = "1")
    private Integer status;

    @ApiModelProperty(value = "创建时间",name = "createTime", example = "")
    private Date createTime;

    @ApiModelProperty(value = "每页记录数",name = "pageSize", example = "20", required = true)
    private Integer page;

    @ApiModelProperty(value = "当前页数",name = "currPage", example = "1", required = true)
    @Min(1)
    private Integer rows;
}