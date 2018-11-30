package com.cqvie.sell.enums;

import lombok.Getter;

/**
 * @author yuyu
 * @since 2017/11/20 18:50
 */
@Getter
public enum ProductStatusEnum {
    UP(0,"在架"),
    DOMN(1,"下架")
    ;
    private  Integer code;
    private  String message;
    ProductStatusEnum(Integer code,String message) {
        this.code = code;
        this.message = message;
    }
}
