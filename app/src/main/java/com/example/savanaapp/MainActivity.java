package com.example.savanaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}