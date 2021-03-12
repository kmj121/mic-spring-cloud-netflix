package com.gupaoedu.example.orderserviceprovider.service.impl;

import com.gupaoedu.example.orderserviceprovider.controller.dto.ItemsDto;
import com.gupaoedu.example.orderserviceprovider.controller.dto.OrderDto;
import com.gupaoedu.example.orderserviceprovider.converter.OrderConverter;
import com.gupaoedu.example.orderserviceprovider.mapper.entitys.TbOrder;
import com.gupaoedu.example.orderserviceprovider.mapper.entitys.TbOrderItem;
import com.gupaoedu.example.orderserviceprovider.mapper.persistence.TbOrderItemMapper;
import com.gupaoedu.example.orderserviceprovider.mapper.persistence.TbOrderMapper;
import com.gupaoedu.example.orderserviceprovider.service.IOrderService;
import com.gupaoedu.springcloud.api.R;
import com.gupaoedu.springcloud.clients.IGoodsFeignClient;
import com.gupaoedu.springcloud.exception.BizException;
import com.gupaoedu.springcloud.vo.ItemDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@Service
public class OrderServiceImpl implements IOrderService{

    @Autowired
    IGoodsFeignClient goodsFeignClient;

    @Autowired
    TbOrderMapper tbOrderMapper;

    @Autowired
    TbOrderItemMapper tbOrderItemMapper;

    @Autowired
    OrderConverter orderConverter;

    @Transactional
    @Override
    public String createOrder(OrderDto orderDto) {
        /**
         * 1. 锁库存
         * 2. 查询商品信息
         * 3. 创建订单
         */
        R r=goodsFeignClient.decreaseStock(orderConverter.itemsDto2StockDtoList(orderDto.getItems()));
        if(r.getCode()!=200){
            throw new BizException(r.getMsg());
        }
        List<Long> ids=orderDto.getItems().stream().map(dto->Long.parseLong(dto.getItemId())).collect(Collectors.toList());
        R<List<ItemDetailVo>> listR=goodsFeignClient.getItemsByIds(ids);
        BigDecimal totalPrice=new BigDecimal(0);
        String orderId= UUID.randomUUID().toString().replace("-","");
        for(ItemsDto itemsDto:orderDto.getItems()){
            for(ItemDetailVo itemDetailVo:listR.getData()){
                if(itemDetailVo.getId().toString().equals(itemsDto.getItemId())){
                    BigDecimal totalFee=itemDetailVo.getPrice().multiply(BigDecimal.valueOf(itemsDto.getNum()));
                    totalPrice=totalPrice.add(totalFee);
                    TbOrderItem tbOrderItem=new TbOrderItem();
                    tbOrderItem.setItemId(itemDetailVo.getId());
                    tbOrderItem.setNum(itemDetailVo.getNum());
                    tbOrderItem.setCreateTime(new Date());
                    tbOrderItem.setOrderId(orderId);
                    tbOrderItem.setPicPath(itemDetailVo.getImage());
                    tbOrderItem.setPrice(itemDetailVo.getPrice());
                    tbOrderItem.setTotalFee(totalFee);
                    tbOrderItem.setStatus(1);
                    tbOrderItem.setTitle(itemDetailVo.getTitle());
                    tbOrderItem.setId(UUID.randomUUID().toString().replace("-",""));
                    tbOrderItemMapper.insert(tbOrderItem);
                }
            }
        }
        TbOrder tbOrder=new TbOrder();
        tbOrder.setOrderId(orderId);
        tbOrder.setPayment(totalPrice);
        tbOrder.setPaymentTime(new Date());
        tbOrder.setStatus(0);
        tbOrder.setCreateTime(new Date());
        tbOrder.setUpdateTime(new Date());
        tbOrder.setUserId(1000000l);
        tbOrder.setOrderId(orderId);
        tbOrderMapper.insert(tbOrder);
        return orderId;
    }
}
