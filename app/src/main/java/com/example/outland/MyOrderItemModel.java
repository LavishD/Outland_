package com.example.outland;

public class MyOrderItemModel {

    private String orderNumber;
    private String dateTimeAndItems;
    private String  deliveryStatus;

    public MyOrderItemModel(String orderNumber, String deliveryStatus, String dateTimeAndItems) {
        this.orderNumber = orderNumber;
        this.dateTimeAndItems = dateTimeAndItems;
        this.deliveryStatus = deliveryStatus;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDateTimeAndItems() {
        return dateTimeAndItems;
    }

    public void setDateTimeAndItems(String dateTimeAndItems) {
        this.dateTimeAndItems = dateTimeAndItems;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }


}
