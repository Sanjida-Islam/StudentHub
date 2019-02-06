package com.example.hp.loginpage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hp.loginpage.ModelClass.UserProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Registration extends AppCompatActivity {

    private EditText username, userid, userbatch, userphone, useremail, userbloodgroup, userpassword;
    private Button registerbtn;
    //private TextView login;
    private FirebaseAuth firebaseAuth;
    private ProgressBar loadingProgress;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference table_student;

    String student_name, student_id, student_batch, student_phone, student_email, student_bloodGroup,student_password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIView();

        loadingProgress.setVisibility(View.INVISIBLE);
//        For Table
        firebaseDatabase = FirebaseDatabase.getInstance();
        table_student = firebaseDatabase.getReference("User");
//      for  table

        firebaseAuth = FirebaseAuth.getInstance();

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerbtn.setVisibility(View.INVISIBLE);
                loadingProgress.setVisibility(View.VISIBLE);

                validate();

               if(validate()){

                     final String user_email = useremail.getText().toString().trim();
                     final String user_password = userpassword.getText().toString().trim();

                   firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {


                           if(task.isSuccessful()) {
                               sendEmailVerification();
                               // updateUserInfo(username, userid, userbatch, userphone, userbloodgroup,firebaseAuth.getCurrentUser());
                           }else{
                               Toast.makeText(Registration.this, "Registration Failed", Toast.LENGTH_SHORT).show();

                               registerbtn.setVisibility(View.VISIBLE);
                               loadingProgress.setVisibility(View.INVISIBLE);
                           }
                       }
                   });

               }

            }
        });





    }


    private void setupUIView(){

        username = (EditText) findViewById(R.id.name);
        userid = (EditText)findViewById(R.id.userid);
        userbatch = (EditText)findViewById(R.id.batch);
        userphone= (EditText)findViewById(R.id.phone);
        useremail= (EditText)findViewById(R.id.email);
        userbloodgroup= (EditText)findViewById(R.id.bg);
        userpassword= (EditText)findViewById(R.id.password);
        registerbtn= (Button)findViewById(R.id.register);
        loadingProgress = (ProgressBar)findViewById(R.id.progressBar);
    }


    //Check if all details are given
    private Boolean validate(){

        boolean result = false;
        int batch = 34;
        String Batch = String.valueOf(batch);

        student_name = username.getText().toString().trim();
        student_id = userid.getText().toString().trim();
        student_batch = userbatch.getText().toString().trim();
        student_phone = userphone.getText().toString().trim();
        student_email = useremail.getText().toString().trim();
        student_bloodGroup = userbloodgroup.getText().toString().trim();
        student_password = userpassword.getText().toString().trim();


//        if(student_name.isEmpty() || student_id.isEmpty() || student_batch != Batch || student_phone.isEmpty() || student_email.isEmpty() || student_password.isEmpty()){
//            Toast.makeText(Registration.this, "please enter all the details",Toast.LENGTH_SHORT).show();
//
//            registerbtn.setVisibility(View.VISIBLE);
//            loadingProgress.setVisibility(View.INVISIBLE);
//        }
        if(student_name.isEmpty()){
            Toast.makeText(Registration.this, "please enter your name",Toast.LENGTH_SHORT).show();

            registerbtn.setVisibility(View.VISIBLE);
            loadingProgress.setVisibility(View.INVISIBLE);


        }else if(student_id.isEmpty()){
            Toast.makeText(Registration.this, "please enter your id",Toast.LENGTH_SHORT).show();

            registerbtn.setVisibility(View.VISIBLE);
            loadingProgress.setVisibility(View.INVISIBLE);


        }else if(! student_batch.equals(Batch)  ){
            Toast.makeText(Registration.this, "please enter your batch",Toast.LENGTH_SHORT).show();

            registerbtn.setVisibility(View.VISIBLE);
            loadingProgress.setVisibility(View.INVISIBLE);


        }else if(student_phone.isEmpty()){
            Toast.makeText(Registration.this, "please enter your phone no",Toast.LENGTH_SHORT).show();

            registerbtn.setVisibility(View.VISIBLE);
            loadingProgress.setVisibility(View.INVISIBLE);


        }else if( student_email.isEmpty()){
            Toast.makeText(Registration.this, "please enter your email",Toast.LENGTH_SHORT).show();

            registerbtn.setVisibility(View.VISIBLE);
            loadingProgress.setVisibility(View.INVISIBLE);


        }else if(student_password.isEmpty()){
            Toast.makeText(Registration.this, "please enter your password, at least 6 digit",Toast.LENGTH_SHORT).show();

            registerbtn.setVisibility(View.VISIBLE);
            loadingProgress.setVisibility(View.INVISIBLE);


        } else {
            //Toast.makeText(Registration.this, "batch and s batch : "+ student_batch,Toast.LENGTH_SHORT).show();
            result = true;
        }
        return result;
    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        sendUserData();
                        Toast.makeText(Registration.this, "Successfully Registered, Verification mail sent!",Toast.LENGTH_SHORT).show();

                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(Registration.this,LogIn.class));

                    }else{
                        Toast.makeText(Registration.this, "Verification mail hasn't been sent!",Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

    private void sendUserData(){
//        firebaseDatabase = FirebaseDatabase.getInstance();
//       String id = table_student.push().getKey();
//        DatabaseReference  table_student = firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile userProfile = new UserProfile(student_name, student_id, student_batch, student_phone, student_email, student_bloodGroup);
        table_student.child(firebaseAuth.getUid()).setValue(userProfile);
    }
}
