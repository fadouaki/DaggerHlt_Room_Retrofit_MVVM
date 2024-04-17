package com.homework.firstmvvm

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.homework.firstmvvm.adapters.productAdapter
import com.homework.firstmvvm.viewModule.ProductViewModule
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // for dagger know that activity start is this

class FavoriteActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: productAdapter
    lateinit var productViewModule: ProductViewModule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        recyclerView = findViewById(R.id.FavRecyclerView)
        productViewModule = ViewModelProvider(this).get(ProductViewModule::class.java)
        adapter = productAdapter(this@FavoriteActivity,productViewModule)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter



        productViewModule.getFavoriteProduct()

        productViewModule.getfavoriteList().observe(this, Observer { products ->
            adapter.setList(products)
        })

    }

}