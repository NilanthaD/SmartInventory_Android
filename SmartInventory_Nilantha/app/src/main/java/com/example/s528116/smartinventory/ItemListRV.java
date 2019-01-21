package com.example.s528116.smartinventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ItemListRV extends AppCompatActivity {
    private RecyclerView itemsRV;
    private RecyclerView.Adapter itemsAdapter;
    private RecyclerView.LayoutManager itemsLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list_rv);

        ArrayList<ItemContainer> itemListArray = new ArrayList<>();
        itemListArray.add(new ItemContainer(R.drawable.iphone6, "iphone123234", "iPhone6", "$100.00", "25"));
        itemListArray.add(new ItemContainer(R.drawable.iphone6, "343234234", "camera", "25", "20"));
        itemListArray.add(new ItemContainer(R.drawable.iphone6, "343234234", "camera", "25", "20"));
        itemListArray.add(new ItemContainer(R.drawable.iphone6, "343234234", "camera", "25", "20"));

        itemsRV = findViewById(R.id.itemsRV);
        itemsRV.setHasFixedSize(true);
        itemsLayoutManager = new LinearLayoutManager(this);
        itemsAdapter = new ItemsAdapter(itemListArray);
        itemsRV.setLayoutManager(itemsLayoutManager);
        itemsRV.setAdapter(itemsAdapter);
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
