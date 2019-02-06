package com.example.hp.loginpage;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.loginpage.ModelClass.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfileInfo extends AppCompatActivity {

    private EditText name_update, id_update, batch_update, phone_update,email_update, bloodgroup_update;
    private Button save;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private  DatabaseReference rootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile_info);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        name_update = findViewById(R.id.name_update);
        id_update = findViewById(R.id.id_update);
        batch_update = findViewById(R.id.batch_update);
        phone_update = findViewById(R.id.phone_update);
        email_update = findViewById(R.id.email_update);
        bloodgroup_update =  findViewById(R.id.bloodgroup_update);
        save = findViewById(R.id.btn_save_info);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        rootRef = FirebaseDatabase.getInstance().getReference();

        final String currentUserID = firebaseAuth.getCurrentUser().getUid();

        rootRef.child("User").child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile =  dataSnapshot.getValue(UserProfile.class);
                name_update.setText(userProfile.getUserName());
                id_update.setText(userProfile.getUserId());
                batch_update.setText(userProfile.getUserBatch());
                phone_update.setText(userProfile.getUserPhone());
                email_update.setText(userProfile.getUserEmail());
                bloodgroup_update.setText(userProfile.getUserBloodGroup());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpdateProfileInfo.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = name_update.getText().toString();
                String id = id_update.getText().toString();
                String batch = batch_update.getText().toString();
                String phone = phone_update.getText().toString();
                String email = email_update.getText().toString();
                String bloodGroup = bloodgroup_update.getText().toString();

                UserProfile userProfile =  new UserProfile(name, id, batch, phone, email, bloodGroup);

                rootRef.child("User").child(currentUserID).setValue(userProfile);
                finish();

            }
        });
    }
}
