package com.example.s531378.smartinventoryadmin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AcceptNewUsers extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_new_users);

        TextView cancelTv = findViewById(R.id.cnclTV) ;

        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AcceptNewUsers.this, AdminView.class);
                startActivity(i);
            }
        });
    }
}
