package com.example.adminsmartinventory;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    private EditText adminIdET;
    private EditText passwordET;
    private EditText fNameET;
    private EditText lNameET;
    private EditText conformPWET;
    private TextView continueBTN;


    public static Activity signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setContentView(R.layout.activity_signup);

        adminIdET = findViewById(R.id.adminIdET);
        passwordET = findViewById(R.id.passwordET);
        fNameET = findViewById(R.id.firstNameET);
        lNameET = findViewById(R.id.lastNameET);

        conformPWET = findViewById(R.id.conformPWET);
        continueBTN = findViewById(R.id.continueBTN);


        signup = this;



        continueBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = adminIdET.getText().toString();
                String passWord = passwordET.getText().toString();
                String fName = fNameET.getText().toString();
                String lName = lNameET.getText().toString();


                if(!passWord.equals(confirmPWET.getText().toString())){
                    setAlert("Password conformation failed. ");
                    return;
                }

                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(Signup.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                    setAlert("Email cannot be empty");
                    return;
                }
                if(TextUtils.isEmpty(passWord)){
//                    Toast.makeText(Signup.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                    setAlert("Password cannot be empty");
                    return;
                }
                if(passWord.length()<6){
//                    Toast.makeText(signup, "Password should be at least 6 charactors", Toast.LENGTH_SHORT).show();
                    setAlert("Password should be at least 6 characters");
                    return;
                }
                if(TextUtils.isEmpty(fName) || TextUtils.isEmpty(lName) || TextUtils.isEmpty(pNumber)){
                    setAlert("All the fields need to be filed.");
                    return;
                }



            }



    }





}
}