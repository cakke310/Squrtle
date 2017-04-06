package com.squrtle.data;

import com.squrtle.http.ApiService;
import com.squrtle.http.HttpManager;
import com.squrtle.ui.bean.AppInfo;
import com.squrtle.ui.bean.PageBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by c_xuwei-010 on 2017/4/6.
 */
public class RecommendModel {

    public void getApps(Callback<PageBean<AppInfo>> callback){
        HttpManager manager = new HttpManager();
        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);
        apiService.getApps("{'page': 0}").enqueue(callback);
    }

}
