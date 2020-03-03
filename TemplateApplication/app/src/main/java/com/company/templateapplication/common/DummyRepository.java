package com.company.templateapplication.common;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.company.templateapplication.entity.Dummy;

public interface DummyRepository {
    LiveData<PagedList<Dummy>> getAllDummies();
    void insert(Dummy dummy);
    void update(Dummy dummy);
    void delete(Dummy dummy);
}
