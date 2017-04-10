package com.squrtle.presenter.contract;

import com.squrtle.ui.BaseView;
import com.squrtle.presenter.BasePresenter;
import com.squrtle.bean.AppInfo;

import java.util.List;

/**
 * Created by c_xuwei-010 on 2017/4/6.
 */
public interface RecommendContract {
    interface View extends BaseView{


        void showNodata();
        void showError(String msg);
        void showResult(List<AppInfo> datas);
    }

    interface Presenter extends BasePresenter{
        public void requestDatas();
    }


}
