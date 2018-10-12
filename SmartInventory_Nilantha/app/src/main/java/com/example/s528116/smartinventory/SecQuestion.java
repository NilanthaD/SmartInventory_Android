package com.example.s528116.smartinventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;

public class SecQuestion extends AppCompatActivity /*implements AdapterView.OnItemSelectedListener */{

    private Spinner secq1SP;
    private Spinner secq2SP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_question);

        secq1SP = findViewById(R.id.secq1SP);
        secq2SP = findViewById(R.id.secq2SP);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.secq1, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.secq2, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        secq1SP.setAdapter(adapter);
        secq2SP.setAdapter(adapter1);
        secq1SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              String secQ1 = parent.getItemAtPosition(position).toString();
              Toast.makeText(SecQuestion.this, "Security Q1 : " + secQ1, Toast.LENGTH_SHORT).show();

          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {

          }
      });


        secq2SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String secQ2 = parent.getItemAtPosition(position).toString();
                Toast.makeText(SecQuestion.this, "Security Q2 : " + secQ2, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String secQ1 = parent.getItemAtPosition(position).toString();
//        Toast.makeText(this, "Security Q1 : " + secQ1, Toast.LENGTH_SHORT).show();
//        String secQ2 = parent.getItemAtPosition(position).toString();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
}
