package com.example.savanaapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> products;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    public void updateProducts(List<Product> newProducts) {
        this.products = newProducts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);

        holder.productImage.setImageResource(product.getImageResId());
        holder.productName.setText(product.getName());
        holder.productPrice.setText(String.format("$%.2f", product.getPrice()));

        if (product.getOriginalPrice() > product.getPrice()) {
            holder.originalPrice.setVisibility(View.VISIBLE);
            holder.originalPrice.setText(String.format("$%.2f", product.getOriginalPrice()));
            holder.discountTag.setVisibility(View.VISIBLE);
            holder.discountTag.setText("-" + product.getDiscountPercent() + "%");
        } else {
            holder.originalPrice.setVisibility(View.GONE);
            holder.discountTag.setVisibility(View.GONE);
        }

        holder.ratingText.setText(String.valueOf(product.getRating()));

        holder.btnAddToCart.setOnClickListener(v -> {
            CartManager.getInstance().addToCart(product);
            Toast.makeText(context, product.getName() + " added to bag!", Toast.LENGTH_SHORT).show();
            holder.btnAddToCart.setText("✓ Added");
            v.postDelayed(() -> holder.btnAddToCart.setText("Add to Bag"), 1500);
        });

        holder.heartBtn.setOnClickListener(v -> {
            Toast.makeText(context, "Added to Wishlist ♡", Toast.LENGTH_SHORT).show();
        });

        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("product", product);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView productImage;
        TextView productName, productPrice, originalPrice, discountTag, ratingText;
        MaterialButton btnAddToCart;
        ImageView heartBtn;

        ProductViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_product);
            productImage = itemView.findViewById(R.id.img_product);
            productName = itemView.findViewById(R.id.tv_product_name);
            productPrice = itemView.findViewById(R.id.tv_price);
            originalPrice = itemView.findViewById(R.id.tv_original_price);
            discountTag = itemView.findViewById(R.id.tv_discount_tag);
            ratingText = itemView.findViewById(R.id.tv_rating);
            btnAddToCart = itemView.findViewById(R.id.btn_add_to_cart);
            heartBtn = itemView.findViewById(R.id.btn_heart);
        }
    }
}