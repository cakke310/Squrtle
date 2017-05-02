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

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by c_xuwei-010 on 2017/4/19.
 */
public class AppInfoPresenter extends BasePresenter<AppInfoModel,AppInfoContract.AppInfoView> {
    private RxErrorHandler mErrorHandler;

    public static final int TOP_LIST=1;
    public static final int GAME=2;
    public static final int CATEGORY=3;

    public static final int FEATURED=0;
    public static final int TOPLIST=1;
    public static final int NEWLIST=2;


    @Inject
    public AppInfoPresenter(AppInfoModel mModel, AppInfoContract.AppInfoView mView, RxErrorHandler errorHandler) {
        super(mModel, mView);
        this.mErrorHandler = errorHandler;

    }

    public void request(int type, int page,int categoryId, int flagType){
        Observer subscriber =null;
        if(page==0){
            subscriber =  new ProgressDialogSubscriber<PageBean<AppInfo>>(mContext) {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {
                    mView.showResult(appInfoPageBean);
                }

                @Override
                public void onComplete() {

                }
            };

        }
        else {
            subscriber = new ErrorHandlerSubscriber<PageBean<AppInfo>>(mContext) {


                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {
                    mView.showResult(appInfoPageBean);

                }

                @Override
                public void onComplete() {

                }


            };
        }

        Observable observable =  getObservable(type,page,categoryId,flagType);



        observable
                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(subscriber);
    }

    public void requestData(int type, int page){
        request(type,page,0,0);
    }

    public void requestCategoryApps(int categoryId, int page,int flagType){
        request(CATEGORY,page,categoryId,flagType);
    }

    private Observable<BaseBean<PageBean<AppInfo>>> getObservable(int type, int page, int categoryId,int flagType){
        switch (type){
            case TOP_LIST:
                return mModel.topList(page);
            case GAME:
                return mModel.games(page);

            case CATEGORY:
                if(flagType==FEATURED){
                    return  mModel.getFeaturedAppsByCategory(categoryId,page);
                }
                else  if(flagType==TOPLIST){
                    return  mModel.getTopListAppsByCategory(categoryId,page);
                }
                else  if(flagType==NEWLIST){
                    return  mModel.getNewListAppsByCategory(categoryId,page);
                }
            default:
                return Observable.empty();
        }
    }


}
