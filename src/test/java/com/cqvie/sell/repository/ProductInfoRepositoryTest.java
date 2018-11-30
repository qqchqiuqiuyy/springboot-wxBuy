package com.cqvie.sell.repository;

import com.cqvie.sell.dataobject.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yuyu
 * @since 2017/11/20 18:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
    @Autowired
    ProductInfoRepository repository;
    @Test
    public  void add(){

        for (int i =0;i<1000;i++){
            ProductInfo productInfo = new ProductInfo();
            productInfo.setProductName("红烧肉"+i);
            productInfo.setCategoryType(7);
            productInfo.setProductIcon("ssss");
            productInfo.setProductId("1234"+i);
            productInfo.setProductPrice(new BigDecimal(3.5));
            productInfo.setProductDescription("好吃");
            productInfo.setProductStock(1000);
            productInfo.setProductStatus(0);
            repository.save(productInfo);
        }

    }
    @Test
    public void find(){
        List<ProductInfo> byProductStatus = repository.findByProductStatus(0);
        System.out.println(byProductStatus.size());
        }
}