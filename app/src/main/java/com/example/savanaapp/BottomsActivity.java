package com.example.savanaapp;

import java.util.List;

public class BottomsActivity extends BaseCategoryActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_category;
    }

    @Override
    protected String getCategoryTitle() {
        return "Bottoms";
    }

    @Override
    protected List<Product> getProducts() {
        return ProductData.getBottomsProducts();
    }
}