package com.gupaoedu.example.userserviceprovider.biz;

import com.gupaoedu.example.userserviceprovider.controller.dto.AuthLoginDto;
import com.gupaoedu.example.userserviceprovider.controller.enums.LoginTypeEnum;
import com.gupaoedu.example.userserviceprovider.mapper.entitys.TbMember;
import com.netflix.discovery.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@Service
public class PhoneCodeLoginProcessor extends AbstractLogin{
    @Override
    public int getLoginType() {
        return LoginTypeEnum.PHONE_CODE.getCode();
    }
    @Override
    public void validate(AuthLoginDto authLoginDto) {
        if(StringUtils.isBlank(authLoginDto.getPhone())){
            //TODO
        }
    }

    @Override
    public TbMember doProcessor(AuthLoginDto authLoginDto) {
        return null;
    }
}
