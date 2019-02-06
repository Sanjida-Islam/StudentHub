package com.example.hp.loginpage;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.example.hp.loginpage.HelperClass.NoticHelperClass;
import com.example.hp.loginpage.ModelClass.NoticeModelClass;
import com.example.hp.loginpage.Notice.Notice_RecycleView_config;

import java.util.List;

public class DeleteNotice extends AppCompatActivity {

    private RecyclerView mrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_notice);

        mrecyclerView = findViewById(R.id.notice_recycleView);
        new NoticHelperClass().noticeList(new NoticHelperClass.DataStatus() {
            @Override
            public void DataLoaded(List<NoticeModelClass> notice, List<String> Keys) {
                // findViewById(R.id.floating_addNotice).setVisibility(View.GONE);
                new Delete_notice_Recycle_config().setConfig(mrecyclerView, DeleteNotice.this, notice, Keys);
            }

            @Override
            public void DataIsInseted() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


}
