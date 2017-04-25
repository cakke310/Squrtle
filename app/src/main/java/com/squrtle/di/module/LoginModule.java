package com.squrtle.di.module;

import com.squrtle.data.AppInfoModel;
import com.squrtle.data.LoginModel;
import com.squrtle.data.http.ApiService;
import com.squrtle.presenter.contract.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by c_xuwei-010 on 2017/4/25.
 */
@Module
public class LoginModule {
    private LoginContract.LoginView mView;

    public LoginModule(LoginContract.LoginView mView) {
        this.mView = mView;
    }

    @Provides
    public LoginContract.LoginView provideView(){
        return mView;
    }

    @Provides
    public LoginContract.ILoginModel provideModel(ApiService apiService){
        return new LoginModel(apiService);
    }
}
