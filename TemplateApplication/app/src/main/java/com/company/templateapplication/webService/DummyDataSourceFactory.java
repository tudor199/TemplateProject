package com.company.templateapplication.webService;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.company.templateapplication.entity.Dummy;

import retrofit2.Retrofit;

public class DummyDataSourceFactory extends DataSource.Factory {
    private DummyService dummyService;
    private MutableLiveData<PageKeyedDataSource<Integer, Dummy>> dummyLiveDataSource;


    public DummyDataSourceFactory(Retrofit retrofit) {
        this.dummyService = retrofit.create(DummyService.class);
    }

    @NonNull
    @Override
    public DataSource create() {
        dummyLiveDataSource = new MutableLiveData<>();
        DummyDataSource dummyDataSource = new DummyDataSource(dummyService);
        dummyLiveDataSource.postValue(dummyDataSource);
        return dummyDataSource;
    }

    public LiveData<PageKeyedDataSource<Integer, Dummy>> getDummyLiveDataSource() {
        return dummyLiveDataSource;
    }
}
