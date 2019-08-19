package com.example.outland;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {
    private List<CartItemModel> cartItemModelList;
    //private TextView cartTotalAmount;


    public CartAdapter(List<CartItemModel> cartItemModelList /*TextView cartTotalAmount*/) {
        this.cartItemModelList = cartItemModelList;
       // this.cartTotalAmount = cartTotalAmount;
    }

    @Override
    public int getItemViewType(int position) {
        switch (cartItemModelList.get(position).getType()){

            case 0:
                return CartItemModel.CART_ITEM;
            case 1:
                return CartItemModel.CART_SUMMARY;
            default:
                return -1;


        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType){

            case CartItemModel.CART_ITEM:
                View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item_layout, viewGroup, false);
                return new CartItemViewHolder(itemView);
            case CartItemModel.CART_SUMMARY:
                View summaryView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_summary_layout, viewGroup, false);
                return new CartSummaryViewHolder(summaryView);
            default:
                return null;

        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        switch (cartItemModelList.get(position).getType()){

            case CartItemModel.CART_ITEM:
                int resource = cartItemModelList.get(position).getProductImage();
                String productMrp = cartItemModelList.get(position).getProductMrp();
                String productPrice = cartItemModelList.get(position).getProductPrice();
                String productName = cartItemModelList.get(position).getProductNam();
                String productWeight = cartItemModelList.get(position).getProductWeight();

                ((CartItemViewHolder)viewHolder).setItemDetails(resource,productMrp, productPrice, productName, productWeight);
                break;

            case CartItemModel.CART_SUMMARY:
                String totalMrp = cartItemModelList.get(position).getTotalMrp();
                String discountReceived = cartItemModelList.get(position).getDiscountReceived();
                String deliveryCharges = cartItemModelList.get(position).getDeliverCharges();
                String subtotal = cartItemModelList.get(position).getSubTotal();
                // String checkoutTotal = cartItemModelList.get(position).getCheckoutTotal();


                ((CartSummaryViewHolder)viewHolder).setSummary(totalMrp, discountReceived, deliveryCharges,subtotal);

                break;
            default:


        }


    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }

    class CartItemViewHolder extends RecyclerView.ViewHolder{

        private ImageView productImage;
        private TextView productName;
        private TextView productMrp;
        private TextView productPrice;
        private TextView productWeight;
        private TextView productQty;


        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.vm_product_image);
            productMrp = itemView.findViewById(R.id.vm_product_mrp);
            productPrice = itemView.findViewById(R.id.vm_product_price);
            productName = itemView.findViewById(R.id.vm_product_name);
            productWeight = itemView.findViewById(R.id.vm_product_weight);
            productQty = itemView.findViewById(R.id.cart_product_qty);
        }

        private void setItemDetails(int resource, String mrp, String price, String name, String weight) {

            productImage.setImageResource(resource);
            productMrp.setText(mrp);
            productPrice.setText(price);
            productName.setText(name);
            productWeight.setText(weight);

        }

    }

    class CartSummaryViewHolder extends RecyclerView.ViewHolder{

        private TextView totalmrp;
        private TextView discountRecevied;
        private TextView deliveryCharges;
        private TextView subtotal;
       // private TextView checkoutTotal;


        public CartSummaryViewHolder(@NonNull View itemView) {
            super(itemView);

            totalmrp = itemView.findViewById(R.id.summary_mrp_amount);
            discountRecevied = itemView.findViewById(R.id.summary_discount_amount);
            deliveryCharges = itemView.findViewById(R.id.summary_delivery_charges_amount);
            subtotal = itemView.findViewById(R.id.summary_subtotal);
           // checkoutTotal = itemView.findViewById(R.id.total_cart_price);

        }

        private void setSummary(String mrpText, String discountText, String deliveryChargesText, String subtotalText /*String checkoutTotalText*/){

            totalmrp.setText(mrpText);
            discountRecevied.setText(discountText);
            deliveryCharges.setText(deliveryChargesText);
            subtotal.setText(subtotalText);
           // checkoutTotal.setText(checkoutTotalText);

        }
    }
}
