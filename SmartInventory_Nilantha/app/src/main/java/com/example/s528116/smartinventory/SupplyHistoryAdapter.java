package com.example.s528116.smartinventory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SupplyHistoryAdapter extends RecyclerView.Adapter<SupplyHistoryAdapter.SupplyHistoryViewHoder> {

    private ArrayList<SupplyHistory> supplyListAL;
    private Context context;

    public SupplyHistoryAdapter(ArrayList<SupplyHistory> supplyListArray, Context context) {
        this.supplyListAL = supplyListArray;
        this.context = context;
    }

    public static class SupplyHistoryViewHoder extends RecyclerView.ViewHolder {
        public ImageView itemImageIV;
        public TextView statusTV;
        public TextView itemNameTV;
        public TextView unitPriceTV;
        public TextView numberOfUnitsTV;
        public TextView dateTV;

        public SupplyHistoryViewHoder(@NonNull View supplyHistoryView) {
            super(supplyHistoryView);
            itemImageIV = supplyHistoryView.findViewById(R.id.itemImageIV);
            statusTV = supplyHistoryView.findViewById(R.id.statusTV);
            itemNameTV = supplyHistoryView.findViewById(R.id.itemNameTV);
            unitPriceTV = supplyHistoryView.findViewById(R.id.unitPriceTV);
            numberOfUnitsTV = supplyHistoryView.findViewById(R.id.numberOfUnitsTV);
            dateTV = supplyHistoryView.findViewById(R.id.dateTV);

        }
    }

    @NonNull
    @Override
    public SupplyHistoryViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.supply_history_container, viewGroup, false);
        SupplyHistoryViewHoder supplyHistoryVH = new SupplyHistoryViewHoder(v);
        return supplyHistoryVH;

    }


    @Override
    public void onBindViewHolder(@NonNull final SupplyHistoryViewHoder supplyHistoryViewHoder, final int i) {
        final SupplyHistory currentSupplyItem = supplyListAL.get(i);

        supplyHistoryViewHoder.itemImageIV.setImageResource(currentSupplyItem.getImage());
        supplyHistoryViewHoder.statusTV.setText(currentSupplyItem.getStatus());
        supplyHistoryViewHoder.itemNameTV.setText(currentSupplyItem.getImage());
        supplyHistoryViewHoder.unitPriceTV.setText(currentSupplyItem.getUnitPrice());
        supplyHistoryViewHoder.numberOfUnitsTV.setText(currentSupplyItem.getNumberOfUnits());
        supplyHistoryViewHoder.dateTV.setText(currentSupplyItem.getRequestCreatedDate().toString());
    }

    @Override
    public int getItemCount() {
        return supplyListAL.size();
    }
}
