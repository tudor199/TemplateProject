package com.company.templateapplication.roomService;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.company.templateapplication.entity.Dummy;

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
