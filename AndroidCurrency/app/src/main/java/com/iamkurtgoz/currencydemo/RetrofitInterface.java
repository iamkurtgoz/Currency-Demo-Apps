package com.iamkurtgoz.currencydemo;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("/api/latest")
    Call<ResponseBody> api(
            @Query("access_key") String access_key,
            @Query("format") int format
    );

}
