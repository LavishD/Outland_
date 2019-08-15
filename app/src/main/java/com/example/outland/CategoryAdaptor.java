package com.example.outland;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.ViewHolder> {


    private List<CategoryModel> categoryModelList;



    public CategoryAdaptor(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public CategoryAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.catagory_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdaptor.ViewHolder holder, int position) {

        String icon = categoryModelList.get(position).getCategoryIconLink();
        String name = categoryModelList.get(position).getCategoryName();
        boolean isVisible = categoryModelList.get(position).isVisible();
        holder.setCategoryName(icon, name, position);

    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView categoryIcon;
        private TextView categoryName;
        private ConstraintLayout categoryItemBGLayout;
        private ConstraintLayout categoryItemLayout;
        private RecyclerView category_recycler_view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIcon = itemView.findViewById(R.id.category_icon);
            categoryName = itemView.findViewById(R.id.category_name);
            categoryItemLayout = itemView.findViewById(R.id.layout_testing);
            categoryItemBGLayout = itemView.findViewById(R.id.category_bg_layout);
            category_recycler_view = itemView.findViewById(R.id.category_recycler_view);
        }




        private void setCategoryName(String iconUrl, final String name, final int position){



            if (!iconUrl.equals("null")) {
                Glide.with(itemView.getContext()).load(iconUrl).apply(new RequestOptions().placeholder(R.mipmap.home_icon)).into(categoryIcon);
            }
            categoryName.setText(name);


          //  if (isVisible){

                categoryName.setVisibility(View.VISIBLE);
                categoryIcon.setVisibility(View.VISIBLE);
              //  categoryItemLayout.setVisibility(View.VISIBLE);
//                category_recycler_view.setVisibility(View.VISIBLE);

            //} else {
             //   categoryName.setVisibility(View.GONE);
              //  categoryIcon.setVisibility(View.GONE);
               // categoryItemLayout.setVisibility(View.GONE);
                //
                //category_recycler_view.setVisibility(View.GONE);

            //}


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position != 0) {
                        Intent categoryIntent = new Intent(itemView.getContext(), CategoryActivity.class);
                        categoryIntent.putExtra("CategoryName", name);
                        itemView.getContext().startActivity(categoryIntent);
                    }

                }
            });

        }

    }
}
