package com.gupaoedu.example.orderserviceprovider.service;

import com.gupaoedu.example.orderserviceprovider.controller.dto.OrderDto;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
public interface IOrderService {

    /**
     * 下单
     * @param orderDto
     * @return
     */
    String createOrder(OrderDto orderDto);
}
