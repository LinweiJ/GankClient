package com.wega.gankclient.remote.di;

import android.text.TextUtils;


import com.wega.gankclient.application.di.AppScope;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by mguy on 2018/1/5.
 */

@Module
public class OkhttpModule {

    private final int TIMEOUT_READ = 20;
    private final int TIMEOUT_CONNECTION = 20;
    private final int TIMEOUT_WRITE = 20;

    public static final  String DEBUG = "debug";
    public static final  String RELEASE = "release";
    public static final  String CACHE = "cache";


    @AppScope
    @Provides
    @Named(CACHE)
    public OkHttpClient providerAutoCacheOkHttpClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);

                String cacheControl = request.cacheControl().toString();
                if (TextUtils.isEmpty(cacheControl)) {
                    cacheControl = "public, max-age=" + 3600 * 6 + " ,max-stale=2419200";
                }
                return response.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            }
        };
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .build();
    }

    @AppScope
    @Provides
    @Named(DEBUG)
    public OkHttpClient providerDebugOkHttpClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .build();
    }



    @AppScope
    @Provides
    @Named(RELEASE)
    public OkHttpClient providerOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                    .build();
        return okHttpClient;
    }
}