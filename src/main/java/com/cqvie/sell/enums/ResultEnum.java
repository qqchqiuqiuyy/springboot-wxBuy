package com.cqvie.sell.enums;

import lombok.Getter;

/**
 * @author yuyu
 * @since 2017/11/22 14:52
 */
@Getter
public enum  ResultEnum {
    PARAM_ERROR(1,"参数不正确"),
    PRODUCT_NOT_EXIT(10,"商品不存在"),
    PRODUCT_STOCK_ERROR_(11,"库存不正确"),
    ORDER_NOT_EXIT(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIT(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    ORDER_UPDATE_ERROR(15,"订单更新失败"),
    ORDER_NOT_DETAIL(16,"订单中无商品详情"),
    PAY_ORDER(17,"订单支付状态不正确"),
    CART_NOT(18,"购物车为空"),
    ORDER_OWNER_ERROR(19,"该订单不属于当前用户")
    ;
    private Integer code;
    private String message;
    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
