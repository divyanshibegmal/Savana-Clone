package com.example.savanaapp;

import java.util.List;

public class ActivewearActivity extends BaseCategoryActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_category;
    }

    @Override
    protected String getCategoryTitle() {
        return "Activewear";
    }

    @Override
    protected List<Product> getProducts() {
        return ProductData.getActivewearProducts();
    }
}