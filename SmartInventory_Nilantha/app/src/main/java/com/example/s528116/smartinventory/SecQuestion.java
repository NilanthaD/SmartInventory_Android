package com.example.s528116.smartinventory;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Array;

public class SecQuestion extends AppCompatActivity /*implements AdapterView.OnItemSelectedListener */{

    private Spinner secq1SP;
    private Spinner secq2SP;
    private TextView cancleTV;
    private Button submitBTN;

    private FirebaseAuth mAuth;
    Intent userDetails = new Intent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_question);
        // get the intent data passed by the signup page
        userDetails = getIntent();

        secq1SP = findViewById(R.id.secq1SP);
        secq2SP = findViewById(R.id.secq2SP);
        cancleTV = findViewById(R.id.cancleTV);
        submitBTN = findViewById(R.id.submitBTN);
        //Create an instance of firebase users
        mAuth = FirebaseAuth.getInstance();

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.secq1, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.secq2, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        secq1SP.setAdapter(adapter);
        secq2SP.setAdapter(adapter1);
        secq1SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              String secQ1 = parent.getItemAtPosition(position).toString();
//              Toast.makeText(SecQuestion.this, "Security Q1 : " + secQ1, Toast.LENGTH_SHORT).show();

          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {

          }
        });


        secq2SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String secQ2 = parent.getItemAtPosition(position).toString();
//                Toast.makeText(SecQuestion.this, "Security Q2 : " + secQ2, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//      Press cancel button will finish the activity.
        cancleTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Signup.signup.finish();
                finish();
            }
        });

//    Click on submit button
        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.createUserWithEmailAndPassword(userDetails.getStringExtra("email"), userDetails.getStringExtra("password"))
                        .addOnCompleteListener(SecQuestion.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    user.sendEmailVerification()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
//                                            Toast.makeText(SecQuestion.this, "Veryfication email sent", Toast.LENGTH_SHORT).show();
                                            Signup.signup.finish();
                                            AlertDialog.Builder builder = new AlertDialog.Builder(SecQuestion.this);
                                            builder.setMessage("Veryfication email is sent to your email. Please click on the link to verify your email").setCancelable(false)
                                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            finish();
                                                        }
                                                    });
//                                            finish();
                                            AlertDialog alert = builder.create();
                                            alert.setTitle("Email verification sent");
                                            alert.show();
                                        }
                                    });

                                }
                                else {
//                                    Toast.makeText(SecQuestion.this, "Sorry..Couldn't create an account" + task.getException(), Toast.LENGTH_SHORT).show();
                                    AlertDialog.Builder builder = new AlertDialog.Builder(SecQuestion.this);
                                    builder.setMessage(task.getException().toString()).setCancelable(false)
                                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.cancel();
                                                }
                                            });
                                    AlertDialog alert = builder.create();
                                    alert.show();
                                }
                            }
                        });
//                finish();

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().signOut();
    }
}
