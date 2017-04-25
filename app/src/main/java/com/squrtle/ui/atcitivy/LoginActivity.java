package com.squrtle.ui.atcitivy;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.squrtle.R;
import com.squrtle.bean.LoginBean;
import com.squrtle.di.component.AppComponent;
import com.squrtle.di.component.DaggerLoginComponent;
import com.squrtle.di.module.LoginModule;
import com.squrtle.presenter.LoginPresenter;
import com.squrtle.presenter.contract.LoginContract;
import com.squrtle.ui.widget.LoadingButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * Created by c_xuwei-010 on 2017/4/17.
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.LoginView{


    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.txt_mobi)
    EditText txtMobi;
    @BindView(R.id.view_mobi_wrapper)
    TextInputLayout viewMobiWrapper;
    @BindView(R.id.txt_password)
    EditText txtPassword;
    @BindView(R.id.view_password_wrapper)
    TextInputLayout viewPasswordWrapper;
    @BindView(R.id.btn_login)
    LoadingButton btnLogin;

    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent.builder().appComponent(appComponent).loginModule(new LoginModule(this))
                .build().inject(this);
    }

    @Override
    public void init() {
        initView();
    }

    private void initView() {
        toolBar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.md_white_1000)
                        )
        );

        Observable<CharSequence> obMobi = RxTextView.textChanges(txtMobi);
        Observable<CharSequence> obPassword = RxTextView.textChanges(txtPassword);

        Observable.combineLatest(obMobi, obPassword, new Func2<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean call(CharSequence mobi, CharSequence pwd) {
                return isPhoneValid(mobi.toString()) && isPasswordValid(pwd.toString());
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                RxView.enabled(btnLogin).call(aBoolean);
            }
        });

        RxView.clicks(btnLogin).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                mPresenter.login(txtMobi.getText().toString().trim(),txtPassword.getText().toString().trim());
            }
        });

    }

    private boolean isPhoneValid(String phone){
        return phone.length() == 11;
    }

    private boolean isPasswordValid(String password){
        return password.length() >= 6;
    }


    @OnClick(R.id.btn_login)
    public void onClick() {
    }

    @Override
    public void checkPhoneError() {
        viewMobiWrapper.setError("手机号格式不正确");
    }

    @Override
    public void checkPhoneSuccess() {
        viewMobiWrapper.setError("");
        viewMobiWrapper.setErrorEnabled(false);
    }

    @Override
    public void loginSuccess(LoginBean bean) {
        this.finish();
    }
}
