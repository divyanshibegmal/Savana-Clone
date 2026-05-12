package com.example.savanaapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText searchBar;
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private TextView tvResultInfo;
    private LinearLayout layoutCategories, layoutResults;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initViews();
        setupSearch();
        setupCategoryShortcuts();
        setupBottomNav();

        // Auto-focus search bar
        searchBar.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(searchBar, InputMethodManager.SHOW_IMPLICIT);
    }

    private void initViews() {
        searchBar = findViewById(R.id.et_search_main);
        recyclerView = findViewById(R.id.rv_search_results);
        tvResultInfo = findViewById(R.id.tv_result_info);
        layoutCategories = findViewById(R.id.layout_categories_browse);
        layoutResults = findViewById(R.id.layout_results);
        bottomNav = findViewById(R.id.bottom_nav);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new ProductAdapter(this, ProductData.getAllProducts());
        recyclerView.setAdapter(adapter);

        ImageView backBtn = findViewById(R.id.btn_back);
        if (backBtn != null) backBtn.setOnClickListener(v -> finish());
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

    private void doSearch(String query) {
        List<Product> results = ProductData.searchProducts(query);
        layoutCategories.setVisibility(View.GONE);
        layoutResults.setVisibility(View.VISIBLE);
        tvResultInfo.setText(results.size() + " results for \"" + query + "\"");
        adapter.updateProducts(results);
    }

    private void showCategories() {
        layoutCategories.setVisibility(View.VISIBLE);
        layoutResults.setVisibility(View.GONE);
    }

    private void setupCategoryShortcuts() {
        int[] btns = {R.id.cat_beauty, R.id.cat_tops, R.id.cat_dresses,
                R.id.cat_bottoms, R.id.cat_bags, R.id.cat_accessories, R.id.cat_activewear};
        Class<?>[] activities = {BeautyActivity.class, TopsActivity.class, DressesActivity.class,
                BottomsActivity.class, BagsActivity.class, AccessoriesActivity.class, ActivewearActivity.class};

        for (int i = 0; i < btns.length; i++) {
            final Class<?> act = activities[i];
            View btn = findViewById(btns[i]);
            if (btn != null) btn.setOnClickListener(v -> startActivity(new Intent(this, act)));
        }
    }

    private void setupBottomNav() {
        bottomNav.setSelectedItemId(R.id.nav_menu);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
                overridePendingTransition(0, 0);
            } else if (id == R.id.nav_menu) { return true;
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