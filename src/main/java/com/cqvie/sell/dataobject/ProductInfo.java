package com.cqvie.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author yuyu
 * @since 2017/11/20 18:24
 */
@Entity
@Data
public class ProductInfo {
     @Id
     private String productId;
     //商品名称
     private String productName;
     //单价
     private BigDecimal productPrice;
     //库存
     private  Integer productStock;
     //描述
     private String productDescription;
     //图片
     private  String productIcon;
     //状态0正常1下架
     private  Integer productStatus;
     //类目
     private Integer categoryType;

}
