package com.squrtle.di.module;

import com.squrtle.presenter.contract.AppInfoContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by c_xuwei-010 on 2017/4/27.
 */
@Module(includes = {AppModelModule.class})
public class AppDetailModule {
    private AppInfoContract.AppDetailView mView;

    public AppDetailModule(AppInfoContract.AppDetailView mView) {
        this.mView = mView;
    }

    @Provides
    public AppInfoContract.AppDetailView provideView(){
        return mView;
    }


}
