package com.beiyou.auth.Exception;

public class NOLoginException extends Exception {

    public NOLoginException() {
        super("请登录");
    }
    public NOLoginException(String str){
        super(str);
    }
}
