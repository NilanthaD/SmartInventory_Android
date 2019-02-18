package com.example.s528116.smartinventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SupplyRequestSubmitted extends AppCompatActivity {

    TextView backTV;
    Intent intent = new Intent();
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_request_submitted);
        intent = getIntent();
        userEmail = intent.getStringExtra("userEmail");

        backTV = findViewById(R.id.backTV);
        backTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SupplyRequestSubmitted.this, ItemListRV.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("userEmail", userEmail);
                startActivity(i);
                SupplyRequestSubmitted.this.finish();
            }
        });
    }
}
