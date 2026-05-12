package com.example.savanaapp;

import java.util.List;

public class BeautyActivity extends BaseCategoryActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_category;
    }

    @Override
    protected String getCategoryTitle() {
        return "Beauty";
    }

    @Override
    protected List<Product> getProducts() {
        return ProductData.getBeautyProducts();
    }
}