package com.company.templateapplication.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.company.templateapplication.dao.DummyDao;
import com.company.templateapplication.entity.Dummy;

import java.util.Random;

@Database(entities = {Dummy.class}, version = 1)
public abstract class DummyDatabase extends RoomDatabase {
    private static DummyDatabase instance;
    public abstract DummyDao dummyDao();

    public static synchronized DummyDatabase getInstance(Context context) {
        if (instance ==  null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DummyDatabase.class, "dummies")
                    .fallbackToDestructiveMigration()
//                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }


    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(instance).execute();
        }
    };

    private static class PopulateAsyncTask extends AsyncTask<Void, Void, Void> {
        private DummyDao dummyDao;

        PopulateAsyncTask(DummyDatabase dummyDatabase) {
            dummyDao = dummyDatabase.dummyDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < 30; i++) {
                int x = new Random().nextInt(1000);
                dummyDao.insert(new Dummy("Name " + x, x));
            }
            return null;
        }
    }
}
