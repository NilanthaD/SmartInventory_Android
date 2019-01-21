package com.example.s528116.smartinventory;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {
    private ArrayList<ItemContainer> itemListArray;

    public static class ItemsViewHolder extends RecyclerView.ViewHolder{
        public ImageView itemImage;
        public TextView itemID;
        public TextView itemName;
        public TextView unitPrice;
        public TextView quantityNeeded;

        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.imageIV);
            itemID = itemView.findViewById(R.id.itemIdTV);
            itemName =itemView.findViewById(R.id.iNameTV);
            unitPrice = itemView.findViewById(R.id.priceTV);
            quantityNeeded = itemView.findViewById(R.id.qntyNeededTV);
        }
    }

    public ItemsAdapter(ArrayList<ItemContainer> itemListArray) {
        this.itemListArray = itemListArray;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_container, viewGroup,false);
        ItemsViewHolder itemsVH = new ItemsViewHolder(v);
        return itemsVH;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder itemsViewHolder, int i) {
        ItemContainer currentItem = itemListArray.get(i);
        itemsViewHolder.itemImage.setImageResource(currentItem.getImage());
        itemsViewHolder.itemID.setText(currentItem.getItemID());
        itemsViewHolder.itemName.setText(currentItem.getItemName());
        itemsViewHolder.unitPrice.setText(currentItem.getUnitPrice());
        itemsViewHolder.quantityNeeded.setText(currentItem.getQntyNeeded());


    }

    @Override
    public int getItemCount() {
        return itemListArray.size();
    }
}
