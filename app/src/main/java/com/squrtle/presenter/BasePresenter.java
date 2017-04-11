package com.squrtle.presenter;

import com.squrtle.ui.BaseView;

/**
 * Created by c_xuwei-010 on 2017/4/6.
 */
public class BasePresenter<M,V extends BaseView> {
    public M mModel;

    public V mView;

    public BasePresenter(M mModel, V mView) {
        this.mModel = mModel;
        this.mView = mView;
    }
}
