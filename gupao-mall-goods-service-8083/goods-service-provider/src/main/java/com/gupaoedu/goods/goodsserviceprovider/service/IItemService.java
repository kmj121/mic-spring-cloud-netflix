package com.gupaoedu.goods.goodsserviceprovider.service;

import com.gupaoedu.goods.goodsserviceprovider.domain.ItemStockDo;
import com.gupaoedu.goods.goodsserviceprovider.mapper.entitys.TbItem;
import com.gupaoedu.springcloud.dto.ItemStockDto;

import java.util.List;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/

public interface IItemService {

    List<TbItem> findItemsByIds(List<Long> ids);

    boolean decreaseStock(List<ItemStockDo> itemStockDos);

}
