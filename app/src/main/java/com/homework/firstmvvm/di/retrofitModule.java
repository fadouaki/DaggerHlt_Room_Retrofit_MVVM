package com.homework.firstmvvm.di;

import com.homework.firstmvvm.network.productApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module // use it because retrofit is a library we can add insid it @nject
@InstallIn(SingletonComponent.class) // fro use retrofit in all activity of application
public class retrofitModule {
    @Singleton
    @Provides
    public static productApiService getProductApiService() {

        return new Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(productApiService.class);
    }
}

