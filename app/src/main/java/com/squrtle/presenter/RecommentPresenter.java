package com.squrtle.presenter;

import com.squrtle.data.RecommendModel;
import com.squrtle.presenter.contract.RecommendContract;
import com.squrtle.bean.AppInfo;
import com.squrtle.bean.PageBean;

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

    @Inject
    public RecommentPresenter(RecommendModel mModel, RecommendContract.View mView) {
        super(mModel, mView);
    }
//    private RecommendContract.View mView;
//    private RecommendModel mModel;

//    @Inject
//    public RecommentPresenter(RecommendContract.View mView, RecommendModel model) {
//        this.mView = mView;
//        mModel = model;
//    }


    public void requestDatas() {
        mView.showLoading();

        mModel.getApps()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PageBean<AppInfo>>() {
            @Override
            public void onStart() {
                mView.showLoading();
            }

            @Override
            public void onCompleted() {
                mView.dismissLoading();
            }

            @Override
            public void onError(Throwable e) {
                mView.dismissLoading();
            }

            @Override
            public void onNext(PageBean<AppInfo> response) {
                if(response!=null){
                    mView.showResult(response.getDatas());
                }
                else {
                    mView.showNodata();
                }
            }
        });


//        mModel.getApps(new Callback<PageBean<AppInfo>>() {
//            @Override
//            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
//                if(response!=null){
//                    mView.showResult(response.body().getDatas());
//                }
//                else {
//                    mView.showNodata();
//                }
//                mView.dismissLoading();
//            }
//
//            @Override
//            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
//                mView.dismissLoading();
//                mView.showError(t.getMessage());
//            }
//        });
    }
}
