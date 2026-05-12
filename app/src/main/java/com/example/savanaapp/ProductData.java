package com.example.savanaapp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductData {

    // ================= TOPS =================
    public static List<Product> getTopsProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(101, "Polka Dot Puff Sleeve Top", "Tops", 15.99, 22.00, R.drawable.top1, "A stylish polka dot top with puff sleeves.", 4.6f, 128));
        products.add(new Product(102, "Floral Crop Top", "Tops", 12.99, 20.00, R.drawable.top2, "Beautiful floral print crop top for summer.", 4.0f, 95));
        products.add(new Product(103, "Oversized Graphic Tee", "Tops", 10.99, 18.00, R.drawable.top3, "Comfortable oversized tee with trendy graphics.", 3.8f, 210));
        products.add(new Product(104, "Satin Cami Top", "Tops", 17.99, 32.00, R.drawable.top4, "Elegant satin cami top for evening wear.", 4.3f, 84));
        products.add(new Product(105, "Ribbed Casual Top", "Tops", 13.99, 24.00, R.drawable.top5, "Versatile ribbed top for everyday comfort.", 4.1f, 156));
        products.add(new Product(106, "Tie Knot Crop Top", "Tops", 11.99, 21.00, R.drawable.top6, "Chic tie-knot crop top in vibrant colors.", 4.4f, 72));
        return products;
    }

    // ================= BEAUTY =================
    public static List<Product> getBeautyProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(201, "Makeup Brush Set", "Beauty", 12.99, 24.99, R.drawable.beauty1, "Professional 12-piece makeup brush set.", 4.8f, 320));
        products.add(new Product(202, "Gel Nail Kit", "Beauty", 18.99, 34.00, R.drawable.beauty2, "Complete gel nail polish kit with UV lamp.", 4.7f, 185));
        products.add(new Product(203, "Oval Hair Brush", "Beauty", 8.49, 14.99, R.drawable.beauty3, "Detangling oval hair brush for smooth hair.", 4.3f, 450));
        products.add(new Product(204, "Pink Makeup Bag", "Beauty", 9.99, 19.99, R.drawable.beauty4, "Spacious and stylish pink makeup organizer.", 5.0f, 92));
        products.add(new Product(205, "Luxury Lip Gloss", "Beauty", 7.99, 14.99, R.drawable.beauty5, "High-shine luxury lip gloss in various shades.", 4.5f, 215));
        products.add(new Product(206, "Skincare Essentials Kit", "Beauty", 21.99, 39.99, R.drawable.beauty6, "Set of 5 essential skincare products.", 4.2f, 110));
        products.add(new Product(207, "Compact Mirror Set", "Beauty", 6.99, 12.99, R.drawable.beauty7, "Double-sided compact mirror with LED light.", 4.0f, 65));
        return products;
    }

    // ================= ACCESSORIES =================
    public static List<Product> getAccessoriesProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(301, "Gold Chain Necklace", "Accessories", 14.99, 25.00, R.drawable.beauty4, "Elegant gold-plated chain necklace.", 4.7f, 85));
        products.add(new Product(302, "Classic Aviator Sunglasses", "Accessories", 19.99, 35.00, R.drawable.top3, "Timeless aviator style sunglasses.", 4.5f, 120));
        products.add(new Product(303, "Leather Crossbody Bag", "Accessories", 29.99, 55.00, R.drawable.ic_bag, "Premium faux leather crossbody bag.", 4.8f, 60));
        products.add(new Product(304, "Set of 3 Velvet Scrunchies", "Accessories", 6.99, 12.00, R.drawable.beauty1, "Soft velvet scrunchies in assorted colors.", 4.9f, 210));
        products.add(new Product(305, "Minimalist Watch", "Accessories", 39.99, 75.00, R.drawable.beauty2, "Sleek minimalist watch with leather strap.", 4.6f, 45));
        return products;
    }

    // ================= DRESSES =================
    public static List<Product> getDressesProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(401, "Summer Maxi Dress", "Dresses", 24.99, 45.00, R.drawable.top2, "Flowy summer maxi dress with floral pattern.", 4.7f, 140));
        products.add(new Product(402, "Little Black Dress", "Dresses", 34.99, 60.00, R.drawable.top4, "Classic little black dress for any occasion.", 4.9f, 200));
        products.add(new Product(403, "Boho Midi Dress", "Dresses", 22.99, 38.00, R.drawable.top6, "Comfortable boho style midi dress.", 4.5f, 95));
        return products;
    }

    // ================= BOTTOMS =================
    public static List<Product> getBottomsProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(501, "High Waisted Jeans", "Bottoms", 29.99, 50.00, R.drawable.top1, "Classic high waisted denim jeans.", 4.6f, 310));
        products.add(new Product(502, "Pleated Midi Skirt", "Bottoms", 18.99, 32.00, R.drawable.top5, "Elegant pleated skirt for a chic look.", 4.4f, 125));
        return products;
    }

    // ================= ACTIVEWEAR =================
    public static List<Product> getActivewearProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(601, "Seamless Leggings", "Activewear", 21.99, 40.00, R.drawable.top3, "High-performance seamless gym leggings.", 4.8f, 420));
        products.add(new Product(602, "Sports Bra", "Activewear", 15.99, 28.00, R.drawable.top5, "Supportive sports bra for high-impact workouts.", 4.7f, 180));
        return products;
    }

    // ================= BAGS =================
    public static List<Product> getBagsProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(701, "Canvas Tote Bag", "Bags", 12.99, 22.00, R.drawable.ic_bag, "Durable canvas tote bag for everyday use.", 4.5f, 150));
        products.add(new Product(702, "Mini Backpack", "Bags", 25.99, 48.00, R.drawable.beauty4, "Cute and compact mini backpack.", 4.6f, 88));
        return products;
    }

    // ================= ALL PRODUCTS =================
    public static List<Product> getAllProducts() {
        List<Product> all = new ArrayList<>();
        all.addAll(getTopsProducts());
        all.addAll(getBeautyProducts());
        all.addAll(getAccessoriesProducts());
        all.addAll(getDressesProducts());
        all.addAll(getBottomsProducts());
        all.addAll(getActivewearProducts());
        all.addAll(getBagsProducts());
        return all;
    }

    // ================= SEARCH =================
    public static List<Product> searchProducts(String query) {
        String lowerQuery = query.toLowerCase();
        return getAllProducts().stream()
                .filter(p -> p.getName().toLowerCase().contains(lowerQuery) || 
                            p.getCategory().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }
}
