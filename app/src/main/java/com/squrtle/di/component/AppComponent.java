package com.squrtle.di.component;

import android.app.Application;

import com.squrtle.common.rx.RxErrorHandler;
import com.squrtle.data.http.ApiService;
import com.squrtle.di.module.AppModule;
import com.squrtle.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by c_xuwei-010 on 2017/4/10.
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    public ApiService getApiService();

    public Application getApplication();

    public RxErrorHandler getRxErrorHandler();
}
