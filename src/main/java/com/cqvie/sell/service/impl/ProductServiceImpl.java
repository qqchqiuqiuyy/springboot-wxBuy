package com.cqvie.sell.service.impl;

import com.cqvie.sell.dataobject.ProductInfo;
import com.cqvie.sell.dto.CartDto;
import com.cqvie.sell.enums.ProductStatusEnum;
import com.cqvie.sell.enums.ResultEnum;
import com.cqvie.sell.exception.SellException;
import com.cqvie.sell.repository.ProductInfoRepository;
import com.cqvie.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yuyu
 * @since 2017/11/20 18:49
 */
@Service
public class ProductServiceImpl implements ProductService {
    //注入
    @Autowired
    private ProductInfoRepository repository;
    @Override
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    /**
     * 增加库存
     *
     * @param cartDtos
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void increaseStock(List<CartDto> cartDtos) {
        for (CartDto cartDto:cartDtos){

            ProductInfo productInfo = repository.findOne(cartDto.getProductId());
            //判断商品是否存在
            if(productInfo==null){
                throw  new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            Integer i = productInfo.getProductStock() + cartDto.getProductQuantity();
            productInfo.setProductStock(i);
            repository.save(productInfo);
        }
    }
    /**
     * 减去库存
     *
     * @param cartDtos
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decreaseStock(List<CartDto> cartDtos) {

        for (CartDto cartDto:cartDtos){
            ProductInfo productInfo = repository.findOne(cartDto.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            Integer  i = productInfo.getProductStock() - cartDto.getProductQuantity();
            if(i<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR_);
            }
            productInfo.setProductStock(i);
            repository.save(productInfo);
        }

    }
}
