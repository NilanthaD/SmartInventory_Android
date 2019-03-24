package com.example.s528116.smartinventory;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SupplyItemDetail extends AppCompatActivity {

    private ImageView supplyItemImageIV;
    private TextView statusTV, itemIDTV, unitPriceTV, numberOfUnitsTV, createdDateTV, totalValueTV;
    private EditText newAmountET;
    private Button submitNewAmountBTN, deleteRequestBTN, shippingLableBTN, contactVendorBTN;
    private LinearLayout pendingLL, newRequestLL, shippedLL;
    //items for send change request
    private EditText messageET, changeSupplyAmountET;
    private Button changeRequestBTN, cancelSupplyRequestBTN, shippedBTN, shippingLabelBTN;

    private Intent supplyItemIntent = new Intent();
    private String userEmail, supplyDocId, itemDocId, itemId, message, paymentStatus, status, dateCreated;
    private long supplyAmount, totalValue, unitPrice, unitRequired, newSupplyAmount, newUnitRequired;
    private Date createdDate;

    private FirebaseFirestore mDb;
    private DocumentReference supplyItemDocRef, itemsDocRef;
    private CollectionReference changeSupplyRequestref;
    private FirebaseStorage storage;



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
        shippedBTN = findViewById(R.id.shippedBTN);
        pendingLL = findViewById(R.id.pendingLL);
        newRequestLL = findViewById(R.id.newRequestLL);
        shippedLL = findViewById(R.id.shippedLL);
        contactVendorBTN = findViewById(R.id.contactVendorBTN);
        shippingLableBTN = findViewById(R.id.shippingLabelBTN);

        supplyItemIntent = getIntent();
        userEmail = supplyItemIntent.getStringExtra("userEmail");
        status = supplyItemIntent.getStringExtra("status").trim();
        supplyDocId = supplyItemIntent.getStringExtra("supplyDocId");

        storage = FirebaseStorage.getInstance();

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

        if (status.equals("pending")) {
            newRequestLL.setVisibility(View.GONE);
            shippedLL.setVisibility(View.GONE);
            newAmountET.setEnabled(false);
            submitNewAmountBTN.setEnabled(false);
            deleteRequestBTN.setEnabled(false);
        }
        if (status.equals("approved")) {
            pendingLL.setVisibility(View.GONE);
            shippedLL.setVisibility(View.GONE);
        }
        if(status.equals("Shipped")){
            pendingLL.setVisibility(View.GONE);
            newRequestLL.setVisibility(View.GONE);
        }

        itemsDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    unitRequired = document.getLong("unitRequired");
                }
            }
        });

        submitNewAmountBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newSupplyAmount = Long.parseLong(newAmountET.getText().toString());
                if (newSupplyAmount > 0 && newSupplyAmount < unitRequired) {
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
                    Toast.makeText(SupplyItemDetail.this, "new amount must be greater than 0 and less than units required.", Toast.LENGTH_LONG).show();
                }
            }
        });


//      If the request is pending and user click the delete request buttion, it will delete the record from db
        deleteRequestBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SupplyItemDetail.this);
                builder.setMessage("Are you sure you want to delete this Item Supply Request").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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
//      if the status is approved and user want to make a change to the existing request.
        changeRequestBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newSupplyAmount = Integer.parseInt(changeSupplyAmountET.getText().toString());
                message = messageET.getText().toString();
                if(newSupplyAmount>0 && newSupplyAmount<unitRequired){

                    final Map<String, Object> changeRequest = new HashMap<>();
                    changeRequest.put("changeMessage", message);
                    changeRequest.put("newAmount", newSupplyAmount);
                    changeRequest.put("status", "pending");
                    changeRequest.put("requestDate", new Timestamp(new Date()));
                    supplyItemDocRef.collection("ChangeRequest").document().set(changeRequest);
                    Toast.makeText(SupplyItemDetail.this, "Sent Request", Toast.LENGTH_SHORT).show();
                }
            }
        });

        shippedBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supplyItemDocRef.update("status", "Shipped");
                statusTV.setText("Status :Shipped.");
                newRequestLL.setVisibility(View.GONE);
            }
        });

        contactVendorBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contactInt = new Intent(SupplyItemDetail.this, ContactUs.class);
                contactInt.putExtra("userName", userEmail);
                startActivity(contactInt);
            }
        });

        shippingLableBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StorageReference storRef = storage.getReference();
                StorageReference pathRef = storRef.child("shippingLabels/shippingLabel.pdf");

                try {
                    File localFile = File.createTempFile("shippingLBL", "pdf");
                    pathRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(SupplyItemDetail.this, "File Downloaded", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SupplyItemDetail.this, "Error Occured", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

//    public long downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {
//
//
//        DownloadManager downloadmanager = (DownloadManager) context.
//                getSystemService(Context.DOWNLOAD_SERVICE);
//        Uri uri = Uri.parse(url);
//        DownloadManager.Request request = new DownloadManager.Request(uri);
//
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);
//
//        return downloadmanager.enqueue(request);
//    }
}
