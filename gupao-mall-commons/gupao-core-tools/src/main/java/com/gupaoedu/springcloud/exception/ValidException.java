package com.gupaoedu.springcloud.exception;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
public class ValidException extends BaseException{

    public ValidException() {
        super();
    }

    public ValidException(String message) {
        super(message);
        this.msg=message;
    }

    public ValidException(String code, String msg) {
        super();
        this.msg=msg;
        this.code=code;
    }
}
