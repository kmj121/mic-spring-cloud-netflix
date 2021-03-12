package com.gupaoedu.example.userserviceprovider.controller;

import com.gupaoedu.example.userserviceprovider.biz.AbstractLogin;
import com.gupaoedu.example.userserviceprovider.biz.Login;
import com.gupaoedu.example.userserviceprovider.controller.dto.AuthLoginDto;
import com.gupaoedu.springcloud.api.R;
import com.gupaoedu.springcloud.exception.BizException;
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
public class LoginController {

    @PostMapping("/login")
    public R loginAuth(@RequestBody @Validated AuthLoginDto authLoginDto, BindingResult bindingResult){
        authLoginDto.validData(bindingResult);
        //登录逻辑?
        Login login= AbstractLogin.loginMap.get(authLoginDto.getLoginType());
        if(login==null){
            throw new BizException("暂不支持该种登录类型");
        }
        return login.doLogin(authLoginDto);
    }
}
