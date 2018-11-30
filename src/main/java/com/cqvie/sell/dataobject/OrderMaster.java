package com.cqvie.sell.dataobject;

import com.cqvie.sell.enums.OrderStatusEnum;
import com.cqvie.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 DynamicUpdate 自动更新时间
 * @author yuyu
 * @since 2017/11/22 10:18
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {
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
    private Date createTime;
    /**
     *
     * 修改时间
     */
    private Date updateTime;



}
