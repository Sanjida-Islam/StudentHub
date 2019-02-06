package com.example.hp.loginpage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.loginpage.HelperClass.NoticHelperClass;
import com.example.hp.loginpage.ModelClass.NoticeModelClass;
import com.example.hp.loginpage.Notice.Notice_RecycleView_config;

import java.util.List;

public class Delete_notice_Recycle_config {

    private Context mcontex;
    private Delete_notice_Recycle_config.NoticeAdapter mteacherAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<NoticeModelClass> noticeModelClasses, List<String> keys) {
        mcontex = context;
        mteacherAdapter = new Delete_notice_Recycle_config.NoticeAdapter(noticeModelClasses, keys);
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


    class NoticeAdapter extends RecyclerView.Adapter<Delete_notice_Recycle_config.NoticeListView> {
        private List<NoticeModelClass> noticeModelClasses;
        private List<String> mkeys;

        public NoticeAdapter(List<NoticeModelClass> noticeModelClasses, List<String> mkeys) {
            this.noticeModelClasses = noticeModelClasses;
            this.mkeys = mkeys;
        }

        @NonNull
        @Override
        public Delete_notice_Recycle_config.NoticeListView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new Delete_notice_Recycle_config.NoticeListView(viewGroup);
        }


        @Override
        public void onBindViewHolder(@NonNull Delete_notice_Recycle_config.NoticeListView noticeListView, final int position) {
            noticeListView.bind(noticeModelClasses.get(position), mkeys.get(position));

            noticeListView.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onBackPressed(position);


                }
            });

        }

        @Override
        public int getItemCount() {
            return noticeModelClasses.size();
        }



        public void onBackPressed(final int position) {
            final AlertDialog dialog = new AlertDialog.Builder(mcontex).create();

            dialog.setTitle("Are you sure you want to delete ?");

            dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {



                            new NoticHelperClass().deleteNotice(mkeys.get(position), new NoticHelperClass.DataStatus() {
                                @Override
                                public void DataLoaded(List<NoticeModelClass> notice, List<String> Keys) {

                                }

                                @Override
                                public void DataIsInseted() {

                                }


                                @Override
                                public void DataIsDeleted() {
                                    Toast.makeText(mcontex, "Deleted", Toast.LENGTH_SHORT).show();
                                    return;
                                }


                            });
//
                        }

                    });

            dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }
}

