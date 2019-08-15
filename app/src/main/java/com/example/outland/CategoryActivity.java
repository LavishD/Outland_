package com.example.outland;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.outland.DBQueries.lists;
import static com.example.outland.DBQueries.loadFragmentData;
import static com.example.outland.DBQueries.loadedCategoriesName;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView categoryRecyclerView;
    private HomePageAdapter homePageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_category);
        super.onCreate(savedInstanceState);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String title = getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryRecyclerView = findViewById(R.id.category_recycler_view);


        ////////////////////////

        LinearLayoutManager testingLayoutManger = new LinearLayoutManager(this);
        testingLayoutManger.setOrientation(RecyclerView.VERTICAL);
        categoryRecyclerView.setLayoutManager(testingLayoutManger);


        int listPos = 0;
        for (int x = 0; x < loadedCategoriesName.size(); x++) {

            if (loadedCategoriesName.get(x).equals(title)){

                listPos = x;

            }
        }

        if (listPos == 0){
            loadedCategoriesName.add(title);
            lists.add(new ArrayList<HomePageModel>());
            homePageAdapter = new HomePageAdapter(lists.get(loadedCategoriesName.size() - 1));
            loadFragmentData(homePageAdapter, this, loadedCategoriesName.size() - 1, title);
        }else {
            homePageAdapter = new HomePageAdapter(lists.get(listPos));



        }

        categoryRecyclerView.setAdapter(homePageAdapter);
        homePageAdapter.notifyDataSetChanged();

        ///////////////////////

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.main_search_icon) {

            return true;
        } else if (id == R.id.main_cart_icon){

            return true;
        } else if (id == android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
