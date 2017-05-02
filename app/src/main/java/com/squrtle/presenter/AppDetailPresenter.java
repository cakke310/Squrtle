package com.squrtle.presenter;

import com.squrtle.bean.AppInfo;
import com.squrtle.common.rx.RxHttpResponseCompat;
import com.squrtle.common.rx.subscribe.ErrorHandlerSubscriber;
import com.squrtle.data.AppInfoModel;
import com.squrtle.presenter.contract.AppInfoContract;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by c_xuwei-010 on 2017/4/27.
 */
public class AppDetailPresenter extends BasePresenter<AppInfoModel,AppInfoContract.AppDetailView> {

    @Inject
    public AppDetailPresenter(AppInfoModel mModel, AppInfoContract.AppDetailView mView) {
        super(mModel, mView);
    }

    public void getAppDetail(int id){
        mModel.getAppDetail(id).compose(RxHttpResponseCompat.<AppInfo>compatResult())
                .subscribe(new ErrorHandlerSubscriber<AppInfo>(mContext) {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AppInfo appInfo) {
                        mView.showAppDetail(appInfo);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
