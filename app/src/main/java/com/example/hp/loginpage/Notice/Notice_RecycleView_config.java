package com.example.hp.loginpage.Notice;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.loginpage.DeleteNotice;
import com.example.hp.loginpage.HelperClass.NoticHelperClass;
import com.example.hp.loginpage.ModelClass.NoticeModelClass;
import com.example.hp.loginpage.R;
import com.example.hp.loginpage.Registration;

import java.util.List;

public class Notice_RecycleView_config {

    private Context mcontex;
    private NoticeAdapter mteacherAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<NoticeModelClass> noticeModelClasses, List<String> keys) {
        mcontex = context;
        mteacherAdapter = new NoticeAdapter(noticeModelClasses, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mteacherAdapter);
    }


    class NoticeListView extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView date;
        private TextView description;
        private TextView priority;


        private String key;

        public NoticeListView(ViewGroup parent) {

            super(LayoutInflater.from(mcontex).
                    inflate(R.layout.notice_list_cardview, parent, false));

            title = itemView.findViewById(R.id.notice_title);
            date = itemView.findViewById(R.id.notice_date);
            description = itemView.findViewById(R.id.notice_description);
            priority = itemView.findViewById(R.id.notice_priority);

        }

        public void bind(NoticeModelClass noticeModelClass, String key) {
            title.setText(noticeModelClass.getTitle());
            date.setText(noticeModelClass.getDate());
            description.setText(noticeModelClass.getDescription());
            //description.setText(noticeModelClass.getPriority());///
            priority.setText(String.valueOf(noticeModelClass.getPriority()));
            this.key = key;
        }
    }


    class NoticeAdapter extends RecyclerView.Adapter<NoticeListView> {
        private List<NoticeModelClass> noticeModelClasses;
        private List<String> mkeys;

        public NoticeAdapter(List<NoticeModelClass> noticeModelClasses, List<String> mkeys) {
            this.noticeModelClasses = noticeModelClasses;
            this.mkeys = mkeys;
        }

        @NonNull
        @Override
        public NoticeListView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new NoticeListView(viewGroup);
        }


        @Override
        public void onBindViewHolder(@NonNull NoticeListView noticeListView, final int i) {
            noticeListView.bind(noticeModelClasses.get(i), mkeys.get(i));


        }

        @Override
        public int getItemCount() {
            return noticeModelClasses.size();
        }
    }
}
