package com.example.outland;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
    private Button checkoutBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        cartItemsRV = view.findViewById(R.id.cart_items_rv);
        checkoutBtn = view.findViewById(R.id.checkoutBtn);


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


        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent deliveryIntent = new Intent(getContext(), AddAddressActivity.class);
                getContext().startActivity(deliveryIntent);
            }
        });



        return view;
    }

}
