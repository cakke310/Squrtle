package com.squrtle.common.http;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.squrtle.common.Constant;
import com.squrtle.common.util.DensityUtil;
import com.squrtle.common.util.DeviceUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;


public class CommonParamsInterceptor implements Interceptor {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    private Gson gson;
    private Context context;

    public CommonParamsInterceptor(Context context,Gson gson) {
        this.gson = gson;
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        try {
            String method = request.method();

            HashMap<String,Object> commonParamsMap = new HashMap<>();

           // commonParamsMap.put(Constant.IMEI, DeviceUtils.getIMEI(context));
            commonParamsMap.put(Constant.MODEL,DeviceUtils.getModel());
            commonParamsMap.put(Constant.LANGUAGE,DeviceUtils.getLanguage());
            commonParamsMap.put(Constant.os,DeviceUtils.getBuildVersionIncremental());
            commonParamsMap.put(Constant.RESOLUTION, DensityUtil.getScreenW(context)+"*" + DensityUtil.getScreenH(context));
            commonParamsMap.put(Constant.SDK,DeviceUtils.getBuildVersionSDK()+"");
            commonParamsMap.put(Constant.DENSITY_SCALE_FACTOR,context.getResources().getDisplayMetrics().density+"");


            if(method.equals("GET")){
                HttpUrl httpUrl = request.url();
                HashMap<String,Object> rootMap = new HashMap<>();

                Set<String> paramNames = httpUrl.queryParameterNames();

                for (String key: paramNames){
                    if(Constant.PARAM.equals(key)){
                        String oldParamJson = httpUrl.queryParameter(Constant.PARAM);
                        if(oldParamJson!=null){
                            HashMap<String,Object> p =  gson.fromJson(oldParamJson,HashMap.class);
                            if(p!=null){
                                for(Map.Entry<String,Object> entry: p.entrySet()){
                                    rootMap.put(entry.getKey(),entry.getValue());
                                }
                            }
                        }
                    }
                    else {
                        rootMap.put(key,httpUrl.queryParameter(key));
                    }
                }


                rootMap.put("publicParams",commonParamsMap);

                String newJsonParams = gson.toJson(rootMap);

                String url = httpUrl.toString();


                int index = url.indexOf("?");

                if(index>0){
                    url = url.substring(0,index);
                }
                url = url+"?"+Constant.PARAM+"="+newJsonParams;

                request = request.newBuilder().url(url).build();

            }
            else if(method.equals("POST")){

                RequestBody body = request.body();

                HashMap<Object, Object> rootMap = new HashMap<>();
                if(body instanceof  FormBody){
                    for (int i=0; i<((FormBody) body).size(); i++){
                        rootMap.put(((FormBody) body).encodedName(i), ((FormBody) body).encodedValue(i));
                    }
                }
                else {
                    Buffer buffer = new Buffer();
                    body.writeTo(buffer);
                    String oldJsonParams = buffer.readUtf8();

                    rootMap = gson.fromJson(oldJsonParams,HashMap.class);
                    rootMap.put("publicParams",commonParamsMap);
                    String newJsonParams = gson.toJson(rootMap);
                    request = request.newBuilder().post(RequestBody.create(JSON,newJsonParams)).build();


                }

            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }


        return chain.proceed(request);
    }
}
