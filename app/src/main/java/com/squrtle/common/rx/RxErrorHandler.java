package com.squrtle.common.rx;

import android.content.Context;
import android.widget.Toast;

import com.squrtle.common.exception.ApiException;
import com.squrtle.common.exception.BaseException;
import com.squrtle.common.exception.ErrorMessageFactory;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by c_xuwei-010 on 2017/4/13.
 */
public class RxErrorHandler {

    private Context context;

    public RxErrorHandler(Context context) {
        this.context = context;
    }

    public BaseException handleError(Throwable e){
        BaseException exception = new BaseException();

        if(e instanceof ApiException){
            exception.setCode(((ApiException) e).getCode());
        }
        else if(e instanceof SocketException){
            exception.setCode(BaseException.SOCKET_ERROR);
        }
        else if(e instanceof SocketTimeoutException){
            exception.setCode(BaseException.SOCKET_TIMEOUT_ERROR);
        }
        else if(e instanceof HttpException){
            exception.setCode(BaseException.HTTP_ERROR);
        }
        else {
            exception.setCode(BaseException.UNKNOWN_ERROR);
        }

        exception.setDisplayMessage(ErrorMessageFactory.create(context,exception.getCode()));

        return exception;

    }

    public void showErrorMessage(BaseException e){
        Toast.makeText(context,e.getDisplayMessage(),Toast.LENGTH_SHORT).show();
    }
}
