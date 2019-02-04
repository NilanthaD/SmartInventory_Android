package com.example.adminsmartinventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        public void SignupFn(View v)
        {
            Intent in = new Intent(this,MainActivity.class);
            startActivity(in);
        }
    }
}
