package com.squrtle.data;

import com.squrtle.bean.BaseBean;
import com.squrtle.bean.IndexBean;
import com.squrtle.data.http.ApiService;
import com.squrtle.bean.AppInfo;
import com.squrtle.bean.PageBean;

import retrofit2.Callback;
import rx.Observable;

/**
 * Created by c_xuwei-010 on 2017/4/6.
 */
public class AppInfoModel {

    private ApiService mApiService;

    public AppInfoModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getApps(){
        return mApiService.getApps("{'page': 0}");
    }

    public Observable<BaseBean<IndexBean>> index(){
        return mApiService.index();
    }

    public Observable<BaseBean<PageBean<AppInfo>>> topList(int page){
        return  mApiService.topList(page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> games(int page){
        return  mApiService.games(page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getFeaturedAppsByCategory( int categoryid,  int page){

        return  mApiService.getFeaturedAppsByCategory(categoryid,page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getTopListAppsByCategory( int categoryid, int page){

        return  mApiService.getTopListAppsByCategory(categoryid,page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getNewListAppsByCategory( int categoryid, int page){

        return  mApiService.getNewListAppsByCategory(categoryid,page);
    }

    public Observable<BaseBean<AppInfo>> getAppDetail(int id){
        return mApiService.getAppDetail(id);
    }
}
