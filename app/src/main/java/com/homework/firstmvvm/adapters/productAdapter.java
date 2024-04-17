package com.homework.firstmvvm.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.homework.firstmvvm.R;
import com.homework.firstmvvm.model.Product;
import com.homework.firstmvvm.viewModule.ProductViewModule;

import java.util.ArrayList;
import java.util.List;

public class productAdapter extends RecyclerView.Adapter<productAdapter.productViewHolder> {

    private List<Product> productList = new ArrayList<>();
    private Context context;
    private ProductViewModule productViewModule;

    public productAdapter(Context context, ProductViewModule productViewModule) {
        this.context = context;
        this.productViewModule = productViewModule;

    }

    @NonNull
    @Override
    public productViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new productViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull productViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name_product.setText(productList.get(position).getTitle());

        holder.category_product.setText(productList.get(position).getCategory());

        holder.price_product.setText("" + productList.get(position).getPrice());

        if (productViewModule.isProductExist(productList.get(position).getTitle()) != 0) { // exist
            holder.addToFav.setImageResource(R.drawable.baseline_remove_shopping_cart_24);
        } else {
            holder.addToFav.setImageResource(R.drawable.baseline_add_shopping_cart_24);
        }

        Glide.with(context).load(productList.get(position).getImage())
                .into(holder.image_product);

        holder.addToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title= productList.get(position).getTitle();
                if (productViewModule.isProductExist(title) != 0) {
                    productViewModule.deleteProduct(title);
                    holder.addToFav.setImageResource(R.drawable.baseline_add_shopping_cart_24);
                } else {
                    productViewModule.InsertProduct(productList.get(position));
                    holder.addToFav.setImageResource(R.drawable.baseline_remove_shopping_cart_24);
                    Toast.makeText(context, ""+title+" added to basket \uD83D\uDED2", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    public int getItemCount() {

        return productList.size();
    }

    public Product getProductAt(int position) {
        return productList.get(position);
    }

    public static class productViewHolder extends RecyclerView.ViewHolder {
        private ImageView image_product, addToFav;
        private TextView name_product, category_product, price_product;

        public productViewHolder(@NonNull View itemView) {
            super(itemView);
            image_product = itemView.findViewById(R.id.imageView);
            addToFav = itemView.findViewById(R.id.favButton);
            name_product = itemView.findViewById(R.id.textView);
            category_product = itemView.findViewById(R.id.textCategory);
            price_product = itemView.findViewById(R.id.textPrice);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<Product> eProductList) {
        productList = eProductList;
        notifyDataSetChanged();
    }
}
