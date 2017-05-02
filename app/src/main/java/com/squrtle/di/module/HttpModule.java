package com.squrtle.di.module;

import android.app.Application;

import com.google.gson.Gson;
import com.squrtle.AppApplication;
import com.squrtle.BuildConfig;
import com.squrtle.common.http.CommonParamsInterceptor;
import com.squrtle.common.rx.RxErrorHandler;
import com.squrtle.data.http.ApiService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by c_xuwei-010 on 2017/4/10.
 */
@Module
public class HttpModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Application application, Gson gson){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(new CommonParamsInterceptor(application,gson))
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }



    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient);

        return builder.build();
    }

    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    public RxErrorHandler provideErrorHandler(Application appApplication){
        return  new RxErrorHandler(appApplication);
    }
}
