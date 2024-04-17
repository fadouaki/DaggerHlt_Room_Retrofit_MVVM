package com.homework.firstmvvm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.homework.firstmvvm.adapters.productAdapter;
import com.homework.firstmvvm.model.Product;
import com.homework.firstmvvm.viewModule.ProductViewModule;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint // for dagger know that activity start is this
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private productAdapter adapter;
    private ProductViewModule productViewModule;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);

        FloatingActionButton FavButton = findViewById(R.id.floatingActionButton);

        FavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FavoriteActivity.class));
            }
        });
        productViewModule = new ViewModelProvider(this).get(ProductViewModule.class);


        adapter = new productAdapter(MainActivity.this,productViewModule);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);


        productViewModule.getProducts();
        productViewModule.getProductsList().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter.setList(products);
            }

        });
      //  setSetupSwipe();

    }

/*    void setSetupSwipe() {
        ItemTouchHelper.Callback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int positionSwiped = viewHolder.getAdapterPosition();
                Product swipedProduct = adapter.getProductAt(positionSwiped);
                if (productViewModule.isProductExist(swipedProduct.getTitle()) == 0) {
                    productViewModule.InsertProduct(swipedProduct);
                    Toast.makeText(MainActivity.this, "" + swipedProduct.getTitle() + " Add to favorite", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MainActivity.this, "Exist In favorite", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }

        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }*/

    @Override
    protected void onResume() {
        super.onResume();
    }
}