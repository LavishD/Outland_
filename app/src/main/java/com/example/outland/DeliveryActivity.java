package com.example.outland;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class DeliveryActivity extends AppCompatActivity {

    private RecyclerView deliveryRv;
    private Button changeOrAddBtn;
    public static final int SELECT_ADDRESS = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);


         Toolbar toolbar = findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);
         getSupportActionBar().setDisplayShowTitleEnabled(true);
         getSupportActionBar().setTitle("Delivery");
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);



         changeOrAddBtn = findViewById(R.id.change_add_address);
         deliveryRv = findViewById(R.id.deliveryRV);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        deliveryRv.setLayoutManager(layoutManager);

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0, R.drawable.brd, "₹46","₹30", "English Oven Premium Sandwich Bread", "350g", 1));
        cartItemModelList.add(new CartItemModel(0, R.drawable.brd, "₹46","₹30", "English Oven Premium Sandwich Bread", "350g", 2));
        cartItemModelList.add(new CartItemModel(0, R.drawable.brd, "₹46","₹30", "English Oven Premium Sandwich Bread", "350g", 6));
        cartItemModelList.add(new CartItemModel(1, "₹138", "₹48", "FREE", "₹90", "₹90"));

        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        deliveryRv.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        changeOrAddBtn.setVisibility(View.VISIBLE);

        changeOrAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myAddressesIntent = new Intent(DeliveryActivity.this, MyAddressesActivity.class);
                myAddressesIntent.putExtra("MODE", SELECT_ADDRESS);
                startActivity(myAddressesIntent);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home){

            finish();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
