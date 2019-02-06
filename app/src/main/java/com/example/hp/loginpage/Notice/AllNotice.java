package com.example.hp.loginpage.Notice;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.hp.loginpage.HelperClass.NoticHelperClass;
import com.example.hp.loginpage.ModelClass.NoticeModelClass;
import com.example.hp.loginpage.R;

import java.util.List;

public class AllNotice extends AppCompatActivity {

    private RecyclerView mrecyclerView;
    private FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_notice);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mrecyclerView = findViewById(R.id.notice_recycleView);
        new NoticHelperClass().noticeList(new NoticHelperClass.DataStatus() {
            @Override
            public void DataLoaded(List<NoticeModelClass> notice, List<String> Keys) {
               // findViewById(R.id.floating_addNotice).setVisibility(View.GONE);
                new Notice_RecycleView_config().setConfig(mrecyclerView, AllNotice.this, notice, Keys);
            }

            @Override
            public void DataIsInseted() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });




        floatingActionButton = findViewById(R.id.floating_addNotice);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllNotice.this,NewNotice.class));
            }
        });
    }


}
