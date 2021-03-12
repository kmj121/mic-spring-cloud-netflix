package com.gupaoedu.springcloud.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@Data
public class ItemDetailVo {

    private Long id;

    private String title;

    private BigDecimal price;

    private Integer num;

    private String image;

    private Long cid;

}
