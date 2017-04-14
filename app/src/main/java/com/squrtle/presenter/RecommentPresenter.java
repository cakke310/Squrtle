package com.squrtle.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.squrtle.bean.BaseBean;
import com.squrtle.common.rx.RxErrorHandler;
import com.squrtle.common.rx.RxHttpResponseCompat;
import com.squrtle.common.rx.subscribe.ErrorHandlerSubscriber;
import com.squrtle.common.rx.subscribe.ProgressDialogSubscriber;
import com.squrtle.data.RecommendModel;
import com.squrtle.presenter.contract.RecommendContract;
import com.squrtle.bean.AppInfo;
import com.squrtle.bean.PageBean;

import org.xml.sax.ErrorHandler;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
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
//    private RecommendContract.View mView;
//    private RecommendModel mModel;

//    @Inject
//    public RecommentPresenter(RecommendContract.View mView, RecommendModel model) {
//        this.mView = mView;
//        mModel = model;
//    }


    public void requestDatas() {

        Activity activity = null;
        if(mView instanceof Fragment){
            Log.e("fragment view","");
            activity = ((Fragment) mView).getActivity();
        }
        else {
            Log.e("activity view","");
            activity = (Activity) mView;
        }

//        mModel.getApps()
//                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
//                .subscribe(new ErrorHandlerSubscriber<PageBean<AppInfo>>(mErrorHandler) {
//                    @Override
//                    public void onStart() {
//                        mView.showLoading();
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        mView.dismissLoading();
//                    }
//
//                    @Override
//                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
//                        if(appInfoPageBean!=null){
//                            mView.showResult(appInfoPageBean.getDatas());
//                        } else {
//                            mView.showNodata();
//                        }
//                    }
//                });

        mModel.getApps()
                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(new ProgressDialogSubscriber<PageBean<AppInfo>>(activity, mErrorHandler) {
                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                        if(appInfoPageBean!=null){
                            mView.showResult(appInfoPageBean.getDatas());
                        } else {
                            mView.showNodata();
                        }
                    }
                });


    }
}
