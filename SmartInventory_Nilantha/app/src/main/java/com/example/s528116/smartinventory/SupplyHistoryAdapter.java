package com.example.s528116.smartinventory;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        public LinearLayout supplyHistoryContainer;


        public SupplyHistoryViewHoder(@NonNull View supplyHistoryView) {
            super(supplyHistoryView);
            itemImageIV = supplyHistoryView.findViewById(R.id.itemImageIV);
            statusTV = supplyHistoryView.findViewById(R.id.statusTV);
            itemNameTV = supplyHistoryView.findViewById(R.id.itemNameTV);
            unitPriceTV = supplyHistoryView.findViewById(R.id.unitPriceTV);
            numberOfUnitsTV = supplyHistoryView.findViewById(R.id.numberOfUnitsTV);
            dateTV = supplyHistoryView.findViewById(R.id.dateTV);
            supplyHistoryContainer = supplyHistoryView.findViewById(R.id.supplyHistoryContainer);

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
//        Date createdDate = new Date();
//        createdDate = currentSupplyItem.getRequestCreatedDate();
//        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
//        String dateCreated = sdf.format(createdDate);
        final String dateCreated = FormatDate.getDate(currentSupplyItem.getRequestCreatedDate());


        supplyHistoryViewHoder.itemImageIV.setImageResource(currentSupplyItem.getImage());
        supplyHistoryViewHoder.statusTV.setText("Status :" + currentSupplyItem.getStatus());
        supplyHistoryViewHoder.itemNameTV.setText("Item :" + currentSupplyItem.getItemName());
        supplyHistoryViewHoder.unitPriceTV.setText("Unit Price :$" + currentSupplyItem.getUnitPrice());
        supplyHistoryViewHoder.numberOfUnitsTV.setText("Number of Units : " +currentSupplyItem.getNumberOfUnits());

        supplyHistoryViewHoder.dateTV.setText("Created :" + dateCreated);


        supplyHistoryViewHoder.supplyHistoryContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent supplyItemDetailIntent = new Intent(context, SupplyItemDetail.class);
                supplyItemDetailIntent.putExtra("userEmail", currentSupplyItem.getUserEmail() );
                supplyItemDetailIntent.putExtra("itemId", currentSupplyItem.getItemName());
                supplyItemDetailIntent.putExtra("status", currentSupplyItem.getStatus());
                supplyItemDetailIntent.putExtra("unitPrice", currentSupplyItem.getUnitPrice());
                supplyItemDetailIntent.putExtra("numberOfUnits", currentSupplyItem.getNumberOfUnits());
                supplyItemDetailIntent.putExtra("message", currentSupplyItem.getMessage());
                supplyItemDetailIntent.putExtra("totalValue", currentSupplyItem.getTotalValue());
                supplyItemDetailIntent.putExtra("paymentStatus", currentSupplyItem.getPaymentStatus());
                supplyItemDetailIntent.putExtra("itemDocId", currentSupplyItem.getItemDocId());
                supplyItemDetailIntent.putExtra("date", dateCreated);
                context.startActivity(supplyItemDetailIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return supplyListAL.size();
    }
}
