package com.example.outland;

public class ViewMoreModel {

    private String  productImage;
    private String productPrice;
    private String productMrp;
    private String productName;
    private String productWeight;

    public ViewMoreModel(String productImage, String productPrice, String productMrp, String productName, String productWeight) {
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.productMrp = productMrp;
        this.productName = productName;
        this.productWeight = productWeight;
    }



    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
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


