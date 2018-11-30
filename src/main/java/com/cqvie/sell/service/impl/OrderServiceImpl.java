package com.cqvie.sell.service.impl;

import com.cqvie.sell.converter.OrderMasterDtoConverter;
import com.cqvie.sell.dataobject.OrderDetail;
import com.cqvie.sell.dataobject.OrderMaster;
import com.cqvie.sell.dataobject.ProductInfo;
import com.cqvie.sell.dto.CartDto;
import com.cqvie.sell.dto.OrderDto;
import com.cqvie.sell.enums.OrderStatusEnum;
import com.cqvie.sell.enums.PayStatusEnum;
import com.cqvie.sell.enums.ResultEnum;
import com.cqvie.sell.exception.SellException;
import com.cqvie.sell.repository.OrderDetailRepository;
import com.cqvie.sell.repository.OrderMasterRepository;
import com.cqvie.sell.service.OrderService;
import com.cqvie.sell.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import utils.OrderUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuyu
 * @since 2017/11/22 14:44
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    /**
     * 创建订单
     *
     * @param orderDto
     * @return Order实体
     */
    @Override
    @Transactional
    public OrderDto createOrderMaster(OrderDto orderDto) {
        String orderId = OrderUtil.getOrderNumber("10");
        BigDecimal orderAmount = new BigDecimal(0);
        //查询单价
        for (OrderDetail orderDetail: orderDto.getOrderDetails()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if(productInfo==null){
                throw  new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            //计算总价
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
            orderDetail.setDetailId(OrderUtil.getOrderNumber("10"));
            orderDetail.setOrderId(orderId);
            orderDetail.setProductName(productInfo.getProductName());
            orderDetail.setProductPrice(productInfo.getProductPrice());
            orderDetailRepository.save(orderDetail);
        }

        //OrderMaster
        OrderMaster orderMaster = new OrderMaster();
        //属性拷贝
        orderDto.setOrderId(orderId);
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderAnount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        //写入数据库
        orderMasterRepository.save(orderMaster);
        //扣库存

        List<CartDto> cartDtoList = orderDto.getOrderDetails().stream().map(e -> new CartDto(e.getProductId(),e.getProductQuantity()))
        .collect(Collectors.toList());
        productService.decreaseStock(cartDtoList);
        return orderDto;
    }

    /**
     * 查询单个订单
     *
     * @param orderId
     * @return
     */
    @Override
    public OrderDto findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(orderMaster==null){
            throw  new SellException(ResultEnum.ORDER_NOT_EXIT);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw  new SellException(ResultEnum.ORDERDETAIL_NOT_EXIT);

        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        orderDto.setOrderDetails(orderDetailList);
        return orderDto;
    }

    /**
     * 查询用户订单列表
     *
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);

        List<OrderDto> dtoList = OrderMasterDtoConverter.converter(orderMasterPage.getContent());
        Page<OrderDto> orderDtoPage = new PageImpl<OrderDto>(dtoList,pageable,orderMasterPage.getTotalElements());
        return orderDtoPage;
    }
    /**
     * 取消订单
     *
     * @param orderDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderDto cancleOrder(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();
        //拷贝属性

        //判断订单状态
        if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("取消订单，订单状态不正确orderId={}, orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw  new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        /**
         * orderDto 拷贝的对象
         * orderMater 获取值的对象
         */
        BeanUtils.copyProperties(orderDto,orderMaster);
        //修改订单
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster updateResultOrder = orderMasterRepository.save(orderMaster);
        if(updateResultOrder==null){
            log.error("取消订单更新失败 orderMaster={}",orderMaster);
            throw  new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        if(CollectionUtils.isEmpty(orderDto.getOrderDetails())){
            log.error("【取消订单】订单中无商品详情");
            throw  new SellException(ResultEnum.ORDER_NOT_DETAIL);
        }
        //返回库存
        List<CartDto> cartDtos = orderDto.getOrderDetails().stream().map(e -> new CartDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.increaseStock(cartDtos);
        //如果以支付,退款
        if(orderDto.getOrderStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            //TODO
        }
        return orderDto;
    }

    /**
     * 完结订单
     *
     * @param orderDto
     * @return
     */
    @Override
    public OrderDto finish(OrderDto orderDto) {
        //判断订单状态
        OrderMaster orderMaster = new OrderMaster();
        if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("完结订单，订单状态不正确orderId={}, orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw  new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        orderDto.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        OrderMaster resultOrderMaster = orderMasterRepository.save(orderMaster);
        if(resultOrderMaster==null){
            log.error("完结订单更新失败 orderMaster={}",orderMaster);
            throw  new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        //修改订单状态

        return orderDto;
    }
    /**
     * 支付订单
     *
     * @param orderDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderDto paid(OrderDto orderDto) {
        //判断订单状态
        OrderMaster orderMaster = new OrderMaster();
        if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("支付订单，订单状态不正确orderId={}, orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw  new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        if(!orderDto.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("支付订单，支付状态不正确orderId={}, payStatus={}",orderDto.getOrderId(),orderDto.getPayStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        //支付状态
        BeanUtils.copyProperties(orderDto,orderMaster);
        //修改支付状态
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster resultOrderMaster = orderMasterRepository.save(orderMaster);
        if(resultOrderMaster == null){
            log.error("支付订单，更新失败,orderId={}, payStatus={}",orderDto.getOrderId(),orderDto.getPayStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        return  orderDto;
    }
}
