package com.gupaoedu.example.userserviceprovider.biz;

import com.gupaoedu.example.userserviceprovider.controller.dto.AuthLoginDto;
import com.gupaoedu.springcloud.api.R;
import com.gupaoedu.springcloud.exception.BizException;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
public interface Login {

    R doLogin(AuthLoginDto authLoginDto) throws BizException;
}
