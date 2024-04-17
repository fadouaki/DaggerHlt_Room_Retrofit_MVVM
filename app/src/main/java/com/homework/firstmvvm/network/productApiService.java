package com.homework.firstmvvm.network;

import com.homework.firstmvvm.model.Product;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
public interface productApiService {
    @GET("products")
    Observable<List<Product>> getProducts();
}
