package com.example.outland;

public class HorizontalProductScrollModel {

    private int productImage;
    private String productPrice;
    private String productMRP;
    private String productName;
    private String productWeight;

    public HorizontalProductScrollModel(int productImage, String productPrice, String productMRP, String productName, String productWeight) {
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.productMRP = productMRP;
        this.productName = productName;
        this.productWeight = productWeight;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductMRP() {
        return productMRP;
    }

    public void setProductMRP(String productMRP) {
        this.productMRP = productMRP;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }
}
