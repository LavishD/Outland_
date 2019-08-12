package com.example.outland;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderDetailsItemLayoutAdapter extends RecyclerView.Adapter<OrderDetailsItemLayoutAdapter.ViewHolder> {


    private List<OrderDetailsItemLayoutModel> orderDetailsItemLayoutModelList;
    private boolean orderDetails;

    public OrderDetailsItemLayoutAdapter(List<OrderDetailsItemLayoutModel> orderDetailsItemLayoutModelList, boolean orderDetails) {
        this.orderDetailsItemLayoutModelList = orderDetailsItemLayoutModelList;
        this.orderDetails = orderDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_details_iem_layout,viewGroup,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        int resource = orderDetailsItemLayoutModelList.get(position).getProductImage();
        String productPrice = orderDetailsItemLayoutModelList.get(position).getProductPrice();
        String productMrp = orderDetailsItemLayoutModelList.get(position).getProductMrp();
        String productName = orderDetailsItemLayoutModelList.get(position).getProductName();
        String productWeight = orderDetailsItemLayoutModelList.get(position).getProductWeight();
        String quantity = orderDetailsItemLayoutModelList.get(position).getQuantity();

        viewHolder.setData(resource, productPrice, productMrp, productName, productWeight, quantity);


    }

    @Override
    public int getItemCount() {
        return orderDetailsItemLayoutModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView productImage;
        private TextView productPrice;
        private TextView productMrp;
        private TextView productName;
        private TextView productWeight;
        private TextView productQty;
        private TextView quantity;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.vm_product_image);
            productPrice = itemView.findViewById(R.id.vm_product_price);
            productMrp = itemView.findViewById(R.id.vm_product_mrp);
            productName = itemView.findViewById(R.id.vm_product_name);
            productWeight = itemView.findViewById(R.id.vm_product_weight);
            productQty = itemView.findViewById(R.id.qty_tv);
            quantity = itemView.findViewById(R.id.od_qty);

        }

        private void setData(int resource, String price, String productMrpText, String productNameText, String productWeightText, String quantityText){

            productImage.setImageResource(resource);
            productPrice.setText(price);
            productMrp.setText(productMrpText);
            productName.setText(productNameText);
            productWeight.setText(productWeightText);
            quantity.setText(quantityText);

            if (orderDetails) {

                quantity.setVisibility(View.VISIBLE);
                productQty.setVisibility(View.VISIBLE);

            } else {

                quantity.setVisibility(View.GONE);
                productQty.setVisibility(View.GONE);

            }


        }

    }
}
