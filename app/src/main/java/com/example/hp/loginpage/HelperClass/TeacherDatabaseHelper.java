package com.example.hp.loginpage.HelperClass;

import android.support.annotation.NonNull;

import com.example.hp.loginpage.ModelClass.TeacherModelClass;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TeacherDatabaseHelper {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceteacher;
    private List<TeacherModelClass>teacher = new ArrayList<>();

    public interface DataStatus{
        void DataLoaded(List<TeacherModelClass> teacher, List<String> Keys);
        void DataIsInseted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public TeacherDatabaseHelper() {
        mDatabase =FirebaseDatabase.getInstance();
        mReferenceteacher = mDatabase.getReference("teacher");
    }

    public void teacherList(final DataStatus dataStatus){
        mReferenceteacher.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                teacher.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot teacherSnapshot : dataSnapshot.getChildren()){
                    keys.add(teacherSnapshot.getKey());
                    TeacherModelClass teacherModelClass = teacherSnapshot.getValue(TeacherModelClass.class);
                    teacher.add(teacherModelClass);
                }
                dataStatus.DataLoaded(teacher, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addTeacher(TeacherModelClass teacherModelClass, final DataStatus dataStatus){
        String key = mReferenceteacher.push().getKey();
        mReferenceteacher.child(key).setValue(teacherModelClass)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInseted();
                    }
                });
    }

    public void updateTeacherInfo(String key, TeacherModelClass teacherModelClass, final DataStatus dataStatus){
        mReferenceteacher.child(key).setValue(teacherModelClass)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                       dataStatus.DataIsUpdated();
                    }
                });

    }

    public void deleteTeacherInfo(String key, final DataStatus dataStatus){
        mReferenceteacher.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }

}
