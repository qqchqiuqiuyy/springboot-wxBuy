package com.cqvie.sell.dto;

import lombok.Data;

/**
 * 购物车
 * @author yuyu
 * @since 2017/11/22 15:21
 */
@Data
public class CartDto {
    /**
     * 商品id
     */
    private String productId;
    /**
     * 商品数量
     */
    private Integer productQuantity;

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
