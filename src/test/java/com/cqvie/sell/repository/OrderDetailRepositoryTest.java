package com.cqvie.sell.repository;

import com.cqvie.sell.dataobject.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yuyu
 * @since 2017/11/22 14:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    OrderDetailRepository repository;

    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> byOrderId = repository.findByOrderId("123");
        System.out.println(byOrderId.size());
    }

    @Test
    public void save(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123");
        orderDetail.setOrderId("123");
        orderDetail.setProductIcon("sssss");
        orderDetail.setProductId("123");
        orderDetail.setProductName("猜猜猜");
        orderDetail.setProductQuantity(2);
        orderDetail.setProductPrice(new BigDecimal(3.2));
        repository.save(orderDetail);
    }
}