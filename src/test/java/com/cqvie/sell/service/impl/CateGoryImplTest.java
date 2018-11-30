package com.cqvie.sell.service.impl;

import com.cqvie.sell.dataobject.ProductCategory;
import com.cqvie.sell.service.CateGoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author yuyu
 * @since 2017/11/20 18:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CateGoryImplTest {
    @Autowired
    CateGoryService cateGoryService;


    @Test
    public void findOne() throws Exception {
        ProductCategory one = cateGoryService.findOne(5);
        System.out.println(one);
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> all = cateGoryService.findAll();
        System.out.println(all.size());
    }

    @Test
    public void findByCateGoryTypeIn() throws Exception {
    }

    @Test
    public void save() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("男生最爱");
        productCategory.setCategoryType(221);
        cateGoryService.save(productCategory);
    }

}