package com.example.outland;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsActivity extends AppCompatActivity {


    private RecyclerView orderDetailsRV;

    private TextView paymentTv;
    private TextView pamentMethod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Order Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        orderDetailsRV =  findViewById(R.id.order_details_rv);
        paymentTv = findViewById(R.id.payment_tv);
        pamentMethod = findViewById(R.id.payment_method);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        orderDetailsRV.setLayoutManager(layoutManager);

        List<OrderDetailsItemLayoutModel> orderDetailsItemLayoutModelList = new ArrayList<>();
        orderDetailsItemLayoutModelList.add(new OrderDetailsItemLayoutModel(R.drawable.brd,"30","35","English Oven Premium Sandwich \nBread","350 g","Qty:","1"));
        orderDetailsItemLayoutModelList.add(new OrderDetailsItemLayoutModel(R.drawable.brd,"30","35","English Oven Premium Sandwich \nBread","350 g","Qty:","2"));
        orderDetailsItemLayoutModelList.add(new OrderDetailsItemLayoutModel(R.drawable.brd,"30","35","English Oven Premium Sandwich \nBread","350 g","Qty:","3"));
        orderDetailsItemLayoutModelList.add(new OrderDetailsItemLayoutModel(R.drawable.brd,"30","35","English Oven Premium Sandwich \nBread","350 g","Qty:","1"));
        orderDetailsItemLayoutModelList.add(new OrderDetailsItemLayoutModel(R.drawable.brd,"30","35","English Oven Premium Sandwich \nBread","350 g","Qty:","2"));

        OrderDetailsItemLayoutAdapter orderDetailsItemLayoutAdapter = new OrderDetailsItemLayoutAdapter(orderDetailsItemLayoutModelList);
        orderDetailsRV.setAdapter(orderDetailsItemLayoutAdapter);
        orderDetailsItemLayoutAdapter.notifyDataSetChanged();

        paymentTv.setVisibility(View.VISIBLE);
        pamentMethod.setVisibility(View.VISIBLE);





    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home){

            finish();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }


}
