package com.example.hp.loginpage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentProfile extends AppCompatActivity {

    private ImageView profile_pic;
    private TextView profile_name, profile_id, profile_batch, profile_phone, profile_email, profile_bloodgroup;

    private static final int REQUEST_CALL = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FloatingActionButton floatingActionButton = findViewById(R.id.call_student);

        profile_pic = findViewById(R.id.st_profile_pic);
        profile_name = findViewById(R.id.st_profile_name);
        profile_id = findViewById(R.id.st_profile_id);
        profile_batch = findViewById(R.id.st_profile_batch);
        profile_phone = findViewById(R.id.st_profile_phone);
        profile_email = findViewById(R.id.st_profile_email);
        profile_bloodgroup = findViewById(R.id.st_profile_bloodgroup);




        String studentName = getIntent().getStringExtra("studentName");
        String studentId = getIntent().getStringExtra("studentID");
        String studentBatch = getIntent().getStringExtra("studentBatch");
        final String studentPhone = getIntent().getStringExtra("studentPhone");
        String studentEmail = getIntent().getStringExtra("studentEmail");
        String studentBloodGroup = getIntent().getStringExtra("studentBloodGroup");


        profile_name.setText("Name :" +studentName);
        profile_id.setText("ID :" +studentId);
        profile_batch.setText("Batch : " +studentBatch);
        profile_phone.setText("Phone : " +studentPhone);
        profile_email.setText("Email : " +studentEmail);
        profile_bloodgroup.setText("Blood Group : " +studentBloodGroup);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(studentPhone.trim().length()>0){
                    if(ContextCompat.checkSelfPermission(StudentProfile.this,Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(StudentProfile.this, new  String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
                    }else {
                        Intent call = new Intent(Intent.ACTION_CALL);
                        call.setData(Uri.parse("tel:"+studentPhone));
                        startActivity(call);
                    }
                }else {
                    Toast.makeText(StudentProfile.this, "Phone number is Empty", Toast.LENGTH_SHORT).show();

                }

            }
        });




    }




}

