package com.keba.keba;

import com.keba.keba.Data.SearchRequest;
import com.keba.keba.Data.Test;
import com.keba.keba.Data.Test2;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Sparrow on 10/31/2017.
 */

public interface RetrofitInterface {

    @GET("/test/{id}")
    Call<ResponseBody> jsonPlaceHolder(@Path("id") String id);

    @GET("/test/")
    Call<Test> test();

    @POST("/test2/")
    Call<SearchRequest> test2(@Body SearchRequest request);

    @GET("/test3/")
    Call<ResponseBody> test3();
}
