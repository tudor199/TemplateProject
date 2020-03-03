package com.company.templateapplication.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.company.templateapplication.common.DummyRepository;
import com.company.templateapplication.entity.Dummy;
import com.company.templateapplication.webService.WebDummyRepository;

public class DummyViewModel extends AndroidViewModel {
    private DummyRepository dummyRepository;
    private LiveData<PagedList<Dummy>> dummies;

    public DummyViewModel(@NonNull Application application) {
        super(application);
        dummyRepository = new WebDummyRepository(application);
        dummies = dummyRepository.getAllDummies();
    }

    public void insert(Dummy dummy) {
        dummyRepository.insert(dummy);
    }

    public void update(Dummy dummy) {
        dummyRepository.update(dummy);
    }

    public void delete(Dummy dummy) {
        dummyRepository.delete(dummy);
    }

    public LiveData<PagedList<Dummy>> getAllDummies() {
        return dummies;
    }

}
