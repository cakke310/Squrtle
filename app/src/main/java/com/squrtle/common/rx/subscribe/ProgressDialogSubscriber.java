package com.squrtle.common.rx.subscribe;

import android.app.ProgressDialog;
import android.content.Context;

import com.squrtle.common.rx.RxErrorHandler;

/**
 * Created by c_xuwei-010 on 2017/4/13.
 */
public abstract class ProgressDialogSubscriber<T> extends ErrorHandlerSubscriber<T> {

    private Context mContext;
    private ProgressDialog dialog;

    public ProgressDialogSubscriber(Context context) {
        super(context);
        //this.mContext = context;
    }


    @Override
    public void onStart() {
        showProgressDialog();
    }

    @Override
    public void onCompleted() {
        dismisPogressDialog();
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        dismisPogressDialog();
    }

    private void initProgressDialog(){
//        if(dialog==null){
//            dialog = new ProgressDialog(mContext);
//            dialog.setMessage("loading...");
//        }

    }

    private void showProgressDialog(){
        initProgressDialog();
//        dialog.show();
    }

    private void dismisPogressDialog(){
        if(dialog!=null && dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
