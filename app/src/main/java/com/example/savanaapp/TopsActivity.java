package com.example.savanaapp;

import java.util.List;

public class TopsActivity extends BaseCategoryActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_category;
    }

    @Override
    protected String getCategoryTitle() {
        return "Tops";
    }

    @Override
    protected List<Product> getProducts() {
        return ProductData.getTopsProducts();
    }
}