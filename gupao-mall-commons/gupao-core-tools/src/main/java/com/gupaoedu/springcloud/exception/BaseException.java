package com.gupaoedu.springcloud.exception;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
public class BaseException extends RuntimeException{
    protected String code;
    protected String msg;

    public BaseException() {
        super();
    }
    public BaseException(String message) {
        super(message);
        this.msg=message;
    }

    public BaseException(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }
}
