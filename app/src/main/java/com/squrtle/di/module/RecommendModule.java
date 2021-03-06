package com.squrtle.di.module;

import com.squrtle.data.AppInfoModel;
import com.squrtle.data.http.ApiService;
import com.squrtle.presenter.contract.AppInfoContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by c_xuwei-010 on 2017/4/6.
 */
@Module
public class RecommendModule {

    private AppInfoContract.View mView;

    public RecommendModule(AppInfoContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public AppInfoContract.View provideView(){
        return mView;
    }

    @Provides
    public AppInfoModel provideModel(ApiService apiService){
        return new AppInfoModel(apiService);
    }

//    @Provides
//    public AppInfoContract.Presenter providePresenter(AppInfoContract.View mView, AppInfoModel model){
//        return new RecommentPresenter(mView,model);
//    }




}
