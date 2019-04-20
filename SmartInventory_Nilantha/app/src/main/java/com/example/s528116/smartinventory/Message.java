package com.example.s528116.smartinventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class Message extends AppCompatActivity {

    private TextView titleTV, senderTV, sendDateTV;

   // private Button replyBTN;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        titleTV = findViewById(R.id.itemNameTV);
        sendDateTV = findViewById(R.id.sendDateTV);
        senderTV = findViewById(R.id.senderTV);
        
        db = FirebaseFirestore.getInstance();

    }
}
