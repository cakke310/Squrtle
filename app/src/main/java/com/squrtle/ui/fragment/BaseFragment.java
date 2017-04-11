package com.squrtle.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squrtle.AppApplication;
import com.squrtle.di.component.AppComponent;
import com.squrtle.presenter.BasePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by c_xuwei-010 on 2017/4/11.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    private Unbinder unbinder;

    private AppApplication mApplication;
    private View rootView;

    @Inject
    T mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(setLayout(),container,false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mApplication = (AppApplication) getActivity().getApplication();
        setupActivityComponent(mApplication.getAppComponent());
        init();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(unbinder!=Unbinder.EMPTY){
            unbinder.unbind();
        }
    }

    public abstract int setLayout();

    public abstract void init();

    public abstract void setupActivityComponent(AppComponent appComponent);

}
