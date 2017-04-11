package com.squrtle.data;

import com.squrtle.data.http.ApiService;
import com.squrtle.bean.AppInfo;
import com.squrtle.bean.PageBean;

import retrofit2.Callback;
import rx.Observable;

/**
 * Created by c_xuwei-010 on 2017/4/6.
 */
public class RecommendModel {

    private ApiService mApiService;

    public RecommendModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    public Observable<PageBean<AppInfo>> getApps(){
        return mApiService.getApps("{'page': 0}");
    }

}
