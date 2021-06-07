package com.lab.shopCart.shopcartms.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StatusCode {
    E001("E001","參數輸入有誤");

    private final String code;
    private final String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "StatusCode{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
