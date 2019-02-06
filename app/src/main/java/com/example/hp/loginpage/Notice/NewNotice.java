package com.example.hp.loginpage.Notice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.hp.loginpage.HelperClass.NoticHelperClass;
import com.example.hp.loginpage.ModelClass.NoticeModelClass;
import com.example.hp.loginpage.R;
import com.example.hp.loginpage.Registration;

import java.util.List;

public class NewNotice extends AppCompatActivity {

    private EditText new_notice_title, new_notice_date, new_notice_description;
    private NumberPicker new_notice_priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notice);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new_notice_title = findViewById(R.id.new_notice_title);
        new_notice_date = findViewById(R.id.new_notice_date);
        new_notice_description = findViewById(R.id.new_notice_description);
        new_notice_priority = findViewById(R.id.number_picker_priority);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        new_notice_priority.setMinValue(1);
        new_notice_priority.setMaxValue(19);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.notice_add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_notice:
                validate();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void validate(){

        //boolean result = false;
       String date = new_notice_date.getText().toString();
        String title = new_notice_title.getText().toString();
        String description = new_notice_description.getText().toString();

        if(date.isEmpty() || title.isEmpty() || description.isEmpty()){
            Toast.makeText(NewNotice.this, "please enter date",Toast.LENGTH_SHORT).show();
        }else {
            saveNotice();
        }
        //return result;

    }

    private void saveNotice(){
        //int priority = new_notice_priority.getValue();
        NoticeModelClass noticeModelClass = new NoticeModelClass();
        noticeModelClass.setTitle(new_notice_title.getText().toString());
        noticeModelClass.setDate(new_notice_date.getText().toString());
        noticeModelClass.setDescription(new_notice_description.getText().toString());
        noticeModelClass.setPriority(new_notice_priority.getValue());

            new NoticHelperClass().addNotice(noticeModelClass, new NoticHelperClass.DataStatus() {
                @Override
                public void DataLoaded(List<NoticeModelClass> notice, List<String> Keys) {

                }

                @Override
                public void DataIsInseted() {
                    Toast.makeText(NewNotice.this, "New Notice Added ", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(NewNotice.this, AllNotice.class));
                }

                @Override
                public void DataIsDeleted() {

                }
            });

    }
}
