package com.squrtle.common.rx;

import android.util.Log;

import com.squrtle.bean.BaseBean;
import com.squrtle.common.exception.ApiException;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by c_xuwei-010 on 2017/4/11.
 */
public class RxHttpResponseCompat {

    public static <T> ObservableTransformer<BaseBean<T>,T> compatResult(){

        return new ObservableTransformer<BaseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseBean<T>> baseBeanObservable) {
                return baseBeanObservable.flatMap(new Function<BaseBean<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(@NonNull final BaseBean<T> tBaseBean) throws Exception {
                        if(tBaseBean.success()){
                            return Observable.create(new ObservableOnSubscribe<T>() {
                                @Override
                                public void subscribe(ObservableEmitter<T> subscriber) throws Exception {
                                    try {
                                        subscriber.onNext(tBaseBean.getData());
                                        subscriber.onComplete();
                                    } catch (Exception e) {
                                        subscriber.onError(e);
                                    }
                                }
                            });
                        }
                        else {
                            return Observable.error(new ApiException(tBaseBean.getStatus(), tBaseBean.getMessage()));

                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
            }
        };
    }


//    public static <T>Observable.Transformer<BaseBean<T>, T> compatResult(){
//        return new Observable.Transformer<BaseBean<T>, T>() {
//            @Override
//            public Observable<T> call(Observable<BaseBean<T>> baseBeanObservable) {
//
//                return baseBeanObservable.flatMap(new Func1<BaseBean<T>, Observable<T>>() {
//                    @Override
//                    public Observable<T> call(final BaseBean<T> tBaseBean) {
//                        if (tBaseBean.success()) {
//                            return Observable.create(new Observable.OnSubscribe<T>() {
//                                @Override
//                                public void call(Subscriber<? super T> subscriber) {
//                                    try {
//                                        subscriber.onNext(tBaseBean.getData());
//                                        subscriber.onCompleted();
//                                    } catch (Exception e) {
//                                        subscriber.onError(e);
//                                    }
//                                }
//                            });
//
//                        } else {
//                            return Observable.error(new ApiException(tBaseBean.getStatus(), tBaseBean.getMessage()));
//                        }
//
//                    }
//                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
//            }
//        };
//    }
}
