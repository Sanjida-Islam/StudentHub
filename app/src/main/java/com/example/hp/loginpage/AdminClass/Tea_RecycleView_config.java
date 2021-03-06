package com.example.hp.loginpage.AdminClass;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.loginpage.ModelClass.TeacherModelClass;
import com.example.hp.loginpage.R;

import java.util.List;

public class Tea_RecycleView_config {
    private Context mcontex;
    private TeacherAdepter mteacherAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<TeacherModelClass>teacherModelClasses, List<String>keys){
        mcontex = context;
        mteacherAdapter = new TeacherAdepter(teacherModelClasses, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mteacherAdapter);
    }


    class TeacherListView extends RecyclerView.ViewHolder{

        private TextView teachername;
        private TextView teacherdep;


        private String key;

        public TeacherListView(ViewGroup parent){

            super(LayoutInflater.from(mcontex).
                    inflate(R.layout.teacher_list, parent, false));

            teachername = itemView.findViewById(R.id.list_teacher_name);
            teacherdep = itemView.findViewById(R.id.list_teacher_Dep);

        }

        public void bind(TeacherModelClass teacherModelClass, String key){
            teachername.setText(teacherModelClass.getTeacherName());
            teacherdep.setText(teacherModelClass.getTeacherDep());
            this.key = key;
        }

    }


    class TeacherAdepter extends RecyclerView.Adapter<TeacherListView>{
        private List<TeacherModelClass> teacherModelClasses;
        private List<String> mkeys;

        public TeacherAdepter(List<TeacherModelClass> teacherModelClasses, List<String> mkeys) {
            this.teacherModelClasses = teacherModelClasses;
            this.mkeys = mkeys;
        }

        @NonNull
        @Override
        public TeacherListView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new TeacherListView(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull TeacherListView teacherListView, final int i) {
            teacherListView.bind(teacherModelClasses.get(i), mkeys.get(i));

            // For example
            teacherListView.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, TeacherInfo.class);
                    //intent.putExtra("key",mkeys.toString());
                    intent.putExtra("teachername",teacherModelClasses.get(i).getTeacherName());
                    intent.putExtra("teacherdep",teacherModelClasses.get(i).getTeacherDep());
                    intent.putExtra("teacherposition",teacherModelClasses.get(i).getTeacherPossition());
                    intent.putExtra("teacherphone",teacherModelClasses.get(i).getTeacherPhone());
                    intent.putExtra("teacheremail",teacherModelClasses.get(i).getTeacherEmail());
                    mcontex.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return teacherModelClasses.size();
        }
    }


    }


