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

import org.w3c.dom.Text;

import java.util.List;

public class HorizontalProductScrollAdapter extends RecyclerView.Adapter<HorizontalProductScrollAdapter.ViewHolder> {


    private List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    public HorizontalProductScrollAdapter(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    @NonNull
    @Override
    public HorizontalProductScrollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_scroll_item_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalProductScrollAdapter.ViewHolder viewHolder, int position) {


        String resource = horizontalProductScrollModelList.get(position).getProductImage();
        String price = horizontalProductScrollModelList.get(position).getProductPrice();
        String mrp = horizontalProductScrollModelList.get(position).getProductMRP();
        String name = horizontalProductScrollModelList.get(position).getProductName();
        String weight = horizontalProductScrollModelList.get(position).getProductWeight();

        viewHolder.setProductImage(resource);
        viewHolder.setProductPrice(price);
        viewHolder.setProductMRP(mrp);
        viewHolder.setProductName(name);
        viewHolder.setProductWeight(weight);

    }

    @Override
    public int getItemCount() {
        if (horizontalProductScrollModelList.size() > 8){

            return 8;
        } else {

            return horizontalProductScrollModelList.size();

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productPrice;
        private TextView productMRP;
        private TextView productName;
        private TextView productWeight;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.hs_product_image);
            productPrice = itemView.findViewById(R.id.hs_product_price);
            productMRP = itemView.findViewById(R.id.hs_product_mrp);
            productName = itemView.findViewById(R.id.hs_product_name);
            productWeight = itemView.findViewById(R.id.hs_product_weight);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent productDetailsIntent = new Intent(itemView.getContext(), ProductDetailsActivity.class);
                    itemView.getContext().startActivity(productDetailsIntent);

                }
            });

        }

        private void setProductImage(String resource){

            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().
                    placeholder(R.drawable.brd)).into(productImage);

        }
        private void setProductPrice(String price){
            productPrice.setText("₹" + price);

        }

        private void setProductMRP(String mrp) {
            productMRP.setText("₹" + mrp);
        }

        private void setProductName(String name) {
            productName.setText(name);
        }

        private void setProductWeight(String weight) {
            productWeight.setText(weight);
        }
    }
}

