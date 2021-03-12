package com.gupaoedu.example.userserviceprovider.feign;

import com.gupaoedu.example.userserviceprovider.utils.JwtGeneratorUtil;
import com.gupaoedu.springcloud.api.R;
import com.gupaoedu.springcloud.clients.IUserAuthFeignClient;
import com.gupaoedu.springcloud.exception.ValidException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RestController;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@RestController
public class UserAuthFeignClient implements IUserAuthFeignClient{

    @Override
    public R<String> validToken(String token) {
        if(StringUtils.isBlank(token)){
            throw new ValidException("token为空");
        }
        try {
            Claims claims = JwtGeneratorUtil.parseToken(token);
            return new R.Builder().setData(claims.get("uid").toString()).buildOk();
        }catch (ExpiredJwtException e){
            return new R.Builder().buildCustomize("token已过期");
        }catch (SignatureException e){
            return new R.Builder().buildCustomize("签名校验失败");
        }
    }
}
