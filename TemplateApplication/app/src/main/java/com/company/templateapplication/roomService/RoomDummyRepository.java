package com.company.templateapplication.roomService;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.company.templateapplication.common.DummyRepository;
import com.company.templateapplication.entity.Dummy;

public class RoomDummyRepository implements DummyRepository {
    private DummyDao dummyDao;
    private LiveData<PagedList<Dummy>> dummies;

    public RoomDummyRepository(Application application) {
        DummyDatabase dummyDatabase = DummyDatabase.getInstance(application);
        dummyDao = dummyDatabase.dummyDao();
        DataSource.Factory factory = dummyDao.getDummyFactory();
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(10)
                .setInitialLoadSizeHint(20)
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
        new InsertAsyncTask(dummyDao).execute(dummy);
    }

    @Override
    public void update(Dummy dummy) {
        new UpdateAsyncTask(dummyDao).execute(dummy);
    }

    @Override
    public void delete(Dummy dummy) {
        new DeleteAsyncTask(dummyDao).execute(dummy);
    }

    private static class InsertAsyncTask extends AsyncTask<Dummy, Void, Void> {
        private DummyDao dummyDao;

        InsertAsyncTask(DummyDao dummyDao) {
            this.dummyDao = dummyDao;
        }

        @Override
        protected Void doInBackground(Dummy... dummies) {
            Dummy dummy = dummies[0];
            dummyDao.insert(dummies[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Dummy, Void, Void> {
        private DummyDao dummyDao;

        UpdateAsyncTask(DummyDao dummyDao) {
            this.dummyDao = dummyDao;
        }

        @Override
        protected Void doInBackground(Dummy... dummies) {
            dummyDao.update(dummies[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Dummy, Void, Void> {
        private DummyDao dummyDao;

        DeleteAsyncTask(DummyDao dummyDao) {
            this.dummyDao = dummyDao;
        }

        @Override
        protected Void doInBackground(Dummy... dummies) {
            dummyDao.delete(dummies[0]);
            return null;
        }
    }
}
