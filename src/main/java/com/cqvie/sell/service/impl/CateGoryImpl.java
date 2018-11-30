package com.cqvie.sell.service.impl;

import com.cqvie.sell.dataobject.ProductCategory;
import com.cqvie.sell.repository.ProductCateGoryRepository;
import com.cqvie.sell.service.CateGoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuyu
 * @since 2017/11/20 18:14
 */
@Service
public class CateGoryImpl implements CateGoryService {
    @Autowired
    private ProductCateGoryRepository repository;

    @Override
    public ProductCategory findOne(Integer categroyID) {
        return repository.findOne(categroyID);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCateGoryTypeIn(List<Integer> categroylist) {
        return repository.findByCategoryTypeIsIn(categroylist);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
