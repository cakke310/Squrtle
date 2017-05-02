package com.squrtle.presenter.contract;

import com.squrtle.bean.BaseBean;
import com.squrtle.bean.LoginBean;
import com.squrtle.ui.BaseView;

import io.reactivex.Observable;


/**
 * Created by c_xuwei-010 on 2017/4/24.
 */
public interface LoginContract {

    public interface ILoginModel{
        Observable<BaseBean<LoginBean>> login(String phone, String pwd);
    }

    public interface LoginView extends BaseView{
        void checkPhoneError();
        void checkPhoneSuccess();

        void loginSuccess(LoginBean bean);
    }
}
