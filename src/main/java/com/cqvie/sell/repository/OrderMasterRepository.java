package com.cqvie.sell.repository;

import com.cqvie.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yuyu
 * @since 2017/11/22 10:38
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
    /**
     * 根据买家openid查询
     * @Description
     * @param buyerOpenid
     * @param pageable
     * @return 分页
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid,Pageable pageable);

}
