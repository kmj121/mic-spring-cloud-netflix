package com.gupaoedu.example.orderserviceprovider.controller.dto;

import com.gupaoedu.springcloud.exception.ValidException;
import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@Data
public class OrderDto {
    @NotNull(message = "name不能为空")
    private String name;
    @NotNull(message = "tel不能为空")
    private String tel;
    private String userId;
    @NotEmpty(message = "商品列表为空")
    private List<ItemsDto> items;

    public void validData(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder stringBuilder=new StringBuilder();
            for(ObjectError oe:bindingResult.getAllErrors()){
                stringBuilder.append(oe.getDefaultMessage()+"\n");
            }
            throw new ValidException(stringBuilder.toString());
        }
    }
}
