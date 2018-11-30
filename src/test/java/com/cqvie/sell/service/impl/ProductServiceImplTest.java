package com.cqvie.sell.service.impl;

import com.cqvie.sell.dataobject.ProductInfo;
import com.cqvie.sell.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author yuyu
 * @since 2017/11/20 18:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    ProductService productService;
    @Test
    public void findOne() throws Exception {
        ProductInfo one = productService.findOne("123");
        System.out.println(one);
    }

    @Test
    public void findupAll() throws Exception {
        List<ProductInfo> productInfos = productService.findUpAll();
        System.out.println(productInfos.get(0));
    }

    @Test
    public void findAll() throws Exception {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<ProductInfo> all = productService.findAll(pageRequest);

    }

    @Test
    public void save() throws Exception {
    }

}