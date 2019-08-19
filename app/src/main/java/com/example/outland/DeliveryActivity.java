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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DeliveryActivity extends AppCompatActivity {

    private RecyclerView deliveryRv;
    private Button changeOrAddBtn;
    public static final int SELECT_ADDRESS = 0;
    private TextView totalAmount;

    private TextView fullName;
    private TextView address;
    private TextView locality;


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
         totalAmount = findViewById(R.id.total_cart_price);

         fullName = findViewById(R.id.name_tv);
         address = findViewById(R.id.address_tv);
         locality = findViewById(R.id.pinCode);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        deliveryRv.setLayoutManager(layoutManager);

        CartAdapter cartAdapter = new CartAdapter(DBQueries.cartItemModelList);
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
    protected void onStart() {
        super.onStart();

        fullName.setText(DBQueries.addressesModelList.get(DBQueries.selectedAddress).getFullName());
        address.setText(DBQueries.addressesModelList.get(DBQueries.selectedAddress).getAddress());
        locality.setText(DBQueries.addressesModelList.get(DBQueries.selectedAddress).getLocality());

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
