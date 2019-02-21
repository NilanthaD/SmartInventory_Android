package com.example.s528116.smartinventory;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ServerTimestamp;
import com.google.firebase.storage.FirebaseStorage;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Item_Detail extends AppCompatActivity {

    private ImageView imageIV;
    private TextView itemNameTV;
    private TextView priceTV;
    private TextView quntityNeededTV;
    private TextView requiredByTV;
    private TextView detailsTV;
    private EditText supplyAmountET;
    private EditText messageET;
    private TextView cancleRequestTV;
    private Button submitRequestBTN;

    private String userEmail; // User Email
    private String docId; //Document Id in Firestore
    private String itemId;
    private int unitsRequired;
    private String createdDate;

    private String supplyAmount;
    private String message;
    private double unitPrice;
    private int supplyAmt;
    private double totalValue;

    private FirebaseFirestore db;
    private DocumentReference itemRef,userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item__detail);

        imageIV = findViewById(R.id.imageIV);
        itemNameTV = findViewById(R.id.itemNameTV);
        priceTV = findViewById(R.id.priceTV);
        quntityNeededTV = findViewById(R.id.quntityNeededTV);
        requiredByTV = findViewById(R.id.requiredByTV);
        detailsTV = findViewById(R.id.detailsTV);
        supplyAmountET = findViewById(R.id.supplyAmountET);
        messageET = findViewById(R.id.messageET);
        cancleRequestTV = findViewById(R.id.cancleRequestTV);
        submitRequestBTN = findViewById(R.id.submitRequestBTN);

        db = FirebaseFirestore.getInstance();

//      Get data from the Intent
        Intent i = getIntent();
        userEmail = i.getStringExtra("userEmail");

        itemId = i.getStringExtra("itemId");
        unitPrice = Double.parseDouble(i.getStringExtra("unitPrice"));
        unitsRequired = Integer.parseInt(i.getStringExtra("qntyRequired"));
        docId = i.getStringExtra("documentId");
        imageIV.setImageResource(R.drawable.iphone6);
        itemNameTV.setText(i.getStringExtra("itemName"));
        priceTV.setText("Buying price :$"+i.getStringExtra("unitPrice"));
        quntityNeededTV.setText("Quntity Needed : " + i.getStringExtra("qntyRequired"));
        requiredByTV.setText("Required By :" + i.getStringExtra("requiredBy"));
//        Get an instance of the items
        itemRef = db.collection("items").document(docId);
        itemRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                detailsTV.setText(documentSnapshot.get("itemDetails").toString());
            }
        });

//        if usr click "Submit Request", it will read the number of items and the message and save the data under user --> SupplyList
        submitRequestBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supplyAmount = supplyAmountET.getText().toString();
                message = messageET.getText().toString();
                supplyAmt = Integer.parseInt(supplyAmount);
                totalValue = unitPrice*supplyAmt*1.0;
                if(supplyAmt>0) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Item_Detail.this);
                    builder.setTitle("Conformation..").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final Map<String, Object> supplyRequest = new HashMap<>();
                            supplyRequest.put("itemDocId", docId);
                            supplyRequest.put("itemId", itemId);
                            supplyRequest.put("message", message);
                            supplyRequest.put("supplyAmount", supplyAmount);
                            supplyRequest.put("paymentStatus", "notSet");
                            supplyRequest.put("status", "pending");
                            supplyRequest.put("unitPrice", Double.toString(unitPrice));
                            supplyRequest.put("totalValue", Double.toString(totalValue));
                            supplyRequest.put("createdDate", new Timestamp(new Date()));
                            userRef = db.collection("users").document(userEmail);
                            userRef.collection("supplyList").document().set(supplyRequest);

                            int newQuntyRequired = unitsRequired - supplyAmt;


                            itemRef.update("unitRequired", Integer.toString(newQuntyRequired));  // Adjust the number of required units.

                            Intent intent = new Intent(Item_Detail.this, SupplyRequestSubmitted.class);
                            intent.putExtra("userEmail", userEmail);
                            startActivity(intent);
                        }
                    }).setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            finish();
                        }
                    });
                    AlertDialog alert =builder.create();
                    alert.show();



                }
                else {
                    Toast.makeText(Item_Detail.this, "Number of Items must be more than 0", Toast.LENGTH_LONG).show();
                }
            }
        });


        cancleRequestTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        Intent a = getIntent();
//        userEmail = a.getStringExtra("userEmail");
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout:

                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                Item_Detail.this.finish();
                break;

            case R.id.SupplyHistory:
                Intent supplyHistoryIntent = new Intent(this, SupplyHistoryRV.class);
                supplyHistoryIntent.putExtra("userEmail", userEmail);
                startActivity(supplyHistoryIntent);
                break;
            case R.id.back:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
