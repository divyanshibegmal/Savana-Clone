package com.example.savanaapp;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Product product = (Product) getIntent().getSerializableExtra("product");
        if (product == null) { finish(); return; }

        ImageView backBtn = findViewById(R.id.btn_back);
        ImageView productImg = findViewById(R.id.img_product_large);
        TextView tvName = findViewById(R.id.tv_detail_name);
        TextView tvPrice = findViewById(R.id.tv_detail_price);
        TextView tvOriginalPrice = findViewById(R.id.tv_detail_original_price);
        TextView tvDescription = findViewById(R.id.tv_detail_description);
        TextView tvRating = findViewById(R.id.tv_detail_rating);
        TextView tvReviews = findViewById(R.id.tv_detail_reviews);
        TextView tvCategory = findViewById(R.id.tv_detail_category);
        TextView tvDiscount = findViewById(R.id.tv_detail_discount);
        MaterialButton btnAddToCart = findViewById(R.id.btn_detail_add_to_cart);
        ImageView btnHeart = findViewById(R.id.btn_detail_heart);

        if (backBtn != null) backBtn.setOnClickListener(v -> finish());
        if (productImg != null) productImg.setImageResource(product.getImageResId());
        if (tvName != null) tvName.setText(product.getName());
        if (tvPrice != null) tvPrice.setText(String.format("$%.2f", product.getPrice()));
        if (tvCategory != null) tvCategory.setText(product.getCategory().toUpperCase());
        if (tvDescription != null) tvDescription.setText(product.getDescription());
        if (tvRating != null) tvRating.setText(String.valueOf(product.getRating()));
        if (tvReviews != null) tvReviews.setText("(" + product.getReviewCount() + " reviews)");

        if (product.getOriginalPrice() > product.getPrice()) {
            if (tvOriginalPrice != null) {
                tvOriginalPrice.setVisibility(android.view.View.VISIBLE);
                tvOriginalPrice.setText(String.format("$%.2f", product.getOriginalPrice()));
                tvOriginalPrice.setPaintFlags(tvOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
            if (tvDiscount != null) {
                tvDiscount.setVisibility(android.view.View.VISIBLE);
                tvDiscount.setText("-" + product.getDiscountPercent() + "%");
            }
        }

        if (btnAddToCart != null) {
            btnAddToCart.setOnClickListener(v -> {
                CartManager.getInstance().addToCart(product);
                Toast.makeText(this, "Added to bag! 🛍️", Toast.LENGTH_SHORT).show();
                btnAddToCart.setText("✓ Added to Bag");
                v.postDelayed(() -> btnAddToCart.setText("Add to Bag"), 2000);
            });
        }

        if (btnHeart != null) {
            btnHeart.setOnClickListener(v ->
                    Toast.makeText(this, "Added to Wishlist ♡", Toast.LENGTH_SHORT).show());
        }
    }
}