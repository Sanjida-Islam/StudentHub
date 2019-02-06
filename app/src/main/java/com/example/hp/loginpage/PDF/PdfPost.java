package com.example.hp.loginpage.PDF;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.loginpage.ModelClass.Upload;
import com.example.hp.loginpage.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PdfPost extends AppCompatActivity implements View.OnClickListener {

    private AlertDialog.Builder alertDialogBuilder;

    //this is the pic pdf code used in file chooser
    final static int PICK_PDF_CODE = 2342;

    //these are the views
    TextView textViewStatus;
    EditText editTextFilename;
    ProgressBar progressBar;
    ProgressDialog progressDialog;

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
//

    //the firebase objects for storage and database
    StorageReference mStorageReference;
    DatabaseReference mDatabaseReference;

    public PdfPost() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_post);


//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mStorageReference = FirebaseStorage.getInstance().getReference();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);

        //getting the views
        textViewStatus = (TextView) findViewById(R.id.textViewStatus);
        editTextFilename = (EditText) findViewById(R.id.editTextFileName);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        //attaching listeners to views
        findViewById(R.id.buttonUploadFile).setOnClickListener(this);
        findViewById(R.id.textViewUploads).setOnClickListener(this);

        //setupFirebaseAuth();
    }


    public void getPDF(){

        if(isEmpty()) return;
        //for greater than lolipop versions we need the permissions asked on runtime
        //so if the permission is not available user will go to the screen to allow storage permission
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){


            selectPdf();
        }else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
        }
    }

    public boolean isEmpty(){
        if(TextUtils.isEmpty(editTextFilename.getText().toString())){
            editTextFilename.setError("REQUIRED");
            return true;
        }
        return false;
    }

    // for example
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            selectPdf();
        }else {
            Toast.makeText(this, "Please provide permission..", Toast.LENGTH_SHORT).show();
        }
    }
    // for example

    ///creating an intent for file chooser
    private void selectPdf(){
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);  // to fatch file
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PDF_CODE);
        startActivityForResult(intent,PICK_PDF_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       // super.onActivityResult(requestCode, resultCode, data);

        //when the user choses the file
        if(requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null && data.getData() != null){
            //is a file is selected
            if (data.getData() != null){
                //uploading the file
                uploadFile(data.getData());
            }else {
                Toast.makeText(this, "Please select a file", Toast.LENGTH_SHORT).show();

            }
        }
    }

    //this method for uploading file
    private void uploadFile(final Uri data) {
       // progressBar.setVisibility(View.VISIBLE);

        // for example
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading file...");
        progressDialog.setProgress(0);
        progressDialog.show();
        // for example
        final StorageReference sRef = mStorageReference.child(Constants.STORAGE_PATH_UPLOADS + System.currentTimeMillis() + ".pdf");


        sRef.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //progressBar.setVisibility(View.GONE);
                textViewStatus.setText("File Uploaded Successfully");

                Upload upload = new Upload(editTextFilename.getText().toString(), taskSnapshot.getStorage().getDownloadUrl().toString());
                mDatabaseReference.child(mDatabaseReference.push().getKey()).setValue(upload).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(PdfPost.this, "File Uploaded Successfully", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(PdfPost.this, "File not Uploaded ", Toast.LENGTH_LONG).show();
                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                //Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(PdfPost.this, "File not Uploaded Successfully", Toast.LENGTH_LONG).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                int progress =(int) (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                //textViewStatus.setText((int) progress + "% Uploading...");
                progressDialog.setProgress(progress);


            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.buttonUploadFile:
                getPDF();
                break;
            case R.id.textViewUploads:
                startActivity(new Intent(this, PdfShow.class));
                break;
        }
    }
}
