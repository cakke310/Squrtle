package com.squrtle.data.http;

import com.squrtle.bean.AppInfo;
import com.squrtle.bean.BaseBean;
import com.squrtle.bean.Category;
import com.squrtle.bean.IndexBean;
import com.squrtle.bean.LoginBean;
import com.squrtle.bean.PageBean;
import com.squrtle.bean.requestbean.LoginRequestBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
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

    @GET("toplist")
    public Observable<BaseBean<PageBean<AppInfo>>> topList(@Query("page") int page);

    @GET("game")
    public Observable<BaseBean<PageBean<AppInfo>>> games(@Query("page") int page);

    @POST("login")
    public Observable<BaseBean<LoginBean>> login(@Body LoginRequestBean param);

    @GET("category")
    Observable<BaseBean<List<Category>>> getCategories();

    @GET("category/featured/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getFeaturedAppsByCategory(@Path("categoryid") int categoryid, @Query("page") int page);

    @GET("category/toplist/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getTopListAppsByCategory(@Path("categoryid") int categoryid,@Query("page") int page);

    @GET("category/newlist/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getNewListAppsByCategory(@Path("categoryid") int categoryid,@Query("page") int page);


}
