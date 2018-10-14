package com.example.s528116.smartinventory;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    private EditText emailET;
    private EditText passwordET;

    private TextView continueTV;
    private TextView cancleTV;

    public static Activity signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);

        continueTV = findViewById(R.id.continueTV);
        cancleTV = findViewById(R.id.cancleTV);

        signup = this;



        continueTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = emailET.getText().toString();
                String passWord = passwordET.getText().toString();
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(Signup.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(passWord)){
                    Toast.makeText(Signup.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent i = new Intent(Signup.this, SecQuestion.class);
                i.putExtra("email", userName);
                i.putExtra("password", passWord);
                startActivity(i);
            }
        });

        cancleTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
