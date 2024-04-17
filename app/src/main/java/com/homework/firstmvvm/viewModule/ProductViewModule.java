package com.homework.firstmvvm.viewModule;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.homework.firstmvvm.model.Product;
import com.homework.firstmvvm.repository.Repository;

import java.util.List;


import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class ProductViewModule extends ViewModel {
    private Repository repository;

    @Inject
    public ProductViewModule(Repository repository) {
        this.repository = repository;
    }

    MutableLiveData<List<Product>> productsList = new MutableLiveData<>();

    LiveData<List<Product>> favoriteList = null;

    public LiveData<List<Product>> getfavoriteList() {
        return favoriteList;
    }


    public MutableLiveData<List<Product>> getProductsList() {
        return productsList;
    }

    @SuppressLint("CheckResult")
    public void getProducts() {
        repository.getProductResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> productsList.setValue(result),
                        error -> Log.d("TakiDev", "Error in ViewModel " + error.toString()));

    }

    public void InsertProduct(Product product) {
        repository.insertProduct(product);
    }

    public void deleteProduct(String productName) {
        repository.deletProduct(productName);
    }

    public void getFavoriteProduct() {
        favoriteList = repository.getProduct();
        Log.d("TakiDev", "add favoriteList "+repository.getRowCount());

    }
    public int isProductExist(String productName) {
        Log.d("TakiDev", "product exist status : "+repository.IsProductExist(productName));
        return repository.IsProductExist(productName);
    }


}
