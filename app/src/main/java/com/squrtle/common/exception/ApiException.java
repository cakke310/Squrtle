package com.squrtle.common.exception;

/**
 * Created by c_xuwei-010 on 2017/4/11.
 */
public class ApiException extends BaseException{

    public ApiException(int code, String displayMessage) {
        super(code, displayMessage);
    }
}
