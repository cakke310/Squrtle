package com.squrtle.data.http;

import com.squrtle.bean.AppInfo;
import com.squrtle.bean.BaseBean;
import com.squrtle.bean.IndexBean;
import com.squrtle.bean.PageBean;
import com.squrtle.bean.requestbean.LoginRequestBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by c_xuwei-010 on 2017/4/6.
 */
public interface ApiService {
    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";

    @GET("featured2")
    public Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParams);


    @GET("index")
    public Observable<BaseBean<IndexBean>> index();

    @GET("")
    public Observable<BaseBean<AppInfo>> topList(@Query("page") int page);

    @POST("login")
    public Observable<BaseBean> login(@Body LoginRequestBean bean);

}
