package com.gupaoedu.example.userserviceprovider.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Map;

/**
 * @Description: jwt加密
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@Slf4j
public class JwtGeneratorUtil {

    private static String SECRET_KEY="gupaoedu";

    private static Key getKeyInstance(){
        SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;
        byte[] keySecretByte=DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key key=new SecretKeySpec(keySecretByte,signatureAlgorithm.getJcaName());
        return key;
    }

    /**
     * shengcheng jwt token
     * @param payLoad
     * @return
     */
    public static String generatorToken(Map<String,Object> payLoad){
        ObjectMapper objectMapper=new ObjectMapper();
        String token=null;
        try {
             token=Jwts.builder().setPayload(objectMapper.writeValueAsString(payLoad)).
                    signWith(SignatureAlgorithm.HS256,getKeyInstance()).compact();
        } catch (JsonProcessingException e) {
            log.error("generatorToken :" +e);
        }
        return token;
    }

    /**
     * 根据token解析出token中的内容
     * @param token
     * @return
     */
    public static Claims parseToken(String token){
        Jws<Claims> claimsJwt=Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(token);
        return claimsJwt.getBody();
    }

}
