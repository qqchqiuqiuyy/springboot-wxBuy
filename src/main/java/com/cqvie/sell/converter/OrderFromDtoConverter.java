package com.cqvie.sell.converter;

import com.cqvie.sell.dataobject.OrderDetail;
import com.cqvie.sell.dto.OrderDto;
import com.cqvie.sell.enums.ResultEnum;
import com.cqvie.sell.exception.SellException;
import com.cqvie.sell.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuyu
 * @since 2017/11/23 15:44
 */
@Slf4j
public class OrderFromDtoConverter {
    public static OrderDto converter(OrderForm orderForm){
        Gson gson  = new Gson();
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerOpenid(orderForm.getOpenId());
        orderDto.setBuyerAddress(orderForm.getOpenId());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList =  gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e) {
            log.error("【对象转换错误】String={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        orderDto.setOrderDetails(orderDetailList);
        return  orderDto;
    }
}
