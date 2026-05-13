package com.example.savanaapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText searchBar;
    private BottomNavigationView bottomNav;
    private RecyclerView recyclerView;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBar = findViewById(R.id.et_search_main);
        bottomNav = findViewById(R.id.bottom_nav_main);
        setupSearch();
        setupRecyclerView();
        setupBottomNav();

        // Auto-focus search bar
//        searchBar.requestFocus();
//        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//        imm.showSoftInput(searchBar, InputMethodManager.SHOW_IMPLICIT);

        // BEAUTY
        View beauty = findViewById(R.id.btn_cat_beauty);
        if (beauty != null) {
            beauty.setOnClickListener(v ->
                    startActivity(new Intent(MainActivity.this, BeautyActivity.class)));
        }

        // TOPS
        View tops = findViewById(R.id.btn_cat_tops);
        if (tops != null) {
            tops.setOnClickListener(v ->
                    startActivity(new Intent(MainActivity.this, TopsActivity.class)));
        }

        // DRESSES
        View dresses = findViewById(R.id.btn_cat_dresses);
        if (dresses != null) {
            dresses.setOnClickListener(v ->
                    startActivity(new Intent(MainActivity.this, DressesActivity.class)));
        }

        // BOTTOMS
        View bottoms = findViewById(R.id.btn_cat_bottoms);
        if (bottoms != null) {
            bottoms.setOnClickListener(v ->
                    startActivity(new Intent(MainActivity.this, BottomsActivity.class)));
        }

        // BAGS
        View bags = findViewById(R.id.btn_cat_bags);
        if (bags != null) {
            bags.setOnClickListener(v ->
                    startActivity(new Intent(MainActivity.this, BagsActivity.class)));
        }

        // ACCESSORIES
        View accessories = findViewById(R.id.btn_cat_accessories);
        if (accessories != null) {
            accessories.setOnClickListener(v ->
                    startActivity(new Intent(MainActivity.this, AccessoriesActivity.class)));
        }

        // ACTIVEWEAR
        View activewear = findViewById(R.id.btn_cat_activewear);
        if (activewear != null) {
            activewear.setOnClickListener(v ->
                    startActivity(new Intent(MainActivity.this, ActivewearActivity.class)));
        }
    }

    private void setupSearch() {
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                String q = s.toString().trim();
                if (q.isEmpty()) {
                    showCategories();
                } else {
                    doSearch(q);
                }
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        searchBar.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                String q = searchBar.getText().toString().trim();
                if (!q.isEmpty()) doSearch(q);
                return true;
            }
            return false;
        });

        // Popular search tags
        setupSearchTag(R.id.tag_tops, "tops");
        setupSearchTag(R.id.tag_dresses, "dresses");
        setupSearchTag(R.id.tag_makeup, "makeup");
        setupSearchTag(R.id.tag_glasses, "glasses");
        setupSearchTag(R.id.tag_bags, "bags");
        setupSearchTag(R.id.tag_sale, "kit");
        setupSearchTag(R.id.tag_beauty, "beauty");
        setupSearchTag(R.id.tag_activewear, "activewear");
    }

    private void doSearch(String query) {
        List<Product> results = ProductData.searchProducts(query);
//        layoutCategories.setVisibility(View.GONE);
//        layoutResults.setVisibility(View.VISIBLE);
//        tvResultInfo.setText(results.size() + " results for \"" + query + "\"");
//        adapter.updateProducts(results);
    }
    private void showCategories() {
//        layoutCategories.setVisibility(View.VISIBLE);
//        layoutResults.setVisibility(View.GONE);
    }

    private void setupSearchTag(int viewId, String query) {
        View tag = findViewById(viewId);
        if (tag != null) {
            tag.setOnClickListener(v -> {
                searchBar.setText(query);
                searchBar.setSelection(query.length());
                doSearch(query);
            });
        }
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.rv_products_main);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            adapter = new ProductAdapter(this, ProductData.getDiscountedProducts());
            recyclerView.setAdapter(adapter);
        }
    }

    private void setupBottomNav() {
        bottomNav.setSelectedItemId(R.id.nav_menu);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                return true;
            }
            else if (id == R.id.nav_menu) {
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