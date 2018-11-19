package com.example.s531378.smartinventoryadmin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SupplyListItems extends Activity {
    ListView list;
    String[] itemname ={
            "iphone 6s", "iphone 8", "iphone x", "iphone 7"

    };

    Integer[] imgid={
          R.drawable.iphone6s, R.drawable.iphone8, R.drawable.iphonex, R.drawable.image


    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_list_items);

        TextView canceltv = findViewById(R.id.cnclTV);
        canceltv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent init = new Intent(SupplyListItems.this, AdminView.class);
                startActivity(init);
            }
        });


        CustomListAdapter adapter = new CustomListAdapter(this, itemname, imgid);
        list = findViewById(R.id.list);
        list.setAdapter(adapter);

    }

}
