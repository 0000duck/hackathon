package com.keba.keba;

import com.keba.keba.data.Question;
import com.keba.keba.data.request.AlarmRequest;
import com.keba.keba.data.request.NewAnswerRequest;
import com.keba.keba.data.request.SearchRequest;
import com.keba.keba.data.response.SearchResponse;

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

    /*@GET("/test/")
    Call<Test> test();*/

    @POST("/test2/")
    Call<SearchRequest> test2(@Body SearchRequest request);

    @GET("/test3/")
    Call<ResponseBody> test3();

    @POST("/test7/")
    Call<ResponseBody> queryByQRResponseBody(@Body AlarmRequest request);

    @POST("/test7/")
    Call<SearchResponse> queryByQR(@Body AlarmRequest request);

    @POST("/q2")
    Call<ResponseBody> newQuestionResponseBody(@Body Question question);

    @POST("/q2")
    Call<Question> newQuestion(@Body Question question);

    @POST("/a1")
    Call<ResponseBody> newAnswer(@Body NewAnswerRequest request);
}
