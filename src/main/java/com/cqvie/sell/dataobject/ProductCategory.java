package com.cqvie.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author yuyu
 * @since 2017/11/20 17:01
 */
@Entity
@DynamicUpdate //动态修改
@Data
public class ProductCategory {
    //类目id
    @Id
    @GeneratedValue
    private Integer categoryId;
    //类目名称
    private String categoryName;
    //类目编号
    private Integer categoryType;
    //修改时间
    private Date updateTime;
    //创建时间
    private Date createTime;

    public ProductCategory() {
    }
}
