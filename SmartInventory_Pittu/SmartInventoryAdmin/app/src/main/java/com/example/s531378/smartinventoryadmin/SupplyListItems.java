package com.example.s531378.smartinventoryadmin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SupplyListItems extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_list_items);

        TextView cancelTv = findViewById(R.id.cnclTV) ;

        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SupplyListItems.this, AdminView.class);
                startActivity(i);
            }
        });
    }
}
