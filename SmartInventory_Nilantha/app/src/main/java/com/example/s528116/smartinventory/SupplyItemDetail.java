package com.example.s528116.smartinventory;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class SupplyItemDetail extends AppCompatActivity {

    private ImageView supplyItemImageIV;
    private TextView statusTV, itemIDTV, unitPriceTV, numberOfUnitsTV, createdDateTV, totalValueTV;
    private EditText newAmountET;
    private Button submitNewAmountBTN, deleteRequestBTN, shippingLableBTN;
    private LinearLayout pendingLL, newRequestLL;
    //items for send change request
    private EditText messageET, changeSupplyAmountET;
    private Button changeRequestBTN, cancelSupplyRequestBTN;

    private Intent supplyItemIntent = new Intent();
    private String userEmail, supplyDocId, itemDocId, itemId, message, paymentStatus, status, dateCreated;
    private long supplyAmount, totalValue, unitPrice, unitRequired, newSupplyAmount, newUnitRequired;
    private Date createdDate;

    private FirebaseFirestore mDb;
    private DocumentReference supplyItemDocRef, itemsDocRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_item_detail);

        supplyItemImageIV = findViewById(R.id.supplyItemImageIV);
        statusTV = findViewById(R.id.statusTV);
        itemIDTV = findViewById(R.id.itemIDTV);
        unitPriceTV = findViewById(R.id.unitPriceTV);
        numberOfUnitsTV = findViewById(R.id.numberOfUnitsTV);
        totalValueTV = findViewById(R.id.totalValueTV);
        createdDateTV = findViewById(R.id.createdDateTV);
        newAmountET = findViewById(R.id.newAmountET);
        submitNewAmountBTN = findViewById(R.id.submitNewAmountBTN);
        deleteRequestBTN = findViewById(R.id.deleteRequestBTN);
        changeRequestBTN = findViewById(R.id.changeRequestBTN);
        messageET = findViewById(R.id.messageET);
        changeSupplyAmountET = findViewById(R.id.changeRequestAmountET);
        cancelSupplyRequestBTN = findViewById(R.id.cancleSupplyRequestBTN);
        shippingLableBTN = findViewById(R.id.shippingLabelBTN);
        pendingLL = findViewById(R.id.pendingLL);
        newRequestLL= findViewById(R.id.newRequestLL);

        supplyItemIntent = getIntent();
        userEmail = supplyItemIntent.getStringExtra("userEmail");
        status = supplyItemIntent.getStringExtra("status").trim();
        supplyDocId = supplyItemIntent.getStringExtra("supplyDocId");

        mDb = FirebaseFirestore.getInstance();
        supplyItemDocRef = mDb.collection("users").document(userEmail).collection("supplyList").document(supplyDocId);
        itemsDocRef = mDb.collection("items").document(supplyItemIntent.getStringExtra("itemDocId"));

        supplyItemDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot supplyDoc = task.getResult();

                    createdDate = supplyDoc.getDate("createdDate");
                    itemDocId = supplyDoc.getString("itemDocId");
                    itemId = supplyDoc.getString("itemId");
                    message = supplyDoc.getString("message");
                    paymentStatus = supplyDoc.getString("paymentStatus");
                    status = supplyDoc.getString("status");
                    supplyAmount = supplyDoc.getLong("supplyAmount");
                    totalValue = supplyDoc.getLong("totalValue");
                    unitPrice = supplyDoc.getLong("unitPrice");

                    dateCreated = FormatDate.getDate(createdDate);
                    supplyItemImageIV.setImageResource(R.drawable.iphone6);
                    itemIDTV.setText(itemId);
                    statusTV.setText("Status :" + status);
                    createdDateTV.setText("Created :" + dateCreated);
                    unitPriceTV.setText("Unit Price :$" + unitPrice);
                    numberOfUnitsTV.setText("Number of Units :" + supplyAmount);
                    totalValueTV.setText("Total Value :$" + totalValue);
                } else
                    Toast.makeText(SupplyItemDetail.this, "Task Failed", Toast.LENGTH_SHORT).show();
            }

        });

        if (!status.equals("pending")) {
            pendingLL.setVisibility(View.GONE);
            newAmountET.setEnabled(false);
            submitNewAmountBTN.setEnabled(false);
            deleteRequestBTN.setEnabled(false);
        } else if(!status.equals("approved"))
            {
            newRequestLL.setVisibility(View.GONE);
            changeRequestBTN.setEnabled(false);
            messageET.setEnabled(false);
            cancelSupplyRequestBTN.setEnabled(false);
            shippingLableBTN.setEnabled(false);
        }

        submitNewAmountBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newSupplyAmount = Long.parseLong(newAmountET.getText().toString());

                itemsDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            unitRequired = document.getLong("unitRequired");
                            newUnitRequired = unitRequired - newSupplyAmount + supplyAmount;
                            totalValue = unitPrice * newSupplyAmount;

                            AlertDialog.Builder builder = new AlertDialog.Builder(SupplyItemDetail.this);
                            builder.setMessage("Are you sure you want to change the supply ammount to be " + newSupplyAmount)
                                    .setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    supplyItemDocRef.update("supplyAmount", newSupplyAmount);
                                    supplyItemDocRef.update("totalValue", totalValue);
                                    itemsDocRef.update("unitRequired", newUnitRequired);
                                    numberOfUnitsTV.setText("Number of Units :" + newSupplyAmount);
                                    totalValueTV.setText("Total Value :$" + totalValue);
                                    newAmountET.setText("");
                                }
                            }).setNegativeButton("canclel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();
                        } else {
                            Toast.makeText(SupplyItemDetail.this, "Could not update the number of Required items", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

        deleteRequestBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SupplyItemDetail.this);
                builder.setMessage("Are you sure you want to delete this Item Supply Request").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        itemsDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    unitRequired = document.getLong("unitRequired");

                                    supplyItemDocRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            itemsDocRef.update("unitRequired", unitRequired + supplyAmount);
                                            Toast.makeText(SupplyItemDetail.this, "Item Deleted", Toast.LENGTH_SHORT).show();
                                            Intent supplyHistoryIntent = new Intent(SupplyItemDetail.this, SupplyHistoryRV.class);
                                            supplyHistoryIntent.putExtra("userEmail", userEmail);
                                            supplyHistoryIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(supplyHistoryIntent);
                                            SupplyItemDetail.this.finish();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(SupplyItemDetail.this, "Error deleting Document" + e, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        });

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SupplyItemDetail.this.finish();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                SupplyItemDetail.this.finish();
                break;
            case R.id.about:
                Intent aboutIntent = new Intent(this, Aboutus.class);
                aboutIntent.putExtra("userName", userEmail);
                startActivity(aboutIntent);
                break;
            case R.id.SupplyHistory:
            case R.id.back:
                Intent supplyHistoryIntent = new Intent(this, SupplyHistoryRV.class);
                supplyHistoryIntent.putExtra("userEmail", userEmail);
                supplyHistoryIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(supplyHistoryIntent);
                SupplyItemDetail.this.finish();
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
