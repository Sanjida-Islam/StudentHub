package com.example.hp.loginpage.AdminClass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.hp.loginpage.HelperClass.TeacherDatabaseHelper;
import com.example.hp.loginpage.ModelClass.TeacherModelClass;
import com.example.hp.loginpage.R;

import java.util.List;

public class TeacherListActivity extends AppCompatActivity {

    private RecyclerView mrecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mrecyclerView = findViewById(R.id.st_teacher_recycleView);
        new TeacherDatabaseHelper().teacherList(new TeacherDatabaseHelper.DataStatus() {
            @Override
            public void DataLoaded(List<TeacherModelClass> teacher, List<String> Keys) {
                findViewById(R.id.st_loading_teacher_list).setVisibility(View.GONE);
                new Tea_RecycleView_config().setConfig(mrecyclerView, TeacherListActivity.this, teacher, Keys);
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
    }
}
