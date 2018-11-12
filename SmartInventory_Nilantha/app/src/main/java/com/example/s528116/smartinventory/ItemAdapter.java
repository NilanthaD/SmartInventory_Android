package com.example.s528116.smartinventory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public ItemAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            ListItem listItem = listItems.get(i);
            viewHolder.imageIV.setImageResource(listItem.getImage());
            viewHolder.itemNameTV.setText(listItem.getItemName());
            viewHolder.itemPriceTV.setText(listItem.getItemPrice());
            viewHolder.numOfItemsTV.setText(listItem.getNuOfItems());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageIV;
        public TextView itemNameTV;
        public TextView itemPriceTV;
        public TextView numOfItemsTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIV = itemView.findViewById(R.id.imageIV);
            itemNameTV = itemView.findViewById(R.id.itemNameTV);
            itemPriceTV = itemView.findViewById(R.id.itemPriceTV);
            numOfItemsTV = itemView.findViewById(R.id.numOfItemsTV);
        }
    }
}
