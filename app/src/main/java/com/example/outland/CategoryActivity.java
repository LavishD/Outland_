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

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView categoryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String title = getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryRecyclerView = findViewById(R.id.category_recycler_view);

/////////////// Banner Slider
        List<SliderModel> sliderModelList = new ArrayList<SliderModel>();



        sliderModelList.add(new SliderModel(R.drawable.banner5, "#E3F4FC"));
        sliderModelList.add(new SliderModel(R.drawable.banner7, "#ED1B24"));
        sliderModelList.add(new SliderModel(R.drawable.banner1, "#FCFFF4"));

        sliderModelList.add(new SliderModel(R.drawable.banner2, "#F0F0F2"));
        sliderModelList.add(new SliderModel(R.drawable.banner3, "#F7F6F1"));
        sliderModelList.add(new SliderModel(R.drawable.banner4, "#ED1B24"));
        sliderModelList.add(new SliderModel(R.drawable.banner5, "#E3F4FC"));

        sliderModelList.add(new SliderModel(R.drawable.banner7, "#ED1B24"));
        sliderModelList.add(new SliderModel(R.drawable.banner1, "#FCFFF4"));
        sliderModelList.add(new SliderModel(R.drawable.banner2, "#F0F0F2"));








        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.brd, "₹35", "₹40", "English Oven Premium \n" +
                "Sandwich Bread", "350 g"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.brd, "₹35", "₹40", "English Oven Premium \n" +
                "Sandwich Bread", "350 g"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.brd, "₹35", "₹40", "English Oven Premium \n" +
                "Sandwich Bread", "350 g"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.brd, "₹35", "₹40", "English Oven Premium \n" +
                "Sandwich Bread", "350 g"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.brd, "₹35", "₹40", "English Oven Premium \n" +
                "Sandwich Bread", "350 g"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.brd, "₹35", "₹40", "English Oven Premium \n" +
                "Sandwich Bread", "350 g"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.brd, "₹35", "₹40", "English Oven Premium \n" +
                "Sandwich Bread", "350 g"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.brd, "₹35", "₹40", "English Oven Premium \n" +
                "Sandwich Bread", "350 g"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.brd, "₹35", "₹40", "English Oven Premium \n" +
                "Sandwich Bread", "350 g"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.brd, "₹35", "₹40", "English Oven Premium \n" +
                "Sandwich Bread", "350 g"));



        /////// HS Product Layout

        ////////////////////////

        LinearLayoutManager testingLayoutManger = new LinearLayoutManager(this);
        testingLayoutManger.setOrientation(RecyclerView.VERTICAL);
        categoryRecyclerView.setLayoutManager(testingLayoutManger);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0, sliderModelList));

        homePageModelList.add(new HomePageModel(1, "Deals of the day", horizontalProductScrollModelList));

        homePageModelList.add(new HomePageModel(1, "Buy It Again", horizontalProductScrollModelList));

        homePageModelList.add(new HomePageModel(1, "Deals of the day", horizontalProductScrollModelList));

        homePageModelList.add(new HomePageModel(1, "Buy It Again", horizontalProductScrollModelList));

        homePageModelList.add(new HomePageModel(0, sliderModelList));

        homePageModelList.add(new HomePageModel(0, sliderModelList));


        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        categoryRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

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
