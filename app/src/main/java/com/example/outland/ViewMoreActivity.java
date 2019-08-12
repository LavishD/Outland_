package com.example.outland;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ViewMoreActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Deals of the day");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        List<ViewMoreModel> viewMoreModelList = new ArrayList<>();
        viewMoreModelList.add(new ViewMoreModel(R.drawable.brd,"30","35","English Oven Premium Sandwich \nBread","350 g","Qty:","1"));
        viewMoreModelList.add(new ViewMoreModel(R.drawable.brd,"30","35","English Oven Premium Sandwich \nBread","350 g","Qty:","2"));
        viewMoreModelList.add(new ViewMoreModel(R.drawable.brd,"30","35","English Oven Premium Sandwich \nBread","350 g","Qty:","3"));
        viewMoreModelList.add(new ViewMoreModel(R.drawable.brd,"30","35","English Oven Premium Sandwich \nBread","350 g","Qty:","1"));
        viewMoreModelList.add(new ViewMoreModel(R.drawable.brd,"30","35","English Oven Premium Sandwich \nBread","350 g","Qty:","2"));

        ViewMoreAdapter adapter = new ViewMoreAdapter(viewMoreModelList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

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
