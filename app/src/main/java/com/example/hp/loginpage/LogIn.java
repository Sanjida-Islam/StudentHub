package com.example.hp.loginpage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {

    private Button login;
    private EditText Email, password;
    private TextView register;

    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;

    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        login = findViewById(R.id.login);
        register = findViewById(R.id.reg);
        Email =  findViewById(R.id.logEmail);
        password = findViewById(R.id.pass);
        forgotPassword = findViewById(R.id.tv_forgot_Password);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null){
            finish();
            startActivity(new Intent(LogIn.this,HomePage.class));
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isEmpty()) return;
                validate(Email.getText().toString(), password.getText().toString());
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(LogIn.this,Registration.class);
                startActivity(a);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this,PasswordActivity.class));
            }
        });

    }


    private void validate(String userId, String userpassword){

        progressDialog.setMessage("Loading...");
        progressDialog.show();

//         if((userId.equals("Admin"))&&(userpassword.equals("1234"))){
//         Intent intent = new Intent(LogIn.this,Admin_Home_page.class);
//         startActivity(intent);
//        }
             firebaseAuth.signInWithEmailAndPassword(userId, userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            checkEmailVerification();
                        } else {

                            Toast.makeText(LogIn.this, "Incorrect Email or password", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }
                    }
                });

    }

    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        if(emailflag){
            finish();
            startActivity(new Intent(LogIn.this,HomePage.class));
        }else {
            Toast.makeText(LogIn.this, "Verify your Email", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }

    }

    public boolean isEmpty(){
        if(TextUtils.isEmpty(Email.getText().toString())){
            Email.setError("REQUIRED");
            return true;
        }
        if(TextUtils.isEmpty(password.getText().toString())){
            password.setError("REQUIRED");
            return true;
        }
        return false;
    }
}
