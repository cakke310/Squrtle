package com.squrtle.ui.atcitivy;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.squrtle.AppApplication;
import com.squrtle.di.component.AppComponent;
import com.squrtle.presenter.BasePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by c_xuwei-010 on 2017/4/11.
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    private Unbinder unbinder;
    private AppApplication application;

    @Inject
    T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        unbinder = ButterKnife.bind(this);
        this.application = (AppApplication) getApplication();
        setupActivityComponent(application.getAppComponent());

        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder!=Unbinder.EMPTY){
            unbinder.unbind();
        }
    }

    public abstract int setLayout();

    public abstract void setupActivityComponent(AppComponent appComponent);

    public abstract void init();

}
