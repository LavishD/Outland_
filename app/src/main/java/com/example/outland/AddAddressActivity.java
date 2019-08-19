package com.example.outland;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddAddressActivity extends AppCompatActivity {

    private Button saveBtn;

    private EditText name;
    private EditText flat;
    private EditText street;
    private EditText locality;


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
        name = findViewById(R.id.name_et);
        flat = findViewById(R.id.flat_et);
        street = findViewById(R.id.street_et);
        locality = findViewById(R.id.locality_et);


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(name.getText())){
                    if (!TextUtils.isEmpty(flat.getText())){
                        if (!TextUtils.isEmpty(street.getText())){
                            if (!TextUtils.isEmpty(locality.getText())){

                                final String fullAddress = flat.getText().toString() + " " + street.getText().toString();

                                Map<String, Object> addAddress = new HashMap();
                                addAddress.put("list_size", (long)DBQueries.addressesModelList.size() + 1);
                                addAddress.put("fullname_" + String.valueOf((long)DBQueries.addressesModelList.size() + 1), name.getText().toString());
                                addAddress.put("address_" + String.valueOf((long)DBQueries.addressesModelList.size() + 1), fullAddress);
                                addAddress.put("locality_" + String.valueOf((long)DBQueries.addressesModelList.size() + 1), locality.getText().toString());
                                addAddress.put("selected_" + String.valueOf((long)DBQueries.addressesModelList.size() + 1), true);


                                if (DBQueries.addressesModelList.size() > 0) {
                                    addAddress.put("selected_" + (DBQueries.selectedAddress + 1), false);
                                }

                                FirebaseFirestore.getInstance().collection("USERS").document(FirebaseAuth.getInstance().getUid()).collection("USER_DATA")
                                        .document("MY_ADDRESSES")
                                        .update(addAddress).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {



                                            if (DBQueries.addressesModelList.size() > 0) {
                                                DBQueries.addressesModelList.get(DBQueries.selectedAddress).setSelected(false);
                                            }

                                            DBQueries.addressesModelList.add(new AddressesModel(name.getText().toString(), fullAddress, locality.getText().toString(), true));


                                            if (getIntent().getStringExtra("INTENT").equals("deliveryIntent")) {

                                                Intent deliveryIntent = new Intent(AddAddressActivity.this, DeliveryActivity.class);
                                                startActivity(deliveryIntent);
                                            } else {

                                                MyAddressesActivity.refreshItem(DBQueries.selectedAddress, DBQueries.addressesModelList.size() - 1);

                                            }

                                            DBQueries.selectedAddress = DBQueries.addressesModelList.size() -1;
                                            finish();

                                        }else {
                                            String error = task.getException().getMessage();
                                            Toast.makeText(AddAddressActivity.this, error, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            } else {

                                locality.requestFocus();

                            }
                        } else {

                            street.requestFocus();

                        }
                    } else {

                        flat.requestFocus();

                    }
                } else {

                    name.requestFocus();
                    Toast.makeText(AddAddressActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();

                }



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
