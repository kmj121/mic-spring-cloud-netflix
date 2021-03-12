package com.gupaoedu.example.userserviceprovider.biz;

import com.gupaoedu.example.userserviceprovider.controller.dto.AuthLoginDto;
import com.gupaoedu.example.userserviceprovider.controller.enums.LoginTypeEnum;
import com.gupaoedu.example.userserviceprovider.mapper.entitys.TbMember;
import org.springframework.stereotype.Service;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@Service
public class PhonePwdLoginProcessor extends AbstractLogin{
    @Override
    public int getLoginType() {
        return LoginTypeEnum.PHONE_PWD.getCode();
    }

    @Override
    public void validate(AuthLoginDto authLoginDto) {

    }

    @Override
    public TbMember doProcessor(AuthLoginDto authLoginDto) {
        return null;
    }
}
