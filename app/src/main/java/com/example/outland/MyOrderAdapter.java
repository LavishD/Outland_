package com.example.outland;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    private List<MyOrderItemModel> myOrderItemModelList;

    public MyOrderAdapter(List<MyOrderItemModel> myOrderItemModelList) {
        this.myOrderItemModelList = myOrderItemModelList;
    }

    @NonNull
    @Override
    public MyOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_order_item_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderAdapter.ViewHolder viewHolder, int position) {

        String orderNo = myOrderItemModelList.get(position).getOrderNumber();
        String deliveryStatus = myOrderItemModelList.get(position).getDeliveryStatus();
        String dateAndTimeAndItemsNo = myOrderItemModelList.get(position).getDateTimeAndItems();

        viewHolder.setData(orderNo, deliveryStatus, dateAndTimeAndItemsNo);

    }

    @Override
    public int getItemCount() {
        return myOrderItemModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView deliveredDot;
        private TextView orderNo;
        private TextView deliveryStatus;
        private TextView dateAndTimeAndItemsNo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            deliveredDot = itemView.findViewById(R.id.delivery_dot);
            orderNo = itemView.findViewById(R.id.order_no);
            dateAndTimeAndItemsNo = itemView.findViewById(R.id.date_time_items_tv);
            deliveryStatus = itemView.findViewById(R.id.delivery_status);



        }

        private void setData(String orderNoText, String dateAndTimeAndItemsNoText, String deliverStatusText){


            orderNo.setText(orderNoText);
            dateAndTimeAndItemsNo.setText(dateAndTimeAndItemsNoText);
            if (deliverStatusText.equals("Cancelled")) {

                deliveredDot.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.colorPrimary)));
                deliveryStatus.setTextColor(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.colorPrimary)));
            } else {

                deliveredDot.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.successGreen)));
                deliveryStatus.setTextColor(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.successGreen)));

            }
            deliveryStatus.setText(deliverStatusText);

        }
    }

}




























