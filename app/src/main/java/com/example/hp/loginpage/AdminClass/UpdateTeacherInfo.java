package com.example.hp.loginpage.AdminClass;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hp.loginpage.HelperClass.TeacherDatabaseHelper;
import com.example.hp.loginpage.ModelClass.TeacherModelClass;
import com.example.hp.loginpage.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class UpdateTeacherInfo extends AppCompatActivity {

    EditText teacherName, teacherPosition, teacherPhone, teacherEmail;
    Spinner spinner_dep;
    Button update;

    private String key;
    private String teacher_name;
    private String teacher_position;
    private String teacher_dep;
    private String teacher_phone;
    private String teacher_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_teacher_info);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        key = getIntent().getStringExtra("key");
        //teacher_name = getIntent().getStringExtra("teachername");
        //teacher_position = getIntent().getStringExtra("teacherposition");
        //teacher_phone = getIntent().getStringExtra("teacherphone");
        //teacher_email = getIntent().getStringExtra("teacheremail");
        teacher_dep = getIntent().getStringExtra("department");


        teacherName = findViewById(R.id.edit_teacher_name);
        teacherPosition = findViewById(R.id.edit_teacher_Position);
        teacherPhone = findViewById(R.id.edit_teacher_Phone);
        teacherEmail = findViewById(R.id.edit_teacher_Edit);
        update = findViewById(R.id.btn_Update_Teacher);

        spinner_dep = findViewById(R.id.spinner_dep);
        spinner_dep.setSelection(getIndex_spinnerItem(spinner_dep, teacher_dep));


        teacherName.setText(getIntent().getStringExtra("teachername"));
        teacherPosition.setText(getIntent().getStringExtra("teacherposition"));
        teacherPhone.setText(getIntent().getStringExtra("teacherphone"));
        teacherEmail.setText(getIntent().getStringExtra("teacheremail"));
        //teacherDep.setText("Department : "+getIntent().getStringExtra("department"));

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TeacherModelClass teacherModelClass = new TeacherModelClass();
                teacherModelClass.setTeacherName(teacherName.getText().toString());
                teacherModelClass.setTeacherDep(spinner_dep.getSelectedItem().toString());
                teacherModelClass.setTeacherPossition(teacherPosition.getText().toString());
                teacherModelClass.setTeacherPhone(teacherPhone.getText().toString());
                teacherModelClass.setTeacherEmail(teacherEmail.getText().toString());


                new TeacherDatabaseHelper().updateTeacherInfo(key, teacherModelClass, new TeacherDatabaseHelper.DataStatus() {
                    @Override
                    public void DataLoaded(List<TeacherModelClass> teacher, List<String> Keys) {

                    }

                    @Override
                    public void DataIsInseted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                        Toast.makeText(UpdateTeacherInfo.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void DataIsDeleted() {

                    }


                });
            }
        });
    }

    private int getIndex_spinnerItem(Spinner spinner, String item){
        int index = 0;
        for(int i =0; i<spinner.getCount(); i++){
            if(spinner.getItemAtPosition(i).equals(item)){
                index = i;
                break;
            }

        }
        return index;
    }


    //  for memu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.teacher_update_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_teacher_profile:
                //Toast.makeText(UpdateTeacherInfo.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                new TeacherDatabaseHelper().deleteTeacherInfo(key, new TeacherDatabaseHelper.DataStatus() {
                    @Override
                    public void DataLoaded(List<TeacherModelClass> teacher, List<String> Keys) {

                    }

                    @Override
                    public void DataIsInseted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(UpdateTeacherInfo.this, "Deleted", Toast.LENGTH_SHORT).show();
                        finish();
                        return;
                    }


                });
                return true;


            default:return super.onOptionsItemSelected(item);
        }

    }
}
