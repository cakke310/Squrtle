package com.squrtle.presenter;

import com.hwangjr.rxbus.RxBus;
import com.squrtle.bean.LoginBean;
import com.squrtle.common.Constant;
import com.squrtle.common.rx.RxHttpResponseCompat;
import com.squrtle.common.rx.subscribe.ErrorHandlerSubscriber;
import com.squrtle.common.util.ACache;
import com.squrtle.common.util.VerificationUtils;
import com.squrtle.presenter.contract.LoginContract;

import javax.inject.Inject;

/**
 * Created by c_xuwei-010 on 2017/4/24.
 */
public class LoginPresenter extends BasePresenter<LoginContract.ILoginModel,LoginContract.LoginView>{
    @Inject
    public LoginPresenter(LoginContract.ILoginModel mModel, LoginContract.LoginView mView) {
        super(mModel, mView);
    }

    public void login(String phone, String pwd){
        if(!VerificationUtils.matcherPhoneNum(phone)){
            mView.checkPhoneError();
            return;
        }

        mModel.login(phone,pwd).compose(RxHttpResponseCompat.<LoginBean>compatResult())
            .subscribe(new ErrorHandlerSubscriber<LoginBean>(mContext) {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onNext(LoginBean loginBean) {
                    mView.loginSuccess(loginBean);
                    saveUser(loginBean);
                    RxBus.get().post(loginBean.getUser());
                }
            });
    }

    private void saveUser(LoginBean loginBean){
        ACache aCache = ACache.get(mContext);
        aCache.put(Constant.TOKEN,loginBean.getToken());
        aCache.put(Constant.USER,loginBean.getUser());
    }

}
