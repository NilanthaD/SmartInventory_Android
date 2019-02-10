package com.example.s528116.smartinventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class Item_Detail extends AppCompatActivity {

    private ImageView imageIV;
    private TextView itemNameTV;
    private TextView priceTV;
    private TextView quntityNeededTV;
    private TextView requiredByTV;
    private TextView detailsTV;
    private EditText supplyAmountET;
    private EditText messageET;
    private TextView cancleRequestTV;
    private Button submitRequestBTN;

    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item__detail);

        imageIV = findViewById(R.id.imageIV);
        itemNameTV = findViewById(R.id.itemNameTV);
        priceTV = findViewById(R.id.priceTV);
        quntityNeededTV = findViewById(R.id.quntityNeededTV);
        requiredByTV = findViewById(R.id.requiredByTV);
        detailsTV = findViewById(R.id.detailsTV);
        supplyAmountET = findViewById(R.id.supplyAmountET);
        messageET = findViewById(R.id.messageET);
        cancleRequestTV = findViewById(R.id.cancleRequestTV);
        submitRequestBTN = findViewById(R.id.submitRequestBTN);



        Intent i = getIntent();
        imageIV.setImageResource(R.drawable.iphone6);
        itemNameTV.setText(i.getStringExtra("itemName"));
        priceTV.setText("Buying price :"+i.getStringExtra("unitPrice"));
        quntityNeededTV.setText("Quntity Needed : " + i.getStringExtra("qntyRequired"));
        requiredByTV.setText("Required By :" + i.getStringExtra("requiredBy"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout:

                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(Item_Detail.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                Item_Detail.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
