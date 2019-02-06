package com.example.hp.loginpage.AdminClass;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.loginpage.R;

public class TeacherInfo extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;

    private TextView profile_name, profile_dep, profile_position, profile_phone, profile_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_info);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        profile_name = findViewById(R.id.teacher_profile_name);
        //profile_dep = findViewById(R.id.teacher_profile_dep);
        profile_position = findViewById(R.id.teacher_profile_position);
        profile_phone = findViewById(R.id.teacher_profile_phone);
        profile_email = findViewById(R.id.teacher_profile_email);

        FloatingActionButton floatingActionButton = findViewById(R.id.call_teacher);

        
        String teacherName = getIntent().getStringExtra("teachername");
        String teacherPosition = getIntent().getStringExtra("teacherposition");
        final String teacherPhone = getIntent().getStringExtra("teacherphone");
        String teacherEmail = getIntent().getStringExtra("teacheremail");
        //String teacherDep = getIntent().getStringExtra("department");

        profile_name.setText("Name :" +teacherName);
       // profile_dep.setText("Department : " +teacherDep);
        profile_position.setText("Position : " +teacherPosition);
        profile_phone.setText("Phone : " +teacherPhone);
        profile_email.setText("Email : " +teacherEmail);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(teacherPhone.trim().length()>0){
                    if(ContextCompat.checkSelfPermission(TeacherInfo.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(TeacherInfo.this, new  String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
                    }else {
                        Intent call = new Intent(Intent.ACTION_CALL);
                        call.setData(Uri.parse("tel:"+teacherPhone));
                        startActivity(call);
                    }
                }else {
                    Toast.makeText(TeacherInfo.this, "Phone number is Empty", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}
