package com.trjst.enums;

/**
 * @Author: zqy
 * @Date: 2020/1/16 15:40
 */
public enum ErrorCodeSys implements ErrorCode {

    ERROR_UNKNOWN(110, "系统未知错误"),
    ERROR_NO_ACCESS_CODE(111, "授权码为空"),
    ERROR_ACCESS_CODE(112, "授权码错误"),
    ERROR_NO_NG_ACCESS_CODE(113, "NG授权码为空"),
    ERROR_NO_ACCESS_TOKEN(114, "令牌为空"),
    ERROR_SIGN(115, "签名错误"),
    ERROR_SIGN_PARAM(116, "签名参数错误"),
    ERROR_SIGN_EXPIRE_TIME(117, "签名过期"),
    ERROR_SIGN_TRADE_NO(118, "签名流水号错误"),
    ERROR_NO_HEADER(201, "header信息缺失"),
    ERROR_REQUEST_PARAM(202, "请求参数有误"),
    ERROR_ILLEGAL_CHAR(301, "包含非法字符"),
    ERROR_REDIS(302, "Redis服务异常"),
    ERROR_NOT_VALID_ARG(701, "参数校验错误"),
    ERROR_DATA_DUPLICATE(801, "数据错误重复")
    ;

    private String msg;
    private int code;

    ErrorCodeSys(Integer code, String msg) {
        this.msg=msg;
        this.code=code;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public String print() {
        return this.getCode()+":"+this.getMsg();
    }
}