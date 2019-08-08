package com.example.outland;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCartFragment extends Fragment {


    public MyCartFragment() {
        // Required empty public constructor
    }

    private RecyclerView cartItemsRV;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        cartItemsRV = view.findViewById(R.id.cart_items_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        cartItemsRV.setLayoutManager(layoutManager);

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0, R.drawable.brd, "₹46","₹30", "English Oven Premium Sandwich Bread", "350g", 1));
        cartItemModelList.add(new CartItemModel(0, R.drawable.brd, "₹46","₹30", "English Oven Premium Sandwich Bread", "350g", 2));
        cartItemModelList.add(new CartItemModel(0, R.drawable.brd, "₹46","₹30", "English Oven Premium Sandwich Bread", "350g", 6));
        cartItemModelList.add(new CartItemModel(1, "₹138", "₹48", "FREE", "₹90", "₹90"));

        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        cartItemsRV.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();




        return view;
    }

}
