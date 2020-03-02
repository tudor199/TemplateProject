package com.company.templateapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.company.templateapplication.entity.Dummy;

import java.util.List;

@Dao
public interface DummyDao {
    @Insert
    public void insert(Dummy dummy);

    @Update
    void update(Dummy dummy);

    @Delete
    public void delete(Dummy dummy);

    @Query("SELECT * FROM dummies ORDER BY id DESC")
    public DataSource.Factory<Integer, Dummy> getDummyFactory();
}
