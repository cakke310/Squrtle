package com.squrtle.http;

import com.squrtle.ui.bean.AppInfo;
import com.squrtle.ui.bean.PageBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by c_xuwei-010 on 2017/4/6.
 */
public interface ApiService {
    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";

    @GET("featured")
    public Call<PageBean<AppInfo>> getApps(@Query("p") String jsonParams);
}