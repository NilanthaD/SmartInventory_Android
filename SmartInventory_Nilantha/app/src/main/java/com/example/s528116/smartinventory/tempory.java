package com.example.s528116.smartinventory;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class tempory extends AppCompatActivity {

    private ImageView imageIV;
    private Button getImageBTN;
    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempory);

        getImageBTN = findViewById(R.id.getImageBTN);
        imageIV = findViewById(R.id.imageIV);

        mStorageRef = FirebaseStorage.getInstance().getReference();


        getImageBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mStorageRef.child("images/camera.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        imageIV.setImageURI(uri);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(tempory.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
                try {
                    File localFile = File.createTempFile("camera", "jpg");
                }
                 catch (IOException e) {
                    e.printStackTrace();
                }
//                StorageReference riversRef = mStorageRef.child("images/camera.jpg");
//                riversRef.getFile(localFile)
//                mStorageRef.getFile("images/camera.jpg");
//                riversRef.getFile(localF)

//                GlideApp.with(this).load(mStorageRef).into(imageIV);
            }
        });
    }
}
