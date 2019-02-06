package com.example.hp.loginpage.PDF;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;

import com.example.hp.loginpage.HelperClass.PdfAdapter;
import com.example.hp.loginpage.ModelClass.Upload;
import com.example.hp.loginpage.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PdfShow extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_show);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);

        //retrieving upload data from firebase database
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
//                    Upload upload = postSnapshot.getValue(Upload.class);
//                    update.add(upload);
//                }
//
//                String[] uploads = new String[uploadList.size()];
//
//                for(int i = 0; i < uploads.length; i++) {
//                    uploads[i] = uploadList.get(i).getName();
//                }
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(PdfShow.this,R.layout.pdf_view,R.id.pdfID, uploads);
//                recyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                // call for individual items at the database ref...
                Upload fileName = dataSnapshot.getValue(Upload.class);// refer the file name
                Upload url = dataSnapshot.getValue(Upload.class);// return url for file name
                ((PdfAdapter)recyclerView.getAdapter()).update(fileName,url);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        recyclerView = findViewById(R.id.pdf_recycleView);

        //adapter to populate recycleview with items
        recyclerView.setLayoutManager(new LinearLayoutManager(PdfShow.this));
        PdfAdapter pdfAdapter = new PdfAdapter(recyclerView, PdfShow.this, new ArrayList<Upload>(), new ArrayList<Upload>());
        recyclerView.setAdapter(pdfAdapter);
    }
}
