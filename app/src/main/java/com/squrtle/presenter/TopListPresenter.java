package com.squrtle.presenter;

import com.squrtle.bean.AppInfo;
import com.squrtle.bean.PageBean;
import com.squrtle.common.rx.RxErrorHandler;
import com.squrtle.common.rx.RxHttpResponseCompat;
import com.squrtle.common.rx.subscribe.ErrorHandlerSubscriber;
import com.squrtle.common.rx.subscribe.ProgressDialogSubscriber;
import com.squrtle.data.AppInfoModel;
import com.squrtle.presenter.contract.AppInfoContract;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by c_xuwei-010 on 2017/4/19.
 */
public class TopListPresenter extends BasePresenter<AppInfoModel,AppInfoContract.TopListView> {
    private RxErrorHandler mErrorHandler;
    @Inject
    public TopListPresenter(AppInfoModel mModel, AppInfoContract.TopListView mView, RxErrorHandler errorHandler) {
        super(mModel, mView);
        this.mErrorHandler = errorHandler;

    }

    public void getTopListApps(int page){
        Subscriber subscriber =null;
        if(page==0){
            subscriber =  new ProgressDialogSubscriber<PageBean<AppInfo>>(mContext,mErrorHandler) {
                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {
                    mView.showResult(appInfoPageBean);
                }
            };

        }
        else {
            subscriber = new ErrorHandlerSubscriber<PageBean<AppInfo>>(mErrorHandler) {
                @Override
                public void onCompleted() {
                    mView.onLoadMoreComplete();

                }

                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {
                    mView.showResult(appInfoPageBean);

                }


            };
        }



        mModel.topList(page)
                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(subscriber);
    }
}
