package com.example.outland;

public class CartItemModel {

//////// making variables
    public static final int CART_ITEM = 0;
    public static final int CART_SUMMARY = 1;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    ////// cart item

    private int productImage;
    private String productMrp;
    private String productPrice;
    private String productNam;
    private String productWeight;
    private int productQty;

    public CartItemModel(int type, int productImage, String productMrp, String productPrice, String productNam, String productWeight, int productQty) {
        this.type = type;
        this.productImage = productImage;
        this.productMrp = productMrp;
        this.productPrice = productPrice;
        this.productNam = productNam;
        this.productWeight = productWeight;
        this.productQty = productQty;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductMrp() {
        return productMrp;
    }

    public void setProductMrp(String productMrp) {
        this.productMrp = productMrp;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductNam() {
        return productNam;
    }

    public void setProductNam(String productNam) {
        this.productNam = productNam;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    ////// cart item

    //////// cart summary
    private String totalMrp;
    private String discountReceived;
    private String deliveryCharges;
    private String subTotal;
    private String checkoutTotal;


    public CartItemModel(int type, String totalMrp, String discountReceived, String deliveryCharges, String subTotal, String checkoutTotal) {
        this.type = type;
        this.totalMrp = totalMrp;
        this.discountReceived = discountReceived;
        this.deliveryCharges = deliveryCharges;
        this.subTotal = subTotal;
        this.checkoutTotal = checkoutTotal;
    }

    public String getTotalMrp() {
        return totalMrp;
    }

    public void setTotalMrp(String totalMrp) {
        this.totalMrp = totalMrp;
    }

    public String getDiscountReceived() {
        return discountReceived;
    }

    public void setDiscountReceived(String discountReceived) {
        this.discountReceived = discountReceived;
    }

    public String getDeliverCharges() {
        return deliveryCharges;
    }

    public void setDeliverCharges(String deliverCharges) {
        this.deliveryCharges = deliverCharges;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }


    public String getCheckoutTotal() {
        return checkoutTotal;
    }

    public void setCheckoutTotal(String checkoutTotal) {
        this.checkoutTotal = checkoutTotal;
    }

    /////// cart summary



}
