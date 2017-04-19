package com.squrtle.di.module;

import com.squrtle.data.AppInfoModel;
import com.squrtle.data.http.ApiService;
import com.squrtle.presenter.contract.AppInfoContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by c_xuwei-010 on 2017/4/19.
 */
@Module
public class TopListModule {
    private AppInfoContract.TopListView mView;

    public TopListModule(AppInfoContract.TopListView mView) {
        this.mView = mView;
    }

    @Provides
    public AppInfoContract.TopListView provideView(){
        return mView;
    }

    @Provides
    public AppInfoModel provideModel(ApiService apiService){
        return new AppInfoModel(apiService);
    }
}
