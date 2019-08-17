package com.example.outland;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ProductDetailsAdapter extends FragmentPagerAdapter {

    private int totalTabs;
    private String desc;
    private String keyF;

    public ProductDetailsAdapter(FragmentManager fm, int totalTabs, String desc, String keyF) {
        super(fm);
        this.totalTabs = totalTabs;
        this.desc = desc;
        this.keyF = keyF;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){

            case 0:
                ProductDescriptionFragment productDescriptionFragment = new ProductDescriptionFragment();
                productDescriptionFragment.productDescription = desc;
                return productDescriptionFragment;
            case 1:
                ProductKeyFeaturesFragment productKeyFeatureFragment = new ProductKeyFeaturesFragment();
                productKeyFeatureFragment.productKeyFeatures = keyF;
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
