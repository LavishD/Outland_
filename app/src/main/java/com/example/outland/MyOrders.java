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
public class MyOrders extends Fragment {


    public MyOrders() {
        // Required empty public constructor
    }

    private RecyclerView myOrdersRV;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_orders, container, false);

        myOrdersRV = view.findViewById(R.id.my_orders_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myOrdersRV.setLayoutManager(layoutManager);


        List<MyOrderItemModel> myOrderItemModelList = new ArrayList<>();

        myOrderItemModelList.add(new MyOrderItemModel("#6448681-5184-654984", "2 August 2019, 10 AM","Delivered"));
        myOrderItemModelList.add(new MyOrderItemModel("#6441592-5184-654984", "5 August 2019, 6 PM","Delivered"));
        myOrderItemModelList.add(new MyOrderItemModel("#1154164-5184-654984", "6 August 2019, 10 AM","Delivered"));
        myOrderItemModelList.add(new MyOrderItemModel("#4984688-5184-654984", "8 August 2019, 11 AM","Delivered"));
        myOrderItemModelList.add(new MyOrderItemModel("#4948681-5184-654984", "8 August 2019, 6 PM", "Cancelled"));
        myOrderItemModelList.add(new MyOrderItemModel("#6448681-5184-654984", "10 August 2019, 10 AM","Delivered"));


        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(myOrderItemModelList);
        myOrdersRV.setAdapter(myOrderAdapter);
        myOrderAdapter.notifyDataSetChanged();


        return view;
    }

}
