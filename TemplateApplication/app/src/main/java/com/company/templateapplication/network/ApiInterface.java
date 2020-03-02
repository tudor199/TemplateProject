package com.company.templateapplication.network;

import com.company.templateapplication.entity.Dummy;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("dummy/page")
    Call<List<Dummy>> getDummyPage(@Query("page") int page, @Query("size") int size);
}
