package com.example.outland;

public class CategoryModel {

    private String categoryIconLink;
    private String categoryName;
    private boolean isVisible;

    public CategoryModel(String categoryIconLink, String categoryName, boolean isVisible) {
        this.categoryIconLink = categoryIconLink;
        this.categoryName = categoryName;
        this.isVisible = isVisible;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public String getCategoryIconLink() {
        return categoryIconLink;
    }

    public void setCategoryIconLink(String categoryIconLink) {
        this.categoryIconLink = categoryIconLink;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
