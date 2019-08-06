package com.example.outland;

import android.content.Context;
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
import android.widget.LinearLayout;
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

    ////////////banner
    private ViewPager bannerSliderViewPager;
    private List<SliderModel> sliderModelList;
    private int currentPage = 2;
    private Timer timer;
    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;
    /////////

    /////// HS Product Layout
    private TextView hSlayoutTextView;
    private TextView hSViewMoreTextView;
    private RecyclerView hSRecyclerView;
    /////// HS Product Layout





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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

        //////// Banner

        bannerSliderViewPager = view.findViewById(R.id.banner_slider_view_pager);

        sliderModelList = new ArrayList<SliderModel>();



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





        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
        bannerSliderViewPager.setAdapter(sliderAdapter);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(60);

        bannerSliderViewPager.setCurrentItem(currentPage);


        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int i) {
                    currentPage = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if (i == ViewPager.SCROLL_STATE_IDLE) {

                    pageLooper();
                }
            }
        };
        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

        startBannerSlideShow();
        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                pageLooper();
                stopBannerSlideShow();

                if (motionEvent.getAction() == MotionEvent.ACTION_UP){

                    startBannerSlideShow();
                }

                return false;
            }
        });

        /////// HS Product Layout
        hSlayoutTextView = view.findViewById(R.id.horizontal_scroll_layout_title);
        hSViewMoreTextView = view.findViewById(R.id.horizontal_scroll_view_more);
        hSRecyclerView = view.findViewById(R.id.horizontal_product_recycler_view);

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

        HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        hSRecyclerView.setLayoutManager(linearLayoutManager);
        hSRecyclerView.setAdapter(horizontalProductScrollAdapter);

        horizontalProductScrollAdapter.notifyDataSetChanged();

        /////// HS Product Layout

        ////////////////////////
        RecyclerView testing = view.findViewById(R.id.testing);
        LinearLayoutManager testingLayoutManger = new LinearLayoutManager(getContext());
        testingLayoutManger.setOrientation(RecyclerView.VERTICAL);
        testing.setLayoutManager(testingLayoutManger);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0, sliderModelList));

        homePageModelList.add(new HomePageModel(1, "Deals of the day", horizontalProductScrollModelList));

        homePageModelList.add(new HomePageModel(1, "Buy It Again", horizontalProductScrollModelList));

        homePageModelList.add(new HomePageModel(0, sliderModelList));

        homePageModelList.add(new HomePageModel(0, sliderModelList));


        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        testing.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        ///////////////////////


        return view;
    }

    ////// Banner

    private void pageLooper(){

        if (currentPage == sliderModelList.size() - 2) {

            currentPage = 2;
            bannerSliderViewPager.setCurrentItem(currentPage, false);

        }
        if (currentPage == 1) {

            currentPage = sliderModelList.size() -3;
            bannerSliderViewPager.setCurrentItem(currentPage, false);

        }



    }

    private void startBannerSlideShow(){

        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage >= sliderModelList.size()){

                    currentPage =1;
                }
                bannerSliderViewPager.setCurrentItem(currentPage++,true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_TIME, PERIOD_TIME);
    }

    private void stopBannerSlideShow(){
        timer.cancel();
    }


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
