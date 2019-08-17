package com.example.outland;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductKeyFeaturesFragment extends Fragment {


    public ProductKeyFeaturesFragment() {
        // Required empty public constructor
    }

    private TextView keyFeaturesBody;
    public String productKeyFeatures;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_key_features, container, false);
        keyFeaturesBody = view.findViewById(R.id.tv_product_key_features);
        keyFeaturesBody.setText(productKeyFeatures);
        return view;

    }

}
