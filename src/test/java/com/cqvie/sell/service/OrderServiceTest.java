package com.cqvie.sell.service;

import com.cqvie.sell.dataobject.OrderDetail;
import com.cqvie.sell.dto.OrderDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuyu
 * @since 2017/11/22 15:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void createOrderMaster() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerAddress("重庆工程职业技术学院");
        orderDto.setBuyerName("余继彪");
        orderDto.setBuyerOpenid("12223444");
        orderDto.setBuyerPhone("18580009032");
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("1234");
        orderDetail.setProductQuantity(22);
        orderDetails.add(orderDetail);
        orderDto.setOrderDetails(orderDetails);
        orderService.createOrderMaster(orderDto);
    }

    @Test
    public void findOne() throws Exception {
        OrderDto orderDto = orderService.findOne("101511337628940");
        System.out.println(orderDto);
    }

    @Test
    public void findList() throws Exception {
        PageRequest pageRequest = new PageRequest(0,3);
        Page<OrderDto> list = orderService.findList("11111", pageRequest);
    }

    @Test
    public void cancleOrder() throws Exception {
        OrderDto orderDto = orderService.findOne("101511337485850");
        OrderDto cancleOrder = orderService.cancleOrder(orderDto);
        System.out.println(cancleOrder);
    }

    @Test
    public void finish() throws Exception {
        OrderDto orderDto = orderService.findOne("101511337628940");
        OrderDto cancleOrder = orderService.finish(orderDto);
    }

    @Test
    public void paid() throws Exception {
        OrderDto orderDto = orderService.findOne("101511337628940");
        OrderDto cancleOrder = orderService.paid(orderDto);
    }

}