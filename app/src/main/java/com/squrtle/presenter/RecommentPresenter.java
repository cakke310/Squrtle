package com.squrtle.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.squrtle.bean.BaseBean;
import com.squrtle.bean.IndexBean;
import com.squrtle.common.rx.RxErrorHandler;
import com.squrtle.common.rx.RxHttpResponseCompat;
import com.squrtle.common.rx.subscribe.ErrorHandlerSubscriber;
import com.squrtle.common.rx.subscribe.ProgressDialogSubscriber;
import com.squrtle.data.RecommendModel;
import com.squrtle.presenter.contract.RecommendContract;
import com.squrtle.bean.AppInfo;
import com.squrtle.bean.PageBean;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.xml.sax.ErrorHandler;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by c_xuwei-010 on 2017/4/6.
 */
public class RecommentPresenter extends BasePresenter<RecommendModel,RecommendContract.View> {

    private RxErrorHandler mErrorHandler;
    @Inject
    public RecommentPresenter(RecommendModel mModel, RecommendContract.View mView, RxErrorHandler errorHandler) {
        super(mModel, mView);
        this.mErrorHandler = errorHandler;
    }

    public void requestPermission(){
        RxPermissions rxPermissions = new RxPermissions((Activity)mContext);
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if(aBoolean){
                    mView.onRequestPermissionSuccess();
                }
                else {
                    mView.onRequestPermissionError();
                }
            }
        });
    }


    public void requestDatas() {


//        mModel.getApps()
//                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
//                .subscribe(new ProgressDialogSubscriber<PageBean<AppInfo>>(mContext, mErrorHandler) {
//                    @Override
//                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
//                        if(appInfoPageBean!=null){
//                            mView.showResult(appInfoPageBean.getDatas());
//                        } else {
//                            mView.showNodata();
//                        }
//                    }
//                });

        mModel.index().compose(RxHttpResponseCompat.<IndexBean>compatResult())
                .subscribe(new ProgressDialogSubscriber<IndexBean>(mContext,mErrorHandler) {
                    @Override
                    public void onNext(IndexBean indexBean) {
                        mView.showResult(indexBean);
                    }
                });


    }
}
