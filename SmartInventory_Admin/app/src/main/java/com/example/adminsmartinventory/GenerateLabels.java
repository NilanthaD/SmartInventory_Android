package com.example.adminsmartinventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class GenerateLabels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_labels);
    }

    public void generateFunction(View v)
    {
        TextView tv=(TextView)findViewById(R.id.label_num);
        String allchars = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) {
            int index = (int) (rnd.nextFloat() * allchars.length());
            salt.append(allchars.charAt(index));
        }
        String saltStr = salt.toString();
        tv.setText(saltStr);
    }
}
