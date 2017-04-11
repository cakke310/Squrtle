package com.squrtle.data.http;

import com.squrtle.bean.AppInfo;
import com.squrtle.bean.PageBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by c_xuwei-010 on 2017/4/6.
 */
public interface ApiService {
    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";

    @GET("featured")
    public Observable<PageBean<AppInfo>> getApps(@Query("p") String jsonParams);
}
