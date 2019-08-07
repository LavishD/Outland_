package com.example.outland;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ProductDetailsAdapter extends FragmentPagerAdapter {

    private int totalTabs;


    public ProductDetailsAdapter(FragmentManager fm, int totalTabs) {
        super(fm);
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){

            case 0:
                ProductDescriptionFragment productDescriptionFragment = new ProductDescriptionFragment();
                return productDescriptionFragment;
            case 1:
                ProductKeyFeaturesFragment productKeyFeatureFragment = new ProductKeyFeaturesFragment();
                return productKeyFeatureFragment;
            case 2:
                ProductInfoFragment productOtherInfoFragment = new ProductInfoFragment();
                return productOtherInfoFragment;
                default:
                    return  null;



        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
