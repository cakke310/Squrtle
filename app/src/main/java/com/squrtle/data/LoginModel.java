package com.squrtle.data;

import com.squrtle.bean.BaseBean;
import com.squrtle.bean.LoginBean;
import com.squrtle.bean.requestbean.LoginRequestBean;
import com.squrtle.data.http.ApiService;
import com.squrtle.presenter.contract.LoginContract;

import io.reactivex.Observable;


/**
 * Created by c_xuwei-010 on 2017/4/25.
 */
public class LoginModel implements LoginContract.ILoginModel {
    private ApiService mApiService;

    public LoginModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    @Override
    public Observable<BaseBean<LoginBean>> login(String phone, String pwd) {
        LoginRequestBean param = new LoginRequestBean();
        param.setEmail(phone);
        param.setPassword(pwd);
        return mApiService.login(param);
    }
}
