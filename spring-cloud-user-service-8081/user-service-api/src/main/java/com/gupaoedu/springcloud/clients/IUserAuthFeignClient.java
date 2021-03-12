package com.gupaoedu.springcloud.clients;

import com.gupaoedu.springcloud.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@FeignClient(value = "user-service")
public interface IUserAuthFeignClient {

    @GetMapping(value = "/token",consumes = MediaType.APPLICATION_JSON_VALUE)
    R<String> validToken(@RequestParam("token") String token);
}
