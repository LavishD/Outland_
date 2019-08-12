package com.example.outland;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import static com.example.outland.DeliveryActivity.SELECT_ADDRESS;

public class MyAddressesActivity extends AppCompatActivity {


    private RecyclerView myAddressesRV;
    private Button deliverHereBtn;
    private static AddressesAdapter addressesAdapter;


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

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myAddressesRV.setLayoutManager(layoutManager);

        List<AddressesModel> addressesModelList = new ArrayList<>();

        addressesModelList.add(new AddressesModel("Piyush Goel", "65, Outer Ring Rd", "State Bank Nagar, Paschim Vihar, Delhi", true));
        addressesModelList.add(new AddressesModel("Piyush Goel", "65, Outer Ring Rd", "State Bank Nagar, Paschim Vihar, Delhi", false));
        addressesModelList.add(new AddressesModel("Piyush Goel", "65, Outer Ring Rd", "State Bank Nagar, Paschim Vihar, Delhi", false));
        addressesModelList.add(new AddressesModel("Piyush Goel", "65, Outer Ring Rd", "State Bank Nagar, Paschim Vihar, Delhi", false));
        addressesModelList.add(new AddressesModel("Piyush Goel", "65, Outer Ring Rd", "State Bank Nagar, Paschim Vihar, Delhi", false));
        addressesModelList.add(new AddressesModel("Piyush Goel", "65, Outer Ring Rd", "State Bank Nagar, Paschim Vihar, Delhi", false));
        addressesModelList.add(new AddressesModel("Piyush Goel", "65, Outer Ring Rd", "State Bank Nagar, Paschim Vihar, Delhi", false));


        int mode = getIntent().getIntExtra("MODE",-1);
        if (mode == SELECT_ADDRESS){

            deliverHereBtn.setVisibility(View.VISIBLE);

        }else {

            deliverHereBtn.setVisibility(View.GONE);

        }
        addressesAdapter = new AddressesAdapter(addressesModelList,mode);
        myAddressesRV.setAdapter(addressesAdapter);
        ((SimpleItemAnimator)myAddressesRV.getItemAnimator()).setSupportsChangeAnimations(false);
        addressesAdapter.notifyDataSetChanged();


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

            finish();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
