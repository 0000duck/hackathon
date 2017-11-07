package com.keba.keba.backend;

import com.keba.keba.RetrofitInterface;

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
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://" + IP + "/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            retrofitInterface = retrofit.create(RetrofitInterface.class);
        }
        return retrofitInterface;
    }
}
