package com.example.foods.models;

import android.graphics.Bitmap;

public class Products {
    private int productId;
    private Bitmap productImage;
    private String productName;
    private int quantity;
    private int price;
    private int productCategory;

    public Products(int productId, Bitmap productImage, String productName, int quantity, int price, int productCategory) {
        this.productId = productId;
        this.productImage = productImage;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.productCategory = productCategory;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Bitmap getProductImage() {
        return productImage;
    }

    public void setProductImage(Bitmap productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(int productCategory) {
        this.productCategory = productCategory;
    }
}
