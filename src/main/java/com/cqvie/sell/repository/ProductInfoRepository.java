package com.cqvie.sell.repository;

import com.cqvie.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yuyu
 * @since 2017/11/20 18:31
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    /**
     * 根据商品类型查询
     * @param productStatus
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
