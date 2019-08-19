package com.example.outland;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.outland.DeliveryActivity.SELECT_ADDRESS;

public class MyAddressesActivity extends AppCompatActivity {

    private int previousAddress;

    private RecyclerView myAddressesRV;
    private Button deliverHereBtn;
    private static AddressesAdapter addressesAdapter;
    private LinearLayout addBtn;
    private TextView totalAddresses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_addresses);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("My Addresses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myAddressesRV = findViewById(R.id.addressesRV);
        deliverHereBtn = findViewById(R.id.deliver_here_btn);
        addBtn = findViewById(R.id.add_new_address_btn);
        totalAddresses = findViewById(R.id.total_addresses);
        previousAddress = DBQueries.selectedAddress;

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myAddressesRV.setLayoutManager(layoutManager);

        int mode = getIntent().getIntExtra("MODE",-1);
        if (mode == SELECT_ADDRESS){

            deliverHereBtn.setVisibility(View.VISIBLE);

        }else {

            deliverHereBtn.setVisibility(View.GONE);

        }

        deliverHereBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DBQueries.selectedAddress != previousAddress){
                    final int previousAddressIndex = previousAddress;

                    Map<String, Object> updateSelection =  new HashMap<>();
                    updateSelection.put("selected_"+ String.valueOf(previousAddress + 1), false);
                    updateSelection.put("selected_"+ String.valueOf(DBQueries.selectedAddress + 1), true);

                    previousAddress = DBQueries.selectedAddress;

                    FirebaseFirestore.getInstance().collection("USERS").document(FirebaseAuth.getInstance().getUid()).collection("USER_DATA")
                            .document("MY_ADDRESSES").update(updateSelection).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){

                                finish();

                            }else {

                                previousAddress = previousAddressIndex;
                                String error = task.getException().getMessage();
                                Toast.makeText(MyAddressesActivity.this, error, Toast.LENGTH_SHORT).show();

                            }


                        }
                    });

                } else {

                    finish();

                }
            }
        });

        addressesAdapter = new AddressesAdapter(DBQueries.addressesModelList,mode);
        myAddressesRV.setAdapter(addressesAdapter);
        ((SimpleItemAnimator)myAddressesRV.getItemAnimator()).setSupportsChangeAnimations(false);
        addressesAdapter.notifyDataSetChanged();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent addAddressIntent = new Intent(MyAddressesActivity.this, AddAddressActivity.class);
                addAddressIntent.putExtra("INTENT", "null");
                startActivity(addAddressIntent);

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        totalAddresses.setText(String.valueOf("Total " +DBQueries.addressesModelList.size() + " addresses."));

    }

    public static void refreshItem(int deselect, int select){

        addressesAdapter.notifyItemChanged(deselect);
        addressesAdapter.notifyItemChanged(select);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home){
            if (DBQueries.selectedAddress != previousAddress){

                DBQueries.addressesModelList.get(DBQueries.selectedAddress).setSelected(false);
                DBQueries.addressesModelList.get(previousAddress).setSelected(true);
                DBQueries.selectedAddress = previousAddress;

            }
            finish();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (DBQueries.selectedAddress != previousAddress){

            DBQueries.addressesModelList.get(DBQueries.selectedAddress).setSelected(false);
            DBQueries.addressesModelList.get(previousAddress).setSelected(true);
            DBQueries.selectedAddress = previousAddress;

        }
        super.onBackPressed();
    }
}
