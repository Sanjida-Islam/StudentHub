package com.example.hp.loginpage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.loginpage.ModelClass.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyProfile extends AppCompatActivity {

    private ImageView profile_pic;
    private TextView profile_name, profile_id, profile_batch, profile_phone, profile_email, profile_bloodgroup;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private  DatabaseReference rootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        profile_pic = findViewById(R.id.profile_pic);
        profile_name = findViewById(R.id.profile_name);
        profile_id = findViewById(R.id.profile_id);
        profile_batch = findViewById(R.id.profile_batch);
        profile_phone = findViewById(R.id.profile_phone);
        profile_email = findViewById(R.id.profile_email);
        profile_bloodgroup = findViewById(R.id.profile_bloodgroup);


        //Back  Button
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        rootRef = FirebaseDatabase.getInstance().getReference();

        //DatabaseReference table_student = firebaseDatabase.getReference("User");
        //DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        String currentUserID = firebaseAuth.getCurrentUser().getUid();

            rootRef.child("User").child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile =  dataSnapshot.getValue(UserProfile.class);
                profile_name.setText("Name : " +userProfile.getUserName());
                profile_id.setText("ID : " +userProfile.getUserId());
                profile_batch.setText("Batch : " +userProfile.getUserBatch());
                profile_phone.setText("Phone No : " +userProfile.getUserPhone());
                profile_email.setText("Email : " +userProfile.getUserEmail());
                profile_bloodgroup.setText("Blood Group : " +userProfile.getUserBloodGroup());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MyProfile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });



    }



//  for memu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.update_profile_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
//            case R.id.change_profile:
//                Toast.makeText(MyProfile.this, "Profile selected", Toast.LENGTH_SHORT).show();
//                return true;
            case R.id.change_info:
                startActivity(new Intent(MyProfile.this,UpdateProfileInfo.class));
                return true;
            case R.id.change_password:
                startActivity(new Intent(MyProfile.this,UpdatePassword.class));
                return true;
            case R.id.change_email:
                startActivity(new Intent(MyProfile.this,UpdateEmail.class));
                return true;
//            case R.id.delete_account:
//                Toast.makeText(MyProfile.this, "Account Deleted", Toast.LENGTH_SHORT).show();
//
//                return true;

                default:return super.onOptionsItemSelected(item);
        }

    }

    // Back to Which Activity


}
