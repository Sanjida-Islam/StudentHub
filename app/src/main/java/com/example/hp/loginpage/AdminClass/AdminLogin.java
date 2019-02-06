package com.example.hp.loginpage.AdminClass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.loginpage.R;

public class AdminLogin extends AppCompatActivity {

    private EditText adminName, adminPassword;
    private Button adminLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        adminName = findViewById(R.id.admin_username);
        adminPassword = findViewById(R.id.admin_password);
        adminLogin = findViewById(R.id.btn_admin_login);

        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty()) return;
                validate(adminName.getText().toString(), adminPassword.getText().toString());
            }
        });
    }

    private void validate(String useName, String userPassword){
        if((useName.equals("Admin"))& (userPassword.equals("12345"))){
            finish();
            Intent intent = new Intent(AdminLogin.this, Admin_Home_page.class);
            startActivity(intent);
        }else{
            Toast.makeText(AdminLogin.this, "Incorrect Email or password", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean isEmpty(){
        if(TextUtils.isEmpty(adminName.getText().toString())){
            adminName.setError("REQUIRED");
            return true;
        }
        if(TextUtils.isEmpty(adminPassword.getText().toString())){
            adminPassword.setError("REQUIRED");
            return true;
        }
        return false;
    }
}
