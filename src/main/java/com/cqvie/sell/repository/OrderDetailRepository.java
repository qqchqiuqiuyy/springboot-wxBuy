package com.cqvie.sell.repository;

import com.cqvie.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yuyu
 * @since 2017/11/22 10:45
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    /**
     * 根据订单id查询
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);

}
