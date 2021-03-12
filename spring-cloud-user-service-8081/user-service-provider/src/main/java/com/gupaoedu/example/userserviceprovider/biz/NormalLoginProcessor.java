package com.gupaoedu.example.userserviceprovider.biz;

import com.gupaoedu.example.userserviceprovider.controller.dto.AuthLoginDto;
import com.gupaoedu.example.userserviceprovider.controller.enums.LoginTypeEnum;
import com.gupaoedu.example.userserviceprovider.mapper.entitys.TbMember;
import com.gupaoedu.example.userserviceprovider.mapper.entitys.TbMemberExample;
import com.gupaoedu.example.userserviceprovider.mapper.persistence.TbMemberMapper;
import com.gupaoedu.springcloud.exception.BizException;
import com.gupaoedu.springcloud.exception.ValidException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@Slf4j
@Service
public class NormalLoginProcessor extends AbstractLogin{

    @Autowired
    TbMemberMapper tbMemberMapper;

    @Override
    public int getLoginType() {
        return LoginTypeEnum.NORMAL.getCode();
    }

    @Override
    public void validate(AuthLoginDto authLoginDto) {
        if(StringUtils.isBlank(authLoginDto.getUsername())||StringUtils.isBlank(authLoginDto.getPassword())){
            throw new ValidException("帐号或者密码不能为空");
        }
    }

    @Override
    public TbMember doProcessor(AuthLoginDto authLoginDto) {
        log.info("begin NormalLoginProcessor.doProcessor:"+authLoginDto);
        TbMemberExample tbMemberExample=new TbMemberExample();
        tbMemberExample.createCriteria().andStateEqualTo(1).andUsernameEqualTo(authLoginDto.getUsername());
        List<TbMember> members=tbMemberMapper.selectByExample(tbMemberExample);
        if(members==null||members.size()==0){
            throw new BizException("用户名或者密码错误");
        }
        if(!DigestUtils.md5DigestAsHex(authLoginDto.getPassword().getBytes()).equals(members.get(0).getPassword())){
            throw new BizException("用户名或者密码错误");
        }
        return members.get(0);
    }
}
