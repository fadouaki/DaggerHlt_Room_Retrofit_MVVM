package com.homework.firstmvvm.di;

import android.app.Application;

import androidx.room.Room;

import com.homework.firstmvvm.db.productDAO;
import com.homework.firstmvvm.db.productDB;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
@Module
@InstallIn(SingletonComponent.class)
public class DataBaseModule {

    @Singleton
    @Provides
    public productDB provideDB(Application application) {
        return Room.databaseBuilder(application, productDB.class, "shop_DB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Singleton
    @Provides
    public productDAO provideDAO(productDB productDB) {
        return productDB.productDAO();
    }
}
