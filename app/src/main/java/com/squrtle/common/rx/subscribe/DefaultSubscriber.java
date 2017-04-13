package com.squrtle.common.rx.subscribe;

import rx.Subscriber;

/**
 * Created by c_xuwei-010 on 2017/4/13.
 */
public abstract class DefaultSubscriber<T> extends Subscriber<T>{
    @Override
    public void onError(Throwable e) {

    }
}
