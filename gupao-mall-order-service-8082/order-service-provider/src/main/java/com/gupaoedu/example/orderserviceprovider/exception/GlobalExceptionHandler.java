package com.gupaoedu.example.orderserviceprovider.exception;

import com.gupaoedu.springcloud.api.R;
import com.gupaoedu.springcloud.exception.ValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R handleException(Exception e, HttpServletRequest request){
        log.info("GlobalExceptionHandler.handleException:{},{}",request.getRequestURI(),e);
        String msg="系统繁忙："+e.getMessage();
        if(e instanceof ValidException){
            msg=e.getMessage();
        }
        return new R.Builder().buildCustomize(msg);
    }
}
