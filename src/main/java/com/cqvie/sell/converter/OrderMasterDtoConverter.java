package com.cqvie.sell.converter;

import com.cqvie.sell.dataobject.OrderMaster;
import com.cqvie.sell.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * 用于Order类的转换
 *
 * @author yuyu
 * @since 2017/11/22 16:21
 */
public class OrderMasterDtoConverter {

        public static OrderDto converter(OrderMaster orderMaster){
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(orderMaster,orderDto);
            return orderDto;
        }

        public static List<OrderDto> converter(List<OrderMaster> orderMasters){
            return orderMasters.stream().map(e -> converter(e)).collect(Collectors.toList());
        }
}
