package com.squrtle.presenter;

import com.squrtle.data.RecommendModel;
import com.squrtle.presenter.contract.RecommendContract;
import com.squrtle.ui.bean.AppInfo;
import com.squrtle.ui.bean.PageBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by c_xuwei-010 on 2017/4/6.
 */
public class RecommentPresenter implements RecommendContract.Presenter{
    private RecommendContract.View mView;
    private RecommendModel mModel;

    public RecommentPresenter(RecommendContract.View mView, RecommendModel model) {
        this.mView = mView;
        mModel = model;
    }


    @Override
    public void requestDatas() {
        mView.showLoading();

        mModel.getApps(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                if(response!=null){
                    mView.showResult(response.body().getDatas());
                }
                else {
                    mView.showNodata();
                }
                mView.dismissLoading();
            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
                mView.dismissLoading();
                mView.showError(t.getMessage());
            }
        });
    }
}
