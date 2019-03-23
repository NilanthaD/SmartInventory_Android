package com.example.adminsmartinventory;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddItems extends AppCompatActivity {

    TextView cancelTV;
    private TextView imageNameTV;
    private static final int PICK_IMG_REQUEST = 1;
    private Button uploadeImageBTN;
    private Uri imageURI;

    private String imageName = "imageName";
    private FirebaseFirestore db;
    private StorageReference storage;
    private CollectionReference itemCollection;
    private DocumentReference itemDoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);


        cancelTV = findViewById(R.id.CancelTV);
        uploadeImageBTN = findViewById(R.id.uploadImageBTN);
        imageNameTV = findViewById(R.id.imageNameTV);

        storage = FirebaseStorage.getInstance().getReference("tempImages");
        db = FirebaseFirestore.getInstance();
        itemCollection = db.collection("items");


        uploadeImageBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        cancelTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(AddItems.this, AdminView.class);
                startActivity(in);
            }
        });


    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMG_REQUEST && resultCode == RESULT_OK
        && data !=null && data.getData()!=null){
            imageURI = data.getData();
            Toast.makeText(this, "image URI : "+imageURI, Toast.LENGTH_SHORT).show();
//            AlertDialog.Builder builder = new AlertDialog.Builder(this).
//            setTitle("Image name").
////            final EditText fileNameET = new EditText(this);
////            fileNameET.setInputType(InputType.TYPE_CLASS_TEXT);
////            builder.setView(fileNameET);
//
//            setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
////                    imageNameTV.setText(fileNameET.getText().toString());
//                    dialog.cancel();
//                }
//            }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.cancel();
//                }
//            });
            uploadImage();

        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void uploadImage() {
        if (imageURI != null) {
            StorageReference fileRef = storage.child(System.currentTimeMillis() + "." + getFileExtension(imageURI));
            fileRef.putFile(imageURI);
            itemDoc = itemCollection.document("Fdh18JrpFHW4RRNm5wzW");
            Toast.makeText(this, "itemDoc "+ itemDoc, Toast.LENGTH_SHORT).show();
            itemDoc.update("imageURI", imageURI.toString());
       //     itemCollection.document("Fdh18JrpFHW4RRNm5wzW").update("imageURI", imageURI);
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    Toast.makeText(AddItems.this, "upload success", Toast.LENGTH_SHORT).show();
//
//                }
//            }). addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(AddItems.this, "Upload failed :"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        else {
//            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
//        }
        }
    }
}
