package com.example.adminsmartinventory;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


    }

    private EditText adminIdET;
    private EditText passwordET;
    private EditText fNameET;
    private EditText lNameET;

    private EditText confirmPWET;


    private TextView continueBTN;


    public static Activity signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        adminIdET = findViewById(R.id.adminIdET);
        passwordET = findViewById(R.id.passwordET);
        fNameET = findViewById(R.id.firstNameET);
        lNameET = findViewById(R.id.lastNameET);

        confirmPWET = findViewById(R.id.confirmPasswordET);
        continueBTN = findViewById(R.id.continueBTN);


        signup = this;


    }
}