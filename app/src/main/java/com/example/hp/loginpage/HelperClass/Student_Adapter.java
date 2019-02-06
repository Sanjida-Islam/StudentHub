package com.example.hp.loginpage.HelperClass;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hp.loginpage.ModelClass.UserProfile;
import com.example.hp.loginpage.R;

import java.util.List;

public class Student_Adapter extends ArrayAdapter<UserProfile> {

    private Activity context;
    private List<UserProfile> studentlist;

    public Student_Adapter(Activity context, List<UserProfile> studentlist) {
        super(context, R.layout.student_list_layout, studentlist);
        this.context = context;
        this.studentlist = studentlist;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View ListViewItem = inflater.inflate(R.layout.student_list_layout,null,true);

        TextView textViewName =ListViewItem.findViewById(R.id.list_name);
        TextView textViewDep =ListViewItem.findViewById(R.id.list_id);


        UserProfile student = studentlist.get(position);


        textViewName.setText(student.getUserName());
        textViewDep.setText(student.getUserId());

        return ListViewItem;
    }
}
