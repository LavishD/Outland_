package com.example.outland;

public class AddressesModel {

    private String fullName;
    private String address;
    private String locality;
    private boolean selected;

    public AddressesModel(String fullName, String address, String locality, boolean selected) {
        this.fullName = fullName;
        this.address = address;
        this.locality = locality;
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }
}
