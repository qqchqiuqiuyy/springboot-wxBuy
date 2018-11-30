package com.cqvie.sell.repository;

import com.cqvie.sell.dataobject.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @author yuyu
 * @since 2017/11/22 10:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    OrderMasterRepository repository;
    @Test
    public void findByBuyerOpenId() throws Exception {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderMaster> orderMasterPage = repository.findByBuyerOpenid("11111", pageRequest);
        System.out.println(orderMasterPage.getTotalElements());
    }

    @Test
    public void sava(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerAddress("重庆市工程职业技术学院");
        orderMaster.setBuyerName("余继彪");
        orderMaster.setBuyerOpenid("11111");
        orderMaster.setBuyerPhone("18580009032");
        orderMaster.setOrderId("123456");
        orderMaster.setOrderAnount(new BigDecimal(10.2));
        repository.save(orderMaster);
    }
}