package com.example.s528116.smartinventory;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ItemList extends AppCompatActivity {

    ListView itemListLV;

    ArrayList<String> itemName = new ArrayList<>();
    ArrayList<String> itemPrice = new ArrayList<>();
    ArrayList<Integer> unitsNeeded = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);


        itemListLV = findViewById(R.id.itemListLV);
        itemName.add("iPhone 6 16GB AT&T");
        itemPrice.add("$115.50");
        unitsNeeded.add(20);

        itemListLV = findViewById(R.id.itemListLV);
        itemName.add("Samsung Galaxy 6 16GB AT&T");
        itemPrice.add("$107.50");
        unitsNeeded.add(50);

        itemListLV = findViewById(R.id.itemListLV);
        itemName.add("Canon PowerShot SX620 HS 20.2MP Digital Camera - Black");
        itemPrice.add("$160.50");
        unitsNeeded.add(5);

        itemListLV = findViewById(R.id.itemListLV);
        itemName.add("iPhone 6 16GB AT&T");
        itemPrice.add("$115.50");
        unitsNeeded.add(20);

        itemListLV = findViewById(R.id.itemListLV);
        itemName.add("iPhone 6 16GB AT&T");
        itemPrice.add("$115.50");
        unitsNeeded.add(20);

        ListAdapter itemList = new ArrayAdapter<String>(this, R.layout.item_row, R.id.itemNameTV, itemName){

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v= super.getView(position, convertView, parent);
                ImageView imageIV = v.findViewById(R.id.itemIV);
                TextView itemPriceTV = v.findViewById(R.id.itemPriceTV);
                TextView unitsNeededTV = v.findViewById(R.id.unitsTV);
                imageIV.setImageResource(R.drawable.iphone6);
                itemPriceTV.setText(itemPrice.get(position).toString());
                unitsNeededTV.setText("Units needed: "+unitsNeeded.get(position).toString());

                return v;
            }

        };
        itemListLV.setAdapter(itemList);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout:

                FirebaseAuth.getInstance().signOut();
                finish();
                return (true);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
