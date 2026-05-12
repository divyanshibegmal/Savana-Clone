package com.example.savanaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.List;

public abstract class BaseCategoryActivity extends AppCompatActivity {

    protected RecyclerView recyclerView;
    protected ProductAdapter adapter;
    protected BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        setupToolbar();
        setupRecyclerView();
        setupBottomNav();
    }

    protected abstract int getLayoutId();
    protected abstract String getCategoryTitle();
    protected abstract List<Product> getProducts();

    private void setupToolbar() {
        ImageView backBtn = findViewById(R.id.btn_back);
        TextView title = findViewById(R.id.tv_toolbar_title);
        ImageView cartBtn = findViewById(R.id.btn_cart);

        if (title != null) title.setText(getCategoryTitle());
        if (backBtn != null) backBtn.setOnClickListener(v -> finish());
        if (cartBtn != null) cartBtn.setOnClickListener(v ->
                startActivity(new Intent(this, CartActivity.class)));
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.rv_products);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            adapter = new ProductAdapter(this, getProducts());
            recyclerView.setAdapter(adapter);
        }
    }

    private void setupBottomNav() {
        bottomNav = findViewById(R.id.bottom_nav);
        if (bottomNav != null) {
            bottomNav.setOnItemSelectedListener(item -> {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                    overridePendingTransition(0, 0);
                } else if (id == R.id.nav_menu) {
                    startActivity(new Intent(this, SearchActivity.class));
                    overridePendingTransition(0, 0);
                } else if (id == R.id.nav_bag) {
                    startActivity(new Intent(this, CartActivity.class));
                    overridePendingTransition(0, 0);
                } else if (id == R.id.nav_me) {
                    startActivity(new Intent(this, ProfileActivity.class));
                    overridePendingTransition(0, 0);
                }
                return true;
            });
        }
    }
}