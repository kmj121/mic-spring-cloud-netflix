package com.gupaoedu.example.orderserviceprovider.controller;

import com.gupaoedu.example.orderserviceprovider.controller.dto.OrderDto;
import com.gupaoedu.example.orderserviceprovider.service.IOrderService;
import com.gupaoedu.springcloud.api.R;
import com.gupaoedu.springcloud.clients.IGoodsFeignClient;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@RestController
public class OrderController {

    @Autowired
    IOrderService orderService;

    @PostMapping("/order")
    public R order(@RequestBody @Validated OrderDto orderDto, BindingResult bindingResult){
        orderDto.validData(bindingResult);
        String orderId=orderService.createOrder(orderDto);
        return new R.Builder<>().setData(orderId).buildOk();
    }
}
