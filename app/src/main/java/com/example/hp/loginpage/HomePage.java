package com.example.hp.loginpage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hp.loginpage.AdminClass.AdminLogin;
import com.example.hp.loginpage.AdminClass.TeacherListActivity;
import com.example.hp.loginpage.HelperClass.Student_Adapter;
import com.example.hp.loginpage.ModelClass.UserProfile;
import com.example.hp.loginpage.Notice.AllNotice;
import com.example.hp.loginpage.Notice.NewNotice;
import com.example.hp.loginpage.PDF.PdfView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseStudent;

    ListView listViewstudent;
    List<UserProfile> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        listViewstudent = findViewById(R.id.studentlistview);
        studentList = new ArrayList<>();

        databaseStudent = FirebaseDatabase.getInstance().getReference("User");

        firebaseAuth = FirebaseAuth.getInstance();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        mToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // For example

        listViewstudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String studentProfile = parent.getItemAtPosition(position).toString();

                Intent intent = new Intent(HomePage.this, StudentProfile.class);
                intent.putExtra("studentName",studentList.get(position).getUserName());
                intent.putExtra("studentID",studentList.get(position).getUserId());
                intent.putExtra("studentBatch",studentList.get(position).getUserBatch());
                intent.putExtra("studentPhone",studentList.get(position).getUserPhone());
                intent.putExtra("studentEmail",studentList.get(position).getUserEmail());
                intent.putExtra("studentBloodGroup",studentList.get(position).getUserBloodGroup());
                startActivity(intent);


            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(HomePage.this,HomePage.class));

        } else if (id == R.id.nav_notice) {
            startActivity(new Intent(HomePage.this,AllNotice.class));

        }
//        else if (id == R.id.nav_chatroom) {
//            startActivity(new Intent(HomePage.this,ChatRoom.class));
//
//        }
        else if (id == R.id.nav_teacherinfo) {
            startActivity(new Intent(HomePage.this,TeacherListActivity.class));

        }else if (id == R.id.nav_pdf) {
            startActivity(new Intent(HomePage.this,PdfView.class));

        }
             else if (id == R.id.nav_calculator) {
            startActivity(new Intent(HomePage.this,Calculator.class));

        }
            else if (id == R.id.nav_myProfile) {
            startActivity(new Intent(HomePage.this,MyProfile.class));

        }else if (id == R.id.nav_Admin_panel) {
            startActivity(new Intent(HomePage.this,AdminLogin.class));

        } else if (id == R.id.nav_logout) {

            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(HomePage.this,LogIn.class));

        }

        return true;
    }


    // Student ListView

    @Override
    protected void onStart() {
        super.onStart();
        databaseStudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                studentList.clear();

                for(DataSnapshot studentsnapshot: dataSnapshot.getChildren()){
                    UserProfile student = studentsnapshot.getValue(UserProfile.class);
                    studentList.add(student);
                }

                Student_Adapter adapter = new Student_Adapter(HomePage.this,studentList);
                listViewstudent.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        final AlertDialog dialog =  new AlertDialog.Builder(HomePage.this).create();
        dialog.setTitle("Are you sure you want to quit ?");

        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent a = new Intent(Intent.ACTION_MAIN);
                        a.addCategory(Intent.CATEGORY_HOME);
                        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(a);



                    }
                });

        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
