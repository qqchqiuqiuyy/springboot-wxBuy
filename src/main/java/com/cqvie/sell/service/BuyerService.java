package com.cqvie.sell.service;

import com.cqvie.sell.dto.OrderDto;

/**
 * @author yuyu
 * @since 2017/11/24 13:51
 */
public interface BuyerService {

    OrderDto findBuyerOrder(String openid,String orderId);
    OrderDto cancel(String openid,String orderId);


}
