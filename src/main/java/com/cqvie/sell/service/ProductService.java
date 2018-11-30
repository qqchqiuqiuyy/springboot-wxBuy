package com.cqvie.sell.service;

import com.cqvie.sell.dataobject.ProductInfo;
import com.cqvie.sell.dto.CartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author yuyu
 * @since 2017/11/20 18:44
 */
public interface ProductService {
  /**
   * 查询单个商品
   * @param productId
   * @return
   */
  ProductInfo findOne(String productId);
  /**
   * 查询在架商品
   * @return
   */
  List<ProductInfo> findUpAll();

  /**
   * 查询所有商品
    * @param pageable
   * @return
   */
  Page<ProductInfo> findAll(Pageable pageable);

  /**
   * 保存
   * @param productInfo
   * @return
   */
  ProductInfo save(ProductInfo productInfo);
  /**
   * 增加库存
   */
  void  increaseStock(List<CartDto> cartDtos);

  /**
   * 减去库存
   * @param cartDtos
   */
  void decreaseStock(List<CartDto> cartDtos);
}
