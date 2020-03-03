package com.company.templateapplication.webService;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.company.templateapplication.common.Constant;
import com.company.templateapplication.entity.Dummy;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DummyDataSource extends PageKeyedDataSource<Integer, Dummy> {
    private static final String TAG = "DummyDataSource";
    DummyService dummyService;

    public DummyDataSource(DummyService dummyService) {
        this.dummyService = dummyService;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Dummy> callback) {
        dummyService.getDummyPage(Constant.FIRST_PAGE, Constant.ITEMS_PER_PAGE).enqueue(new Callback<List<Dummy>>() {
            @Override
            public void onResponse(Call<List<Dummy>> call, Response<List<Dummy>> response) {
                if (response.isSuccessful()) {
                    callback.onResult(response.body(), null, Constant.FIRST_PAGE + 1);
                } else {
                    Log.e(TAG, "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Dummy>> call, Throwable t) {
                Log.e(TAG, "onResponse: " + t.getMessage());
            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Dummy> callback) {
        dummyService.getDummyPage(params.key, Constant.ITEMS_PER_PAGE).enqueue(new Callback<List<Dummy>>() {
            @Override
            public void onResponse(Call<List<Dummy>> call, Response<List<Dummy>> response) {
                if (response.isSuccessful()) {
                    Integer key = (params.key > 1) ? (params.key - 1) : null;
                    callback.onResult(response.body(), key);
                } else {
                    Log.e(TAG, "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Dummy>> call, Throwable t) {
                Log.e(TAG, "onResponse: " + t.getMessage());
            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Dummy> callback) {
        dummyService.getDummyPage(params.key, Constant.ITEMS_PER_PAGE).enqueue(new Callback<List<Dummy>>() {
            @Override
            public void onResponse(Call<List<Dummy>> call, Response<List<Dummy>> response) {
                if (response.isSuccessful()) {
                    Integer key = null;
                    if (response.body().size() == Constant.ITEMS_PER_PAGE) {
                        key = params.key + 1;
                    }
                    callback.onResult(response.body(), key);
                } else {
                    Log.e(TAG, "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Dummy>> call, Throwable t) {
                Log.e(TAG, "onResponse: " + t.getMessage());
            }
        });

    }
}
