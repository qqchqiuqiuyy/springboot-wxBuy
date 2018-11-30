package com.cqvie.sell.service.impl;

import com.cqvie.sell.dto.OrderDto;
import com.cqvie.sell.enums.ResultEnum;
import com.cqvie.sell.exception.SellException;
import com.cqvie.sell.service.BuyerService;
import com.cqvie.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuyu
 * @since 2017/11/24 13:53
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDto findBuyerOrder(String openid, String orderId) {
                return checkOrderOwner(openid,orderId);
    }

    @Override
    public OrderDto cancel(String openid, String orderId) {
        OrderDto orderDto = checkOrderOwner(openid, orderId);
        if(orderDto==null){
            log.error("【取消订单】查询不到该订单 orderId={}\",orderId");
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }
        return orderService.cancleOrder(orderDto);
    }
    public OrderDto checkOrderOwner(String openid,String orderId){
        OrderDto orderDto = orderService.findOne(orderId);
        if(orderDto==null){
            return null;

        }        //判断该订单是否输入当前用户id
        if(!orderDto.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【查询订单】失败当前用户id和订单i不匹配  openid={},orderDtoId={}",openid,orderDto.getBuyerOpenid());
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDto;
    }
}
