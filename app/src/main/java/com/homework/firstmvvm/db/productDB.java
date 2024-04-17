package com.homework.firstmvvm.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.homework.firstmvvm.model.Product;

@Database(entities = Product.class, version = 1, exportSchema = false)
public abstract class productDB extends RoomDatabase {

    public abstract productDAO productDAO();


}
