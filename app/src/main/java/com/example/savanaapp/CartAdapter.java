package com.example.savanaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<Product> cartItems;
    private OnCartUpdateListener listener;

    public interface OnCartUpdateListener {
        void onCartUpdated();
    }

    public CartAdapter(Context context, List<Product> cartItems, OnCartUpdateListener listener) {
        this.context = context;
        this.cartItems = cartItems;
        this.listener = listener;
    }

    public void updateItems(List<Product> items) {
        this.cartItems = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product item = cartItems.get(position);

        holder.productImage.setImageResource(item.getImageResId());
        holder.productName.setText(item.getName());
        holder.productPrice.setText(String.format("$%.2f", item.getPrice()));
        holder.quantity.setText(String.valueOf(item.getQuantity()));
        holder.itemTotal.setText(String.format("$%.2f", item.getPrice() * item.getQuantity()));

        holder.btnPlus.setOnClickListener(v -> {
            CartManager.getInstance().updateQuantity(item.getId(), item.getQuantity() + 1);
            listener.onCartUpdated();
        });

        holder.btnMinus.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                CartManager.getInstance().updateQuantity(item.getId(), item.getQuantity() - 1);
            } else {
                CartManager.getInstance().removeFromCart(item.getId());
            }
            listener.onCartUpdated();
        });

        holder.btnDelete.setOnClickListener(v -> {
            CartManager.getInstance().removeFromCart(item.getId());
            listener.onCartUpdated();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage, btnPlus, btnMinus, btnDelete;
        TextView productName, productPrice, quantity, itemTotal;

        CartViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.img_cart_product);
            productName = itemView.findViewById(R.id.tv_cart_product_name);
            productPrice = itemView.findViewById(R.id.tv_cart_price);
            quantity = itemView.findViewById(R.id.tv_quantity);
            itemTotal = itemView.findViewById(R.id.tv_item_total);
            btnPlus = itemView.findViewById(R.id.btn_plus);
            btnMinus = itemView.findViewById(R.id.btn_minus);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}