package com.example.savanaapp;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<Product> cartItems;
    private List<CartChangeListener> listeners;

    public interface CartChangeListener {
        void onCartChanged();
    }

    private CartManager() {
        cartItems = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addToCart(Product product) {
        for (Product item : cartItems) {
            if (item.getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                notifyListeners();
                return;
            }
        }
        Product newItem = new Product(product.getId(), product.getName(),
                product.getCategory(), product.getPrice(), product.getOriginalPrice(),
                product.getImageResId(), product.getDescription(),
                product.getRating(), product.getReviewCount());
        newItem.setQuantity(1);
        cartItems.add(newItem);
        notifyListeners();
    }

    public void removeFromCart(int productId) {
        cartItems.removeIf(item -> item.getId() == productId);
        notifyListeners();
    }

    public void updateQuantity(int productId, int quantity) {
        for (Product item : cartItems) {
            if (item.getId() == productId) {
                if (quantity <= 0) {
                    removeFromCart(productId);
                } else {
                    item.setQuantity(quantity);
                    notifyListeners();
                }
                return;
            }
        }
    }

    public List<Product> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public int getCartCount() {
        int count = 0;
        for (Product item : cartItems) {
            count += item.getQuantity();
        }
        return count;
    }

    public double getTotal() {
        double total = 0;
        for (Product item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    public void clearCart() {
        cartItems.clear();
        notifyListeners();
    }

    public void addListener(CartChangeListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void removeListener(CartChangeListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        for (CartChangeListener listener : listeners) {
            listener.onCartChanged();
        }
    }
}