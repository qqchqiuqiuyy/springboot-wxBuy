package com.cqvie.sell.service;

import com.cqvie.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * @author yuyu
 * @since 2017/11/20 18:11
 */
public interface CateGoryService {
    ProductCategory findOne(Integer categroyID);
    List<ProductCategory> findAll();
    List<ProductCategory> findByCateGoryTypeIn(List<Integer> categroylist);
    ProductCategory save(ProductCategory productCategory);
}
