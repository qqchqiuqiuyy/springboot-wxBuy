package com.cqvie.sell.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author yuyu
 * @since 2017/11/23 15:27
 */
@Data
public class OrderForm {
    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名必填")
    private String name;

    /**
     * 买家电话
     */
    @NotEmpty(message = "电话必须填")
    private String phone;
    /**
     * 买家地址
     */
    @NotEmpty(message = "地址必填")
    private String address;
    /**
     * 用户openid
     */
    @NotEmpty(message = "用户openid必填")
    private String openId;
    /**
     * 购物车
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;

}
