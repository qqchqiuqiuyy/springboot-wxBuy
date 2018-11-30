package com.cqvie.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author yuyu
 * @since 2017/11/22 10:34
 */
@Data
@Entity
public class OrderDetail {
    /**
     * 主键
     */
    @Id
    private String detailId;
    /**
     *
     * 订单Id
     */
    private String orderId;
    /**
     *
     * 商品Id
     */
    private String productId;
    /**
     *
     * 商品名称
     */
    private String productName;
    /**
     *
     * 单价
     */
    private BigDecimal productPrice;
    /**
     *
     * 商品数量
     */
    private Integer productQuantity;
    /**
     *
     * 商品小图
     */
    private String productIcon;

}
