package com.gupaoedu.example.userserviceprovider.controller.dto;

import com.gupaoedu.springcloud.exception.ValidException;
import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.constraints.NotNull;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@Data
public class AuthLoginDto {

    private String username;
    private String password;

    private String phone;
    private String code;

    private String openId;

    /**
     * @see com.gupaoedu.example.userserviceprovider.controller.enums.LoginTypeEnum
     */
    @NotNull(message = "登录类型不能为空")
    private Integer loginType;

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
