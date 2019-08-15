package com.example.outland;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.outland.DBQueries.categoryModelList;
import static com.example.outland.DBQueries.firebaseFirestore;
import static com.example.outland.DBQueries.lists;
import static com.example.outland.DBQueries.loadCategories;
import static com.example.outland.DBQueries.loadFragmentData;
import static com.example.outland.DBQueries.loadedCategoriesName;


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
    private RecyclerView homePageRV;
    private HomePageAdapter homePageAdapter;
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
    private ImageView noInternetConnection;




    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        noInternetConnection = view.findViewById(R.id.no_internet_connection);

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() == true) {
            noInternetConnection.setVisibility(View.GONE);
            categoryRecyclerView = view.findViewById(R.id.category_recycler_view);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(layoutManager.HORIZONTAL);
            categoryRecyclerView.setLayoutManager(layoutManager);


            categoryAdaptor = new CategoryAdaptor(categoryModelList);
            categoryRecyclerView.setAdapter(categoryAdaptor);

            if (categoryModelList.size() == 0){

                loadCategories(categoryAdaptor, getContext());

            } else {

                categoryAdaptor.notifyDataSetChanged();

            }

            /////// HS Product Layout

            ////////////////////////
            homePageRV = view.findViewById(R.id.home_page_recycler_view);
            LinearLayoutManager testingLayoutManger = new LinearLayoutManager(getContext());
            testingLayoutManger.setOrientation(RecyclerView.VERTICAL);
            homePageRV.setLayoutManager(testingLayoutManger);

            if (lists.size() == 0){
                loadedCategoriesName.add("Home");
                lists.add(new ArrayList<HomePageModel>());
                homePageAdapter = new HomePageAdapter(lists.get(0));
                loadFragmentData(homePageAdapter, getContext(), 0, "Home");

            } else {
                homePageAdapter = new HomePageAdapter(lists.get(0));
                homePageAdapter.notifyDataSetChanged();

            }

            homePageRV.setAdapter(homePageAdapter);


        } else {
            Glide.with(this).load(R.drawable.brd).into(noInternetConnection);
            noInternetConnection.setVisibility(View.VISIBLE);
        }



        return view;
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
