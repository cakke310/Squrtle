package com.squrtle.common.rx.subscribe;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.squrtle.common.exception.BaseException;
import com.squrtle.common.rx.RxErrorHandler;
import com.squrtle.ui.activity.LoginActivity;

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
        if(exception==null){
            Log.e("ErrorHandlerSubscriber", e.getMessage());
        }
        else {
            if(exception.getCode() == BaseException.ERROR_TOKEN){
                toLogin();
            }
        }

        mRxErrorHandler.showErrorMessage(exception);
    }

    private void toLogin() {

        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }
}
