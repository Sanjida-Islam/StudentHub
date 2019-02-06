package com.example.hp.loginpage.AdminClass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hp.loginpage.HelperClass.TeacherDatabaseHelper;
import com.example.hp.loginpage.ModelClass.TeacherModelClass;
import com.example.hp.loginpage.R;

import java.util.List;

public class AddTeacher extends AppCompatActivity {

    private EditText etName, etPosition, etPhone, etEmail;
    private Button addTeacher;
    private Spinner spinner_dep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        etName = findViewById(R.id.edit_teacher_name);
        etPosition = findViewById(R.id.edit_teacher_Position);
        etPhone = findViewById(R.id.edit_teacher_Phone);
        etEmail = findViewById(R.id.edit_teacher_Edit);
        addTeacher = findViewById(R.id.btn_Add_Teacher);
        spinner_dep = findViewById(R.id.spinner_dep);

        addTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TeacherModelClass teacherModelClass = new TeacherModelClass();
                teacherModelClass.setTeacherName(etName.getText().toString());
                teacherModelClass.setTeacherPossition(etPosition.getText().toString());
                teacherModelClass.setTeacherPhone(etPhone.getText().toString());
                teacherModelClass.setTeacherEmail(etEmail.getText().toString());
                teacherModelClass.setTeacherDep(spinner_dep.getSelectedItem().toString());

                                new TeacherDatabaseHelper().addTeacher(teacherModelClass, new TeacherDatabaseHelper.DataStatus() {
                    @Override
                    public void DataLoaded(List<TeacherModelClass> teacher, List<String> Keys) {

                    }

                    @Override
                    public void DataIsInseted() {
                        Toast.makeText(AddTeacher.this, "A new teacher info added ", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(AddTeacher.this,Admin_Home_page.class));
                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }

                });

            }
        });
    }
}
