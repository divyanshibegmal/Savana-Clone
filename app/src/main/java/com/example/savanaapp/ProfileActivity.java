package com.example.savanaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setupViews();
        setupBottomNav();
    }

    private void setupViews() {
        // Order menu item
        findViewById(R.id.menu_orders).setOnClickListener(v ->
                startActivity(new Intent(this, OrdersActivity.class)));

        // Coupons
        findViewById(R.id.menu_coupons).setOnClickListener(v -> {
            android.widget.Toast.makeText(this, "No coupons available right now.", android.widget.Toast.LENGTH_SHORT).show();
        });

        // Wishlist
        findViewById(R.id.menu_wishlist).setOnClickListener(v ->
                startActivity(new Intent(this, WishlistActivity.class)));

        // Settings
        findViewById(R.id.menu_settings).setOnClickListener(v -> {
            android.widget.Toast.makeText(this, "Settings coming soon!", android.widget.Toast.LENGTH_SHORT).show();
        });

        // Edit profile
        TextView editProfile = findViewById(R.id.tv_edit_profile);
        if (editProfile != null) {
            editProfile.setOnClickListener(v ->
                    android.widget.Toast.makeText(this, "Edit Profile coming soon!", android.widget.Toast.LENGTH_SHORT).show());
        }
    }

    private void setupBottomNav() {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setSelectedItemId(R.id.nav_me);
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
                return true;
            }
            return true;
        });
    }
}