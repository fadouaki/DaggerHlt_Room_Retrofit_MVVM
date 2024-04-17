package com.homework.firstmvvm.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.homework.firstmvvm.model.Product;

import java.util.List;

@Dao
public interface productDAO {

    @Insert
    public void insertProduct(Product product);

    @Query("DELETE FROM fav_table WHERE title LIKE :productName")
    public void deleteProduct(String productName);

    @Query("SELECT * FROM fav_table")
    public LiveData<List<Product>> getProduct();
    @Query("SELECT EXISTS (SELECT 1 FROM sqlite_master WHERE type = 'table' AND name = :tableName)")
    int isTableExists(String tableName);

    @Query("SELECT COUNT(*) FROM fav_table")
    int getRowCount();
    @Query("SELECT COUNT(*) FROM fav_table WHERE title = :name")
    int isProductExist(String name);



}
