package com.squrtle.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.squrtle.ui.BaseView;

import javax.inject.Inject;

/**
 * Created by c_xuwei-010 on 2017/4/6.
 */
public class BasePresenter<M,V extends BaseView> {
    public M mModel;

    public V mView;

    protected Context mContext;

    public BasePresenter(M mModel, V mView) {
        this.mModel = mModel;
        this.mView = mView;
        initContext();


    }

    public void initContext(){
        if(mView instanceof Fragment){
            mContext = ((Fragment) mView).getActivity();
        }
        else {
            mContext = (Activity) mView;
        }
    }
}
