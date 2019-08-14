package com.example.outland;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private List<CategoryModel> categoryModelList;
    private RecyclerView homePageRV;
    private HomePageAdapter adapter;
    private FirebaseFirestore firebaseFirestore;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdaptor categoryAdaptor;







    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecyclerView = view.findViewById(R.id.category_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(layoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        categoryModelList = new ArrayList<CategoryModel>();

        categoryAdaptor = new CategoryAdaptor(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdaptor);


        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("CATEGORIES").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){

                            for (QueryDocumentSnapshot docSnapshot: task.getResult()){


                                categoryModelList.add(new CategoryModel(docSnapshot.get("icon").toString(),
                                        docSnapshot.get("name").toString()));

                            }
                            categoryAdaptor.notifyDataSetChanged();
                        } else {

                            String error = task.getException().getMessage();
                            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();

                        }

                    }
                });

        ///////////// Banner

        //////// Ba







//        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.brd, "₹35", "₹40", "English Oven Premium \n" +
//                "Sandwich Bread", "350 g"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.brd, "₹35", "₹40", "English Oven Premium \n" +
//                "Sandwich Bread", "350 g"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.brd, "₹35", "₹40", "English Oven Premium \n" +
//                "Sandwich Bread", "350 g"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.brd, "₹35", "₹40", "English Oven Premium \n" +
//                "Sandwich Bread", "350 g"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.brd, "₹35", "₹40", "English Oven Premium \n" +
//                "Sandwich Bread", "350 g"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.brd, "₹35", "₹40", "English Oven Premium \n" +
//                "Sandwich Bread", "350 g"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.brd, "₹35", "₹40", "English Oven Premium \n" +
//                "Sandwich Bread", "350 g"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.brd, "₹35", "₹40", "English Oven Premium \n" +
//                "Sandwich Bread", "350 g"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.brd, "₹35", "₹40", "English Oven Premium \n" +
//                "Sandwich Bread", "350 g"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.brd, "₹35", "₹40", "English Oven Premium \n" +
//                "Sandwich Bread", "350 g"));
//


        /////// HS Product Layout

        ////////////////////////
        homePageRV = view.findViewById(R.id.home_page_recycler_view);
        LinearLayoutManager testingLayoutManger = new LinearLayoutManager(getContext());
        testingLayoutManger.setOrientation(RecyclerView.VERTICAL);
        homePageRV.setLayoutManager(testingLayoutManger);
        final List<HomePageModel> homePageModelList = new ArrayList<>();
        adapter = new HomePageAdapter(homePageModelList);
        homePageRV.setAdapter(adapter);

        firebaseFirestore.collection("HOME").document("PROMO").collection("SLIDER").orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {


                if (task.isSuccessful()){

                    for (QueryDocumentSnapshot docSnapshot: task.getResult()){

                        if ((long)docSnapshot.get("view_type") == 0) {

                            List<SliderModel> sliderModelList = new ArrayList<>();
                            long no_of_banners = (long)docSnapshot.get("no_of_banners");
                            for (long x = 1; x < no_of_banners +1;x++){

                                sliderModelList.add(new SliderModel(docSnapshot.get("banner_"+x).toString(),
                                        docSnapshot.get("banner_"+x+"_bg").toString()));
                            }
                            homePageModelList.add(new HomePageModel(0,sliderModelList));

                        } else if ((long)docSnapshot.get("view_type") == 1) {


                            List<HorizontalProductScrollModel> horizontalProductScrollModelList =  new ArrayList<>();
                            long no_of_products = (long)docSnapshot.get("no_of_products");
                            for (long x = 1; x < no_of_products +1;x++){

                                horizontalProductScrollModelList.add(new HorizontalProductScrollModel(docSnapshot.get("product_ID_"+x).toString(),
                                        docSnapshot.get("product_image_"+x).toString(),
                                        docSnapshot.get("product_price_"+x).toString(),
                                        docSnapshot.get("product_mrp_"+x).toString(),
                                        docSnapshot.get("product_name_"+x).toString(),
                                        docSnapshot.get("product_weight_"+x).toString()));

                            }
                            homePageModelList.add(new HomePageModel(1,docSnapshot.get("layout_title").toString(), horizontalProductScrollModelList));


                        }else if ((long)docSnapshot.get("view_type") == 2){


                        }else if ((long)docSnapshot.get("view_type") == 3){

                        }

                    }
                    adapter.notifyDataSetChanged();
                    categoryAdaptor.notifyDataSetChanged();
                } else {

                    String error = task.getException().getMessage();
                    Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();

                }

            }
        });



        ///////////////////////


        return view;
    }

    ////// Banner




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }




    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
