package com.example.outland;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AddAddressActivity extends AppCompatActivity {

    private Button saveBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Add Address");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        saveBtn = findViewById(R.id.add_address_continue);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent deliveryIntent = new Intent(AddAddressActivity.this, DeliveryActivity.class);
                startActivity(deliveryIntent);
                finish();

            }
        });


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
