package com.example.outland;

import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter {

    private List<HomePageModel> homePageModelList;
    private RecyclerView.RecycledViewPool recycledViewPool;

    public HomePageAdapter(List<HomePageModel> homePageModelList) {
        this.homePageModelList = homePageModelList;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    public int getItemViewType(int position) {
        switch (homePageModelList.get(position).getType()){
         case 0:
            return HomePageModel.BANNER_SLIDER;
         case 1:
                return HomePageModel.HORRIZONTAL_PRODUCT_VIEW;
         default:
             return -1;

        }
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        switch (viewType){
            case HomePageModel.BANNER_SLIDER:
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sliding_ad_layout, viewGroup, false);
                return new BannerSliderViewHolder(view);
            case HomePageModel.HORRIZONTAL_PRODUCT_VIEW:
                View hSProductview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horrizontal_scroll_layout, viewGroup, false);
                return new HSProductViewHolder(hSProductview);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        switch (homePageModelList.get(position).getType()){
            case HomePageModel.BANNER_SLIDER:
                    List<SliderModel> sliderModelList = homePageModelList.get(position).getSliderModelList();
                    ((BannerSliderViewHolder) viewHolder).setBannerSliderViewPager(sliderModelList);
                    break;
            case HomePageModel.HORRIZONTAL_PRODUCT_VIEW:
                String title = homePageModelList.get(position).getTitle();
                List<ViewMoreModel> viewMoreModelList = homePageModelList.get(position).getViewMoreModelList();
                List<HorizontalProductScrollModel> horizontalProductScrollModelList = homePageModelList.get(position).getHorizontalProductScrollModelList();
                ((HSProductViewHolder)viewHolder).setHSProductLayout(horizontalProductScrollModelList, title, viewMoreModelList);
                break;
                default:
                    return;

        }

    }

    @Override
    public int getItemCount() {
        return homePageModelList.size();
    }


    public class BannerSliderViewHolder extends RecyclerView.ViewHolder{
        ////////////banner
        private ViewPager bannerSliderViewPager;
        private int currentPage;
        private Timer timer;
        final private long DELAY_TIME = 3000;
        final private long PERIOD_TIME = 3000;
        private List<SliderModel> arrangeList;
        /////////
        public BannerSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            bannerSliderViewPager = itemView.findViewById(R.id.banner_slider_view_pager);



        }
        private void setBannerSliderViewPager(final List<SliderModel> sliderModelList){
            currentPage = 2;
            if (timer != null){

                timer.cancel();

            }

            arrangeList = new ArrayList<>();
            for (int x = 0;x < sliderModelList.size() ;x++   ){

                arrangeList.add(x, sliderModelList.get(x));


            }
            arrangeList.add(0, sliderModelList.get(sliderModelList.size() -2 ));
            arrangeList.add(1, sliderModelList.get(sliderModelList.size() -1 ));
            arrangeList.add(sliderModelList.get(0));
            arrangeList.add(sliderModelList.get(1));


            SliderAdapter sliderAdapter = new SliderAdapter(arrangeList);
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

                        pageLooper(arrangeList);
                    }
                }
            };
            bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

            startBannerSlideShow(arrangeList);
            bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    pageLooper(arrangeList);
                    stopBannerSlideShow();

                    if (motionEvent.getAction() == MotionEvent.ACTION_UP){

                        startBannerSlideShow(arrangeList);
                    }

                    return false;
                }
            });


        }

        private void pageLooper(List<SliderModel> sliderModelList){

            if (currentPage == sliderModelList.size() - 2) {

                currentPage = 2;
                bannerSliderViewPager.setCurrentItem(currentPage, false);

            }
            if (currentPage == 1) {

                currentPage = sliderModelList.size() -3;
                bannerSliderViewPager.setCurrentItem(currentPage, false);

            }



        }

        private void startBannerSlideShow(final List<SliderModel> sliderModelList){

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

    }

    public class HSProductViewHolder extends  RecyclerView.ViewHolder{


        private TextView hSlayoutTextView;
        private TextView hSViewMoreTextView;
        private RecyclerView hSRecyclerView;


        public HSProductViewHolder(@NonNull View itemView) {
            super(itemView);
            hSlayoutTextView = itemView.findViewById(R.id.horizontal_scroll_layout_title);
            hSViewMoreTextView = itemView.findViewById(R.id.horizontal_scroll_view_more);
            hSRecyclerView = itemView.findViewById(R.id.horizontal_product_recycler_view);
            hSRecyclerView.setRecycledViewPool(recycledViewPool);
        }

        private void setHSProductLayout(List<HorizontalProductScrollModel> horizontalProductScrollModelList, final String title, final List<ViewMoreModel> viewMoreModelList){

            hSlayoutTextView.setText(title);

            if (horizontalProductScrollModelList.size() > 8){

                hSViewMoreTextView.setVisibility(View.VISIBLE);
                hSViewMoreTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ViewMoreActivity.viewMoreModelList = viewMoreModelList;

                        Intent viewMoreIntent = new Intent(itemView.getContext(), ViewMoreActivity.class);
                        viewMoreIntent.putExtra("title", title);
                        itemView.getContext().startActivity(viewMoreIntent);
                    }
                });

            } else {

                hSViewMoreTextView.setVisibility(View.INVISIBLE);

            }

            HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            hSRecyclerView.setLayoutManager(linearLayoutManager);
            hSRecyclerView.setAdapter(horizontalProductScrollAdapter);
            horizontalProductScrollAdapter.notifyDataSetChanged();

        }
    }
}
