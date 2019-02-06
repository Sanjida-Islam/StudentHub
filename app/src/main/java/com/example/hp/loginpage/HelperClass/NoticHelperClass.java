package com.example.hp.loginpage.HelperClass;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hp.loginpage.ModelClass.NoticeModelClass;
import com.example.hp.loginpage.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NoticHelperClass {


    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferencenotice;
    private List<NoticeModelClass>notice = new ArrayList<>();

    public interface DataStatus{
        void DataLoaded(List<NoticeModelClass> notice, List<String> Keys);
        void DataIsInseted();
        void DataIsDeleted();
    }

    public NoticHelperClass() {
        mDatabase =FirebaseDatabase.getInstance();
        mReferencenotice = mDatabase.getReference("notice");
    }

    public void noticeList(final DataStatus dataStatus){
        mReferencenotice.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Query query = mReferencenotice.orderByPriority();///////
                notice.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot noticeSnapshot : dataSnapshot.getChildren()){
                    keys.add(noticeSnapshot.getKey());
                    NoticeModelClass noticeModelClass = noticeSnapshot.getValue(NoticeModelClass.class);
                    notice.add(noticeModelClass);
                }
                dataStatus.DataLoaded(notice, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addNotice(NoticeModelClass noticeModelClass, final DataStatus dataStatus){
        String key = mReferencenotice.push().getKey();
        mReferencenotice.child(key).setValue(noticeModelClass)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInseted();
                    }
                });
    }


    public void deleteNotice(String key, final DataStatus dataStatus){
        mReferencenotice.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }

}
