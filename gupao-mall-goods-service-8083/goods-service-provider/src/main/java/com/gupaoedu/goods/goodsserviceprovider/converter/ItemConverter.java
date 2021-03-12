package com.gupaoedu.goods.goodsserviceprovider.converter;

import com.gupaoedu.goods.goodsserviceprovider.domain.ItemStockDo;
import com.gupaoedu.goods.goodsserviceprovider.mapper.entitys.TbItem;
import com.gupaoedu.springcloud.dto.ItemStockDto;
import com.gupaoedu.springcloud.vo.ItemDetailVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@Mapper(componentModel = "spring")
public interface ItemConverter {

    @Mappings({})
    ItemDetailVo tbItemDetail2Vo(TbItem tbItem);

    List<ItemDetailVo> itemDetail2VoList(List<TbItem> tbItemList);

    @Mappings({})
    ItemStockDo itemStockDto2Do(ItemStockDto itemStockDto);

    List<ItemStockDo> itemStockDo2DoList(List<ItemStockDto> itemStockDtos);
}
