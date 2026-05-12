package com.example.savanaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import java.util.List;

public class CartActivity extends AppCompatActivity
        implements CartManager.CartChangeListener, CartAdapter.OnCartUpdateListener {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private TextView tvTotal, tvItemCount, tvEmptyCart, tvSubtotal, tvShipping, tvFinalTotal;
    private LinearLayout layoutCartContent, layoutEmptyCart, layoutOrderSummary;
    private MaterialButton btnCheckout;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initViews();
        setupToolbar();
        setupRecyclerView();
        setupBottomNav();
        setupCheckout();
        CartManager.getInstance().addListener(this);
        refreshCart();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.rv_cart_items);
        tvTotal = findViewById(R.id.tv_total_price);
        tvItemCount = findViewById(R.id.tv_item_count);
        tvEmptyCart = findViewById(R.id.tv_empty_cart);
        tvSubtotal = findViewById(R.id.tv_subtotal);
        tvShipping = findViewById(R.id.tv_shipping);
        tvFinalTotal = findViewById(R.id.tv_final_total);
        layoutCartContent = findViewById(R.id.layout_cart_content);
        layoutEmptyCart = findViewById(R.id.layout_empty_cart);
        layoutOrderSummary = findViewById(R.id.layout_order_summary);
        btnCheckout = findViewById(R.id.btn_checkout);
        bottomNav = findViewById(R.id.bottom_nav);
    }

    private void setupToolbar() {
        ImageView backBtn = findViewById(R.id.btn_back);
        TextView title = findViewById(R.id.tv_toolbar_title);
        if (title != null) title.setText("My Bag");
        if (backBtn != null) backBtn.setOnClickListener(v -> finish());
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new CartAdapter(this, CartManager.getInstance().getCartItems(), this);
        recyclerView.setAdapter(cartAdapter);
    }

    private void setupBottomNav() {
        bottomNav.setSelectedItemId(R.id.nav_bag);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(this, MainActivity.class));
                overridePendingTransition(0, 0);
                finish();
            } else if (id == R.id.nav_menu) {
                startActivity(new Intent(this, SearchActivity.class));
                overridePendingTransition(0, 0);
            } else if (id == R.id.nav_bag) {
                return true;
            } else if (id == R.id.nav_me) {
                startActivity(new Intent(this, ProfileActivity.class));
                overridePendingTransition(0, 0);
            }
            return true;
        });
    }

    private void setupCheckout() {
        if (btnCheckout != null) {
            btnCheckout.setOnClickListener(v ->
                    startActivity(new Intent(this, CheckoutActivity.class)));
        }

        View btnShop = findViewById(R.id.btn_shop_now);
        if (btnShop != null) {
            btnShop.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
        }
    }

    public void refreshCart() {
        List<Product> items = CartManager.getInstance().getCartItems();
        cartAdapter.updateItems(items);

        if (items.isEmpty()) {
            layoutCartContent.setVisibility(View.GONE);
            layoutEmptyCart.setVisibility(View.VISIBLE);
            if (layoutOrderSummary != null) layoutOrderSummary.setVisibility(View.GONE);
        } else {
            layoutCartContent.setVisibility(View.VISIBLE);
            layoutEmptyCart.setVisibility(View.GONE);
            if (layoutOrderSummary != null) layoutOrderSummary.setVisibility(View.VISIBLE);

            double subtotal = CartManager.getInstance().getTotal();
            double shipping = subtotal > 50 ? 0 : 5.99;
            double total = subtotal + shipping;

            if (tvItemCount != null)
                tvItemCount.setText(CartManager.getInstance().getCartCount() + " items");
            if (tvSubtotal != null)
                tvSubtotal.setText(String.format("$%.2f", subtotal));
            if (tvShipping != null)
                tvShipping.setText(shipping == 0 ? "FREE" : String.format("$%.2f", shipping));
            if (tvFinalTotal != null)
                tvFinalTotal.setText(String.format("$%.2f", total));
            if (tvTotal != null)
                tvTotal.setText(String.format("$%.2f", total));
        }
    }

    @Override public void onCartChanged() { runOnUiThread(this::refreshCart); }
    @Override public void onCartUpdated() { refreshCart(); }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CartManager.getInstance().removeListener(this);
    }
}