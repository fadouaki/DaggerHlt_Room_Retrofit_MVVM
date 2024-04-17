package com.homework.firstmvvm.repository;

import androidx.lifecycle.LiveData;

import com.homework.firstmvvm.db.productDAO;
import com.homework.firstmvvm.model.Product;
import com.homework.firstmvvm.network.productApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class Repository {
    private productApiService productApiService;
    private productDAO productDAO;

    @Inject
    public Repository(productApiService productApiService, productDAO productDAO) {
        this.productApiService = productApiService;
        this.productDAO = productDAO;
    }

    public Observable<List<Product>> getProductResponse() {
        return productApiService.getProducts();
    }

    public void insertProduct(Product product) {
        productDAO.insertProduct(product);
    }

    public void deletProduct(String productName) {
        productDAO.deleteProduct(productName);
    }

    public LiveData<List<Product>> getProduct() {
        return productDAO.getProduct();
    }
    public int IsProductExist(String productName) {
        return productDAO.isProductExist(productName);
    }
    public int getRowCount() {
        return productDAO.getRowCount();
    }
}
