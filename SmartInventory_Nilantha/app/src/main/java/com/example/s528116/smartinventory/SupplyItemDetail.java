package com.example.s528116.smartinventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SupplyItemDetail extends AppCompatActivity {

    private ImageView supplyItemImageIV;
    private TextView statusTV;
    private TextView itemIDTV;
    private TextView unitPriceTV;
    private TextView numberOfUnitsTV;
    private TextView createdDateTV;
    private TextView totalValueTV;

    Intent supplyItemIntent = new Intent();
    private  String userEmail;

//    private FirebaseFirestore mDb;
//    CollectionReference supplyItemCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_item_detail);

        supplyItemImageIV = findViewById(R.id.supplyItemImageIV);
        statusTV = findViewById(R.id.statusTV);
        itemIDTV = findViewById(R.id.itemIDTV);
        unitPriceTV = findViewById(R.id.unitPriceTV);
        numberOfUnitsTV = findViewById(R.id.numberOfUnitsTV);
        totalValueTV = findViewById(R.id.totalValueTV);
        createdDateTV = findViewById(R.id.createdDateTV);

        supplyItemIntent = getIntent();
        userEmail = supplyItemIntent.getStringExtra("userEmail");

//        mDb = FirebaseFirestore.getInstance();
//        supplyItemCollection = mDb.collection("users").document(userEmail).collection("supplyList");

        supplyItemImageIV.setImageResource(R.drawable.iphone6);
        itemIDTV.setText(supplyItemIntent.getStringExtra("itemId"));
        statusTV.setText("Status :"+supplyItemIntent.getStringExtra("status"));
        createdDateTV.setText("Created :" + supplyItemIntent.getStringExtra("date"));
        unitPriceTV.setText("Unit Price :$" + supplyItemIntent.getStringExtra("unitPrice"));
        numberOfUnitsTV.setText("Number of Units :"+ supplyItemIntent.getStringExtra("numberOfUnits"));
        totalValueTV.setText("Total Value :$" + supplyItemIntent.getStringExtra("totalValue"));




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
            case R.id.back:
                finish();
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
