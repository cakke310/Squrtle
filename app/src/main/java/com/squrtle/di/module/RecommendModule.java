package com.squrtle.di.module;

import com.squrtle.data.RecommendModel;
import com.squrtle.data.http.ApiService;
import com.squrtle.presenter.RecommentPresenter;
import com.squrtle.presenter.contract.RecommendContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by c_xuwei-010 on 2017/4/6.
 */
@Module
public class RecommendModule {

    private RecommendContract.View mView;

    public RecommendModule(RecommendContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public RecommendContract.View provideView(){
        return mView;
    }

    @Provides
    public RecommendModel provideModel(ApiService apiService){
        return new RecommendModel(apiService);
    }

    @Provides
    public RecommendContract.Presenter providePresenter(RecommendContract.View mView, RecommendModel model){
        return new RecommentPresenter(mView,model);
    }




}
