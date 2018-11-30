package com.cqvie.sell.service;

import com.cqvie.sell.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author yuyu
 * @since 2017/11/22 14:29
 */
public interface OrderService {

    /**
     *
     * 创建订单
     * @return Order实体
     */
    OrderDto createOrderMaster(OrderDto orderDto);

    /**
     * 查询单个订单
     * @param orderId
     * @return
     */
    OrderDto findOne(String orderId);

    /**
     *查询用户订单列表
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderDto> findList(String buyerOpenid, Pageable pageable);

    /**
     * 取消订单
     * @param orderDto
     * @return
     */
    OrderDto cancleOrder(OrderDto orderDto);

    /**
     * 完结订单
     * @param orderDto
     * @return
     */
    OrderDto finish(OrderDto orderDto);

    /**
     * 支付订单
     * @param orderDto
     * @return
     */
    OrderDto paid(OrderDto orderDto);
}