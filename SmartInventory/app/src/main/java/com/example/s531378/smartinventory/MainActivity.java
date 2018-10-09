package com.example.s531378.smartinventory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signUpFn(View v){

        Intent init = new Intent(this,SignUpActivity.class);
        startActivity(init);
    }
}
