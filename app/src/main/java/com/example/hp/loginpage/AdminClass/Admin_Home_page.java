package com.example.hp.loginpage.AdminClass;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

//import com.github.clans.fab.FloatingActionButton;
import com.example.hp.loginpage.ChatRoom;
import com.example.hp.loginpage.DeleteNotice;
import com.example.hp.loginpage.HelperClass.TeacherDatabaseHelper;
import com.example.hp.loginpage.HomePage;
import com.example.hp.loginpage.LogIn;
import com.example.hp.loginpage.ModelClass.TeacherModelClass;
import com.example.hp.loginpage.PDF.PdfPost;
import com.example.hp.loginpage.R;

import java.util.List;

public class Admin_Home_page extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mrecyclerView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private FloatingActionButton floatingActionButton;

    private long backPressedTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__home_page);

        mrecyclerView = findViewById(R.id.teacher_recycleView);
        new TeacherDatabaseHelper().teacherList(new TeacherDatabaseHelper.DataStatus() {
            @Override
            public void DataLoaded(List<TeacherModelClass> teacher, List<String> Keys) {
                findViewById(R.id.loading_teacher_list).setVisibility(View.GONE);
                new RecycleView_config().setConfig(mrecyclerView, Admin_Home_page.this, teacher, Keys);
            }

            @Override
            public void DataIsInseted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }

        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        floatingActionButton = findViewById(R.id.floating_addTeacher);
        mDrawerLayout = findViewById(R.id.drawer_layout_admin);

        mToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view_admin);
        navigationView.setNavigationItemSelectedListener(this);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_Home_page.this, AddTeacher.class));
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

//        if (id == R.id.nav_student) {
//            startActivity(new Intent(Admin_Home_page.this,HomePage.class));
//
//        } else
        if (id == R.id.nav_teacher) {
            startActivity(new Intent(Admin_Home_page.this, Admin_Home_page.class));

        } else if (id == R.id.nav_pdf) {
            startActivity(new Intent(Admin_Home_page.this, PdfPost.class));

        } else if (id == R.id.nav_notice) {
            startActivity(new Intent(Admin_Home_page.this, DeleteNotice.class));

        } else if (id == R.id.nav_logout) {

            finish();
            startActivity(new Intent(Admin_Home_page.this, LogIn.class));

        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finish();
            startActivity(new Intent(Admin_Home_page.this, HomePage.class));
        } else {
            Toast.makeText(this, "Press back again to Log out", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}
