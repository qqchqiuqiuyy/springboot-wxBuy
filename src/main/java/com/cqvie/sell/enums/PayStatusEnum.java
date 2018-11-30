package com.cqvie.sell.enums;

import lombok.Getter;

/**
 * @author yuyu
 * @since 2017/11/22 10:30
 */
@Getter
public enum PayStatusEnum {
    /**
     *
     */
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功");
    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
