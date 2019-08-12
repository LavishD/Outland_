package com.example.outland;

public class ViewMoreModel {

    private int productImage;
    private String productPrice;
    private String productMrp;
    private String productName;
    private String productWeight;

    public ViewMoreModel(int productImage, String productPrice, String productMrp, String productName, String productWeight, String productQty, String quantity) {
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.productMrp = productMrp;
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

    public String getProductMrp() {
        return productMrp;
    }

    public void setProductMrp(String productMrp) {
        this.productMrp = productMrp;
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


