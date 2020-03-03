package com.company.templateapplication.webService;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.company.templateapplication.common.Constant;
import com.company.templateapplication.common.DummyRepository;
import com.company.templateapplication.entity.Dummy;

public class WebDummyRepository implements DummyRepository {
    private DummyService dummyService;
    private LiveData<PagedList<Dummy>> dummies;

    public WebDummyRepository(Application application) {
        dummyService = ApiClient.getRetrofit().create(DummyService.class);
        DummyDataSourceFactory factory = new DummyDataSourceFactory(ApiClient.getRetrofit());
        LiveData<PageKeyedDataSource<Integer, Dummy>> liveDataSource = factory.getDummyLiveDataSource();

        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(Constant.ITEMS_PER_PAGE)
                .setEnablePlaceholders(false)
                .build();

        dummies = new LivePagedListBuilder<>(factory, config).build();
    }

    @Override
    public LiveData<PagedList<Dummy>> getAllDummies() {
        return dummies;
    }

    @Override
    public void insert(Dummy dummy) {

    }

    @Override
    public void update(Dummy dummy) {

    }

    @Override
    public void delete(Dummy dummy) {

    }
}
