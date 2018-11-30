package com.cqvie.sell.dto;

import com.cqvie.sell.dataobject.OrderDetail;
import com.cqvie.sell.enums.OrderStatusEnum;
import com.cqvie.sell.enums.PayStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import utils.DateToLang;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用于在各个层传输的对象
 * @author yuyu
 * @since 2017/11/22 14:37
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
    /**
     *   *订单id
     */
    @Id
    private String orderId;
    /**
     *   买家名字
     */
    private String buyerName;
    /**
     *   买家手机号
     */
    private String buyerPhone;
    /**
     *   买家地址
     */
    private String buyerAddress;

    /**
     *   买家微信Openid
     */
    private String buyerOpenid;
    /**
     * 订单总金额
     */
    private BigDecimal orderAnount;
    /**
     * 订单状态 默认0新下单
     *
     */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    /**
     *支付状态
     *
     */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    /**
     * 创建时间
     */
    @JsonSerialize(using = DateToLang.class)
    private Date createTime;
    /**
     *
     * 修改时间
     */
    @JsonSerialize(using = DateToLang.class)
    private Date updateTime;
    /**
     *
     * 明细列表
     */
    private List<OrderDetail> orderDetails;
}
