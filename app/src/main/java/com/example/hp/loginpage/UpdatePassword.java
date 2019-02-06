package com.example.hp.loginpage;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UpdatePassword extends AppCompatActivity {

    private EditText  new_password;
    private Button  save_password;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);


        new_password = findViewById(R.id.update_password);
        save_password = findViewById(R.id.btn_save_password);

//         Add back button
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();



        save_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_new_password = new_password.getText().toString();


                if(user_new_password.isEmpty()){
                    Toast.makeText(UpdatePassword.this, "Enter new password before save", Toast.LENGTH_SHORT).show();
                }else {

                    firebaseUser.updatePassword(user_new_password).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(UpdatePassword.this, "Password Changed", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(UpdatePassword.this, "Update Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

    }

}
