package com.squrtle;

import android.app.Application;
import android.content.Context;
import android.view.View;

import com.squrtle.di.component.AppComponent;
import com.squrtle.di.component.DaggerAppComponent;
import com.squrtle.di.module.AppModule;
import com.squrtle.presenter.contract.AppInfoContract;

/**
 * Created by c_xuwei-010 on 2017/4/10.
 */
public class AppApplication extends Application {

    private AppComponent mAppComponent;
    private View mView;

    public static AppApplication get(Context context){
        return (AppApplication) context.getApplicationContext();
    }



    public AppComponent getAppComponent(){
        return mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
               .appModule(new AppModule(this)).build();
    }

    public View getView() {
        return mView;
    }

    public void setView(View mView) {
        this.mView = mView;
    }
}
