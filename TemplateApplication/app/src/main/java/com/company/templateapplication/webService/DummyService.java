package com.company.templateapplication.webService;

import com.company.templateapplication.entity.Dummy;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface DummyService {

    @GET("dummy/page")
    Call<List<Dummy>> getDummyPage(@Query("page") int page, @Query("size") int size);

    @GET("dummy/all")
    Call<List<Dummy>> getAllDummy();

    @GET("dummy")
    Call<Dummy> getDummyById(@Query("id") int id);

    @POST("dummy")
    Call<Void> insertDummy(@Body Dummy dummy);

    @PUT("dummy")
    Call<Void> updateDummy(@Query("id")int id, @Body Dummy dummy);

    @DELETE("dummy")
    Call<Void> deleteDummyById(@Query("id") int id);
}
