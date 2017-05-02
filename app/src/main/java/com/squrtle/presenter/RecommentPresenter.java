package com.squrtle.presenter;

import android.Manifest;
import android.app.Activity;

import com.squrtle.bean.IndexBean;
import com.squrtle.common.rx.RxErrorHandler;
import com.squrtle.common.rx.RxHttpResponseCompat;
import com.squrtle.common.rx.subscribe.ErrorHandlerSubscriber;
import com.squrtle.common.rx.subscribe.ProgressDialogSubscriber;
import com.squrtle.data.AppInfoModel;
import com.squrtle.presenter.contract.AppInfoContract;
import com.tbruyelle.rxpermissions.RxPermissions;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * Created by c_xuwei-010 on 2017/4/6.
 */
public class RecommentPresenter extends BasePresenter<AppInfoModel,AppInfoContract.View> {

    private RxErrorHandler mErrorHandler;
    @Inject
    public RecommentPresenter(AppInfoModel mModel, AppInfoContract.View mView, RxErrorHandler errorHandler) {
        super(mModel, mView);
        this.mErrorHandler = errorHandler;
    }

    public void requestPermission(){
        mView.onRequestPermissionSuccess();
//        RxPermissions rxPermissions = RxPermissions.getInstance((Activity)mContext);
//        rxPermissions.request(Manifest.permission.READ_PHONE_STATE).subscribe(new Consumer<Boolean>() {
//            @Override
//            public void accept(Boolean aBoolean) {
//                if(aBoolean){
//                    mView.onRequestPermissionSuccess();
//                }
//                else {
//                    mView.onRequestPermissionError();
//                }
//            }
//        });
    }


    public void requestDatas() {



        mModel.index().compose(RxHttpResponseCompat.<IndexBean>compatResult())
                .subscribe(new ErrorHandlerSubscriber<IndexBean>(mContext) {


                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(IndexBean indexBean) {
                        mView.showResult(indexBean);
                    }

                    @Override
                    public void onComplete() {

                    }
                });





    }
}
