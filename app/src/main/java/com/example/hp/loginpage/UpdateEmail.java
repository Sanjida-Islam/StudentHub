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

public class UpdateEmail extends AppCompatActivity {

    private EditText new_email;
    private Button save_email;
    private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_email);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);


        new_email = findViewById(R.id.update_email);
        save_email = findViewById(R.id.btn_save_email);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();



        save_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_new_email = new_email.getText().toString();

                if(user_new_email.isEmpty()){
                    Toast.makeText(UpdateEmail.this, "Set new Email before save", Toast.LENGTH_SHORT).show();
                }else {

                    firebaseUser.updateEmail(user_new_email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(UpdateEmail.this, "Verification mail sent, Varify before log in", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(UpdateEmail.this, "Update Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

    }
}
