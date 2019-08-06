package com.example.outland;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
        holder.setCategoryName(name);
        holder.setCategoryIcon(icon);

    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView categoryIcon;
        private TextView categoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIcon = itemView.findViewById(R.id.category_icon);
            categoryName = itemView.findViewById(R.id.category_name);
        }

        private  void  setCategoryIcon(String iconUrl){

            //set category icons here
            Glide.with(itemView.getContext()).load(iconUrl).apply(new RequestOptions().placeholder(R.mipmap.home_icon)).into(categoryIcon);

        }

        private void setCategoryName(final String name){

            categoryName.setText(name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent categoryIntent = new Intent(itemView.getContext(), CategoryActivity.class);
                    categoryIntent.putExtra("CategoryName", name);
                    itemView.getContext().startActivity(categoryIntent);


                }
            });

        }

    }
}
