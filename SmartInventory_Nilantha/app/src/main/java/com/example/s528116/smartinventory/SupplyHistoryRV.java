package com.example.s528116.smartinventory;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class SupplyHistoryRV extends AppCompatActivity {

    private RecyclerView supplyHistoryRV;
    private RecyclerView.Adapter supplyHistoryAdapter;
    private RecyclerView.LayoutManager supplyHistryLayoutManager;

    private Intent supplyHistoryIntent = new Intent();
    private String userEmail;
    private FirebaseFirestore db;
//    private FirebaseStorage storage;
//    private StorageReference pathReferance;
    CollectionReference supplyItemsCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_history_rv);
        db = FirebaseFirestore.getInstance();
        supplyHistoryIntent = getIntent();
        userEmail = supplyHistoryIntent.getStringExtra("userEmail");
        supplyItemsCollection = db.collection("users").document(userEmail).collection("supplyList");

        final ArrayList<SupplyHistory> supplyListAL = new ArrayList<>();
        supplyItemsCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot doc: task.getResult()){
                        supplyListAL.add(new SupplyHistory(R.drawable.iphone6,doc.getString("status"), doc.getString("itemId"), doc.getString("totalValue"),
                                doc.getString("supplyAmount"), doc.getDate("createdDate")));
                    }
                    supplyHistoryRV = findViewById(R.id.supplyHistoryRV);
                    supplyHistoryRV.setHasFixedSize(true);
                    supplyHistryLayoutManager = new LinearLayoutManager(SupplyHistoryRV.this);
                    supplyHistoryAdapter = new SupplyHistoryAdapter(supplyListAL, SupplyHistoryRV.this);
                    supplyHistoryRV.setLayoutManager(supplyHistryLayoutManager);
                    supplyHistoryRV.setAdapter(supplyHistoryAdapter);
                }
            }
        });
    }
}
