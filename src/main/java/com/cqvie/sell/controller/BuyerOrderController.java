package com.cqvie.sell.controller;

import com.cqvie.sell.converter.OrderFromDtoConverter;
import com.cqvie.sell.dto.OrderDto;
import com.cqvie.sell.enums.ResultEnum;
import com.cqvie.sell.exception.SellException;
import com.cqvie.sell.form.OrderForm;
import com.cqvie.sell.service.BuyerService;
import com.cqvie.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utils.ResultVoUtils;
import vo.ResultVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuyu
 * @since 2017/11/23 15:25
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private BuyerService buyerService;

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     *
     * @param orderForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/create")
    public ResultVo<Map<String, String>> create(@Validated OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确,orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        //对象转换
        OrderDto orderDto = OrderFromDtoConverter.converter(orderForm);
        //判断明细列表是否为空
        if (CollectionUtils.isEmpty(orderDto.getOrderDetails())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_NOT);
        }
        OrderDto createResult = orderService.createOrderMaster(orderDto);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());
        return ResultVoUtils.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVo<List<OrderDto>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单类别】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        //
        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderDto> orderList = orderService.findList(openid, pageRequest);
        return ResultVoUtils.success(orderList.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVo<OrderDto> detail(@RequestParam("openId") String openId,
                                     @RequestParam("orderId") String orderId
    ) {
        OrderDto buyerOrder = buyerService.findBuyerOrder(openId, orderId);
        return ResultVoUtils.success(buyerOrder);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVo cancel(@RequestParam("openId") String openid,
                           @RequestParam("orderId") String orderId) {
        OrderDto orderDto = buyerService.cancel(openid, orderId);
        return ResultVoUtils.success();
    }
}
