package com.squrtle.common.rx.subscribe;

import android.content.Context;

import com.squrtle.common.exception.ApiException;
import com.squrtle.common.exception.BaseException;
import com.squrtle.common.exception.ErrorMessageFactory;
import com.squrtle.common.rx.RxErrorHandler;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by c_xuwei-010 on 2017/4/13.
 */
public abstract class ErrorHandlerSubscriber<T> extends DefaultSubscriber<T>{

    private RxErrorHandler mRxErrorHandler;
    protected Context mContext;

    public ErrorHandlerSubscriber(Context context){
        this.mContext = context;
        mRxErrorHandler = new RxErrorHandler(mContext);
    }

    @Override
    public void onError(Throwable e) {
        BaseException exception = mRxErrorHandler.handleError(e);

        mRxErrorHandler.showErrorMessage(exception);
    }
}
