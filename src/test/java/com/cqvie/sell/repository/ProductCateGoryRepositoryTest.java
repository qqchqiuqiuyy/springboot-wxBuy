package com.cqvie.sell.repository;

import com.cqvie.sell.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author yuyu
 * @since 2017/11/20 17:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCateGoryRepositoryTest {
  @Autowired
  private ProductCateGoryRepository repository;

  @Test
  public void   findOntTest() {
      ProductCategory productCategory =repository.findOne(2);
      System.out.println(productCategory.toString());
  }
    @Test
    public void   dddOntTest() {
    ProductCategory productCategory  =  new ProductCategory();
    productCategory.setCategoryName("测升水升水试222");
    productCategory.setCategoryType(44);
    repository.save(productCategory);
    }
    @Test
    public void   deleteOntTest() {
        repository.delete(1);
  }
  @Test
  public void   findByCategoryTypeIn() {
    List<Integer> integers = Arrays.asList(7,22);
    List<ProductCategory> byCategoryTypeIsIn = repository.findByCategoryTypeIsIn(integers);
    System.out.println(byCategoryTypeIsIn.size());

  }
}