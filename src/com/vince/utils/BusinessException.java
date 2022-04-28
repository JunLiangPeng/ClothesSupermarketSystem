package com.vince.utils;

public class BusinessException extends RuntimeException{
    public BusinessException(){
        super();
    }
    public BusinessException(String msg){
        super(msg);
    }
}
