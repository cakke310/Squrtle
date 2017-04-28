package com.squrtle.presenter.contract;

import com.squrtle.bean.IndexBean;
import com.squrtle.bean.PageBean;
import com.squrtle.ui.BaseView;
import com.squrtle.presenter.BasePresenter;
import com.squrtle.bean.AppInfo;

import java.util.List;

/**
 * Created by c_xuwei-010 on 2017/4/6.
 */
public interface AppInfoContract {
    interface View extends BaseView{


        void showResult(IndexBean indexBean);

        void onRequestPermissionSuccess();
        void onRequestPermissionError();
    }

    interface AppInfoView extends BaseView{

        void  showResult(PageBean<AppInfo> page);

        void onLoadMoreComplete();


    }

    interface AppDetailView extends BaseView{
        void showAppDetail(AppInfo appInfo);
    }




}
