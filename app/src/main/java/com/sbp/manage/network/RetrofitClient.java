package com.sbp.manage.network;

import com.squareup.moshi.Moshi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitClient {

    private final ApiClient mApiClient;

    private static RetrofitClient sInstance;

    public static RetrofitClient getInstance() {
        if (sInstance == null) {
            sInstance = new RetrofitClient();
        }
        return sInstance;
    }

    private RetrofitClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
        mApiClient = retrofit.create(ApiClient.class);
    }
}
