package com.gupaoedu.goods.goodsserviceprovider.service.impl;

import com.gupaoedu.goods.goodsserviceprovider.domain.ItemStockDo;
import com.gupaoedu.goods.goodsserviceprovider.mapper.entitys.TbItem;
import com.gupaoedu.goods.goodsserviceprovider.mapper.entitys.TbItemExample;
import com.gupaoedu.goods.goodsserviceprovider.mapper.persistence.TbItemMapper;
import com.gupaoedu.goods.goodsserviceprovider.service.IItemService;
import com.gupaoedu.springcloud.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@Service
public class ItemServiceImpl implements IItemService{

    @Autowired
    TbItemMapper tbItemMapper;

    @Override
    public List<TbItem> findItemsByIds(List<Long> ids) {
        TbItemExample tbItemExample=new TbItemExample();
        tbItemExample.createCriteria().andIdIn(ids);
        return tbItemMapper.selectByExample(tbItemExample);
    }

    @Transactional
    @Override
    public boolean decreaseStock(List<ItemStockDo> itemStockDos) {
        List<Long> ids=itemStockDos.stream().map(ItemStockDo::getItemId).collect(Collectors.toList());
        List<TbItem> tbItemList=tbItemMapper.selectStockForUpdate(ids);
        if(tbItemList==null||tbItemList.isEmpty()){
            throw new BizException("未找到对应的商品");
        }
        if(ids.size()!=tbItemList.size()){
            throw new BizException("部分商品不存在");
        }
        itemStockDos.forEach(itemStockDo -> {
            tbItemList.forEach(item->{
                if(Objects.equals(item.getId(),itemStockDo.getItemId())){
                    if(item.getNum()<itemStockDo.getNum()){
                        throw new BizException(itemStockDo.getItemId()+":库存不足");
                    }
                }
                TbItem tbItem=new TbItem();
                tbItem.setId(itemStockDo.getItemId());
                tbItem.setNum(itemStockDo.getNum());
                int row=tbItemMapper.decreaseStock(tbItem);
                if(row<=0){
                    throw new BizException(itemStockDo.getItemId()+":库存不足");
                }
                return;
            });
        });
        return true;
    }
}
