package com.squrtle.presenter;

import com.squrtle.bean.AppInfo;
import com.squrtle.bean.BaseBean;
import com.squrtle.bean.PageBean;
import com.squrtle.common.rx.RxErrorHandler;
import com.squrtle.common.rx.RxHttpResponseCompat;
import com.squrtle.common.rx.subscribe.ErrorHandlerSubscriber;
import com.squrtle.common.rx.subscribe.ProgressDialogSubscriber;
import com.squrtle.data.AppInfoModel;
import com.squrtle.presenter.contract.AppInfoContract;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by c_xuwei-010 on 2017/4/19.
 */
public class AppInfoPresenter extends BasePresenter<AppInfoModel,AppInfoContract.AppInfoView> {
    private RxErrorHandler mErrorHandler;

    public static final int TOP_LIST=1;
    public static final int GAME=2;

    @Inject
    public AppInfoPresenter(AppInfoModel mModel, AppInfoContract.AppInfoView mView, RxErrorHandler errorHandler) {
        super(mModel, mView);
        this.mErrorHandler = errorHandler;

    }

    public void requestData(int type, int page){
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

        Observable observable = getObservable(type,page);



        observable
                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(subscriber);
    }

    private Observable<BaseBean<PageBean<AppInfo>>> getObservable(int type, int page){
        switch (type){
            case TOP_LIST:
                return mModel.topList(page);
            case GAME:
                return mModel.games(page);
            default:
                return Observable.empty();
        }
    }
}
