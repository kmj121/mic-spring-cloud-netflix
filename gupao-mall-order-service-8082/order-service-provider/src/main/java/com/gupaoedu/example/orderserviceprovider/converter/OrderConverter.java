package com.gupaoedu.example.orderserviceprovider.converter;

import com.gupaoedu.example.orderserviceprovider.controller.dto.ItemsDto;
import com.gupaoedu.springcloud.dto.ItemStockDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@Mapper(componentModel = "spring")
public interface OrderConverter {

    ItemStockDto itemDtoStockDto(ItemsDto itemsDto);

    List<ItemStockDto> itemsDto2StockDtoList(List<ItemsDto> itemsDtos);
}
