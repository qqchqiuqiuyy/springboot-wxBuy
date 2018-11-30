package com.cqvie.sell.repository;

import com.cqvie.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yuyu
 * @since 2017/11/20 17:08
 */
public interface ProductCateGoryRepository extends JpaRepository<ProductCategory,Integer>{
    /**
     *  根据类别编号查询
     * @param CategoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIsIn(List<Integer> CategoryTypeList);
}
