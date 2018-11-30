package com.cqvie.sell.controller;

import com.cqvie.sell.dataobject.ProductCategory;
import com.cqvie.sell.dataobject.ProductInfo;
import com.cqvie.sell.service.CateGoryService;
import com.cqvie.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.ResultVoUtils;
import vo.ProductInfoVo;
import vo.ProductVo;
import vo.ResultVo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuyu
 * @since 2017/11/20 19:08
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CateGoryService cateGoryService;

    @GetMapping("/list")
    public  ResultVo list(){

        //查询上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //查询类别
        //类目编号
        List<Integer> cateGoryType = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCateGoryTypeIn = cateGoryService.findByCateGoryTypeIn(cateGoryType);
        //数据拼装
        List<ProductVo> productVoList = new ArrayList<ProductVo>();
        for (ProductCategory productCategory:
             productCateGoryTypeIn) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVo> productInfoVosList= new ArrayList<ProductInfoVo>();
            for (ProductInfo product:
               productInfoList  ) {
                    if(product.getCategoryType().equals(productCategory.getCategoryType())){
                        ProductInfoVo productInfoVo = new ProductInfoVo();
                        productInfoVo.setProductId(product.getProductId());
                        productInfoVo.setProductName(product.getProductName());
                        productInfoVo.setIcon(product.getProductIcon());
                        productInfoVo.setDescription(product.getProductDescription());
                        productInfoVo.setProductPrice(product.getProductPrice());
                        productInfoVosList.add(productInfoVo);
                    }
            }
            productVo.setProductInfoVos(productInfoVosList);
            productVoList.add(productVo);
        }
          return   ResultVoUtils.success(productVoList);
    }
}
