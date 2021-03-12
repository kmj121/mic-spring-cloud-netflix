package com.gupaoedu.goods.goodsserviceprovider.feign;

import com.gupaoedu.goods.goodsserviceprovider.converter.ItemConverter;
import com.gupaoedu.goods.goodsserviceprovider.domain.ItemStockDo;
import com.gupaoedu.goods.goodsserviceprovider.mapper.entitys.TbItem;
import com.gupaoedu.goods.goodsserviceprovider.service.IItemService;
import com.gupaoedu.springcloud.api.R;
import com.gupaoedu.springcloud.clients.IGoodsFeignClient;
import com.gupaoedu.springcloud.dto.ItemStockDto;
import com.gupaoedu.springcloud.vo.ItemDetailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@Slf4j
@RestController
public class GoodsFeignClient implements IGoodsFeignClient{

    @Autowired
    IItemService itemService;

    @Autowired
    ItemConverter itemConverter;

    @Override
    public R<List<ItemDetailVo>> getItemsByIds(List<Long> ids) {
        log.info("begin GoodsFeignClient.getItemsByIds:"+ids);
        List<TbItem> itemList=itemService.findItemsByIds(ids);
        List<ItemDetailVo> itemDetailVos=itemConverter.itemDetail2VoList(itemList);
        return new R.Builder<List<ItemDetailVo>>().setData(itemDetailVos).buildOk();
    }

    @Override
    public R decreaseStock(List<ItemStockDto> itemStockDtos) {
        log.info("begin GoodsFeignClient.decreaseStock:"+itemStockDtos);
        List<ItemStockDo> itemStockDos=itemConverter.itemStockDo2DoList(itemStockDtos);
        boolean rs=itemService.decreaseStock(itemStockDos);
        return new R.Builder<>().buildOk();
    }
}
