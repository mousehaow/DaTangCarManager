package com.datang.datangcarmanager.api;

import com.datang.datangcarmanager.MyApplication;
import com.datang.datangcarmanager.utils.StateUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @name DaTangCarManager
 * @class name：com.datang.datangcarmanager.api
 * @class describe
 * @anthor toby
 * @time 2016/12/2 上午8:58
 * @change
 * @chang time
 * @class describe
 */
public class ApiRetrofit {

    public CarApi mCarApiService;

    public static final String CAR_BASE_URL = "http://iov.cpsdna.com:19080/";

    public CarApi getCarApiService() {
        return mCarApiService;
    }

    public ApiRetrofit() {
        File httpCacheDirectory = new File(MyApplication.mContext.getCacheDir(), "responses");
        int cacheSize = 5 * 1024 * 1024; // 5 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .cache(cache).build();

        Retrofit retrofit_car = new Retrofit.Builder()
                .baseUrl(CAR_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mCarApiService = retrofit_car.create(CarApi.class);

    }

    //cache
    Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = chain -> {

        CacheControl.Builder cacheBuilder = new CacheControl.Builder();
        cacheBuilder.maxAge(0, TimeUnit.SECONDS);
        cacheBuilder.maxStale(365, TimeUnit.DAYS);
        CacheControl cacheControl = cacheBuilder.build();

        Request request = chain.request();
        if (!StateUtils.isNetworkAvailable(MyApplication.mContext)) {
            request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build();

        }
        Response originalResponse = chain.proceed(request);
        if (StateUtils.isNetworkAvailable(MyApplication.mContext)) {
            int maxAge = 0; // read from cache
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public ,max-age=" + maxAge)
                    .build();
        } else {
            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    };
}
