package com.squrtle.di.module;

import com.squrtle.data.AppInfoModel;
import com.squrtle.data.http.ApiService;
import com.squrtle.presenter.contract.AppInfoContract;
import com.squrtle.ui.adapter.AppInfoAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by c_xuwei-010 on 2017/4/19.
 */
@Module
public class AppInfoModule {
    private AppInfoContract.AppInfoView mView;

    public AppInfoModule(AppInfoContract.AppInfoView mView) {
        this.mView = mView;
    }

    @Provides
    public AppInfoContract.AppInfoView provideView(){
        return mView;
    }

    @Provides
    public AppInfoModel provideModel(ApiService apiService){
        return new AppInfoModel(apiService);
    }


}
