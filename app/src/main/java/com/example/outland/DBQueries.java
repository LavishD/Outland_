package com.example.outland;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DBQueries {

    public static FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    public static List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();

    public static List<List<HomePageModel>> lists = new ArrayList<>();
    public static List<String> loadedCategoriesName = new ArrayList<>();


    public static void loadCategories( final CategoryAdaptor categoryAdaptor, final Context context) {


        firebaseFirestore.collection("CATEGORIES").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot docSnapshot : task.getResult()) {


                                categoryModelList.add(new CategoryModel(docSnapshot.get("icon").toString(),
                                        docSnapshot.get("name").toString(),
                                        (boolean)docSnapshot.get("Visible")));

                            }
                            categoryAdaptor.notifyDataSetChanged();
                            HomeFragment.swipeRefreshLayout.setRefreshing(false);
                        } else {

                            String error = task.getException().getMessage();
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();

                        }

                    }
                });


    }

    public static void loadFragmentData(final HomePageAdapter homePageAdapter, final Context context, final int index, String categoryName) {
        firebaseFirestore.collection("CATEGORIES").document(categoryName).collection("Top_Deals").orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {


                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot docSnapshot : task.getResult()) {

                        if ((long) docSnapshot.get("view_type") == 0) {

                            List<SliderModel> sliderModelList = new ArrayList<>();
                            long no_of_banners = (long) docSnapshot.get("no_of_banners");
                            for (long x = 1; x < no_of_banners + 1; x++) {

                                sliderModelList.add(new SliderModel(docSnapshot.get("banner_" + x).toString(),
                                        docSnapshot.get("banner_" + x + "_bg").toString()));
                            }
                            lists.get(index).add(new HomePageModel(0, sliderModelList));

                        } else if ((long) docSnapshot.get("view_type") == 1) {

                            List<ViewMoreModel> viewMoreModelList = new ArrayList<>();
                            List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
                            long no_of_products = (long) docSnapshot.get("no_of_products");
                            for (long x = 1; x < no_of_products + 1; x++) {

                                horizontalProductScrollModelList.add(new HorizontalProductScrollModel(docSnapshot.get("product_ID_" + x).toString(),
                                        docSnapshot.get("product_image_" + x).toString(),
                                        docSnapshot.get("product_price_" + x).toString(),
                                        docSnapshot.get("product_mrp_" + x).toString(),
                                        docSnapshot.get("product_name_" + x).toString(),
                                        docSnapshot.get("product_weight_" + x).toString()));

                                viewMoreModelList.add(new ViewMoreModel(docSnapshot.get("product_image_" + x).toString(),
                                        docSnapshot.get("product_price_" + x).toString(),
                                        docSnapshot.get("product_mrp_" + x).toString(),
                                        docSnapshot.get("product_name_" + x).toString(),
                                        docSnapshot.get("product_weight_" + x).toString()));

                            }
                            lists.get(index).add(new HomePageModel(1, docSnapshot.get("layout_title").toString(), horizontalProductScrollModelList, viewMoreModelList));


                        } else if ((long) docSnapshot.get("view_type") == 2) {


                        } else if ((long) docSnapshot.get("view_type") == 3) {

                        }

                    }
                    homePageAdapter.notifyDataSetChanged();
                } else {

                    String error = task.getException().getMessage();
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();

                }

            }
        });


    }

}