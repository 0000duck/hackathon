package com.keba.keba.backend;

import android.util.Log;

import com.keba.keba.RetrofitInterface;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by spp on 07.11.2017.
 */

public class Backend {
    public static final String IP = "10.0.1.216";
    private static RetrofitInterface retrofitInterface;

    private Backend() {
    }

    public static RetrofitInterface getInstance() {
        if (retrofitInterface == null) {
            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(2, TimeUnit.SECONDS)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://" + IP + "/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            retrofitInterface = retrofit.create(RetrofitInterface.class);
        }
        return retrofitInterface;
    }
}
