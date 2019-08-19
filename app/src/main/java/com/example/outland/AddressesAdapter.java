package com.example.outland;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.example.outland.DeliveryActivity.SELECT_ADDRESS;
import static com.example.outland.MainActivity.MANAGE_ADDRESS;
import static com.example.outland.MyAddressesActivity.refreshItem;

public class AddressesAdapter extends RecyclerView.Adapter<AddressesAdapter.ViewHolder> {

    private List<AddressesModel> addressesModelList;
    private int MODE;
    private int preSelectedPos;

    public AddressesAdapter(List<AddressesModel> addressesModelList, int MODE) {
        this.addressesModelList = addressesModelList;
        this.MODE = MODE;
        preSelectedPos = DBQueries.selectedAddress;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.addresses_item_layout, viewGroup, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        String name = addressesModelList.get(position).getFullName();
        String address = addressesModelList.get(position).getAddress();
        String locality = addressesModelList.get(position).getLocality();
        boolean selected = addressesModelList.get(position).isSelected();

        viewHolder.setData(name, address, locality, selected, position);

    }

    @Override
    public int getItemCount() {
        return addressesModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView fullName;
        private TextView address;
        private TextView locality;
        private ImageView icon;
        private LinearLayout optionConatianer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fullName = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            locality = itemView.findViewById(R.id.locality);
            icon = itemView.findViewById(R.id.iconView);
            optionConatianer = itemView.findViewById(R.id.option_container);

        }

        private void setData(String userName, String userAddress, String userLocality, final boolean selected, final int position){

            fullName.setText(userName);
            address.setText(userAddress);
            locality.setText(userLocality);

            if (MODE == SELECT_ADDRESS){

                icon.setImageResource(R.mipmap.ic_check_24px);

                if (selected){

                    icon.setVisibility(View.VISIBLE);
                    preSelectedPos = position;

                } else {

                    icon.setVisibility(View.GONE);

                }
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (preSelectedPos != position) {
                            addressesModelList.get(position).setSelected(true);
                            addressesModelList.get(preSelectedPos).setSelected(false);
                            refreshItem(preSelectedPos, position);
                            preSelectedPos = position;
                            DBQueries.selectedAddress = position;
                        }
                    }
                });


            }else if (MODE == MANAGE_ADDRESS){

                optionConatianer.setVisibility(View.GONE);
                icon.setImageResource(R.mipmap.ic_more_vert_24px);
                icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        optionConatianer.setVisibility(View.VISIBLE);
                        refreshItem(preSelectedPos, preSelectedPos);
                        preSelectedPos = position;
                    }
                });
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        refreshItem(preSelectedPos, preSelectedPos);
                        preSelectedPos = -1;


                    }
                });


            }


        }

    }
}
