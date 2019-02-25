package com.example.s528116.smartinventory;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ItemListRV extends AppCompatActivity {
    private RecyclerView itemsRV;
    private RecyclerView.Adapter itemsAdapter;
    private RecyclerView.LayoutManager itemsLayoutManager;

    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private StorageReference pathReferance;
    CollectionReference itemCollection;

    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list_rv);

        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        pathReferance = storage.getReference().child("images/iphone6.jpg");
        itemCollection = db.collection("items");

        Intent i = getIntent();
        userEmail = i.getStringExtra("userId");

        final ArrayList<ItemContainer> itemListArray = new ArrayList<>();
        itemCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot doc: task.getResult()){
                        itemListArray.add(new ItemContainer(userEmail, doc.getId(),R.drawable.iphone6, doc.getString("itemId"), doc.getString("itemName"), doc.getString("untPrice"), doc.getString("unitRequired"), doc.getTimestamp("requiredBefore").toDate()));
                    }

                    itemsRV = findViewById(R.id.itemsRV);
                    itemsRV.setHasFixedSize(true);
                    itemsLayoutManager = new LinearLayoutManager(ItemListRV.this);
                    itemsAdapter = new ItemsAdapter(itemListArray, ItemListRV.this);
                    itemsRV.setLayoutManager(itemsLayoutManager);
                    itemsRV.setAdapter(itemsAdapter);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout:

                FirebaseAuth.getInstance().signOut();
                finish();
                return (true);

            case R.id.SupplyHistory:
                Intent supplyHistoryIntent = new Intent(this, SupplyHistoryRV.class);
                supplyHistoryIntent.putExtra("userEmail", userEmail);
                startActivity(supplyHistoryIntent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
