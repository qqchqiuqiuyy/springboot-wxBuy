package com.cqvie.sell.enums;

import lombok.Getter;

/**
 * @author yuyu
 * @since 2017/11/22 10:25
 */
@Getter
public enum  OrderStatusEnum {
    NEW(0,"新下单"),
    FINISH(1,"完结"),
    CANCEL(2,"取消"),
    ;
    private  Integer code;
    private  String message;
    OrderStatusEnum(Integer code,String message) {
        this.code = code;
        this.message = message;
    }
}
