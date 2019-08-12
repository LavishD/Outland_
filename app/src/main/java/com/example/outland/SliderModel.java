package com.example.outland;

public class SliderModel {

    private String banner;
    private String bgColor;

    public SliderModel(String banner, String bgColor) {
        this.banner = banner;
        this.bgColor = bgColor;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }
}
