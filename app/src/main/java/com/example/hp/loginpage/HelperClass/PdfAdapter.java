package com.example.hp.loginpage.HelperClass;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.loginpage.ModelClass.Upload;
import com.example.hp.loginpage.R;

import java.util.ArrayList;

public class PdfAdapter extends RecyclerView.Adapter<PdfAdapter.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<Upload> items = new ArrayList<>();
    ArrayList<Upload> urls = new ArrayList<>();


    public void  update(Upload name, Upload url){
        items.add(name);
        urls.add(url);

    }
    public PdfAdapter(RecyclerView recyclerView, Context context, ArrayList<Upload> items, ArrayList<Upload>urls) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.items = items;
        this.urls = urls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {// to create views for recycler view item..
        View view = LayoutInflater.from(context).inflate(R.layout.pdf_view, viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // initialise the elements of individual items..

        viewHolder.nameOfFile.setText(items.get(i).getName());

    }

    @Override
    public int getItemCount() {// return the no of items...
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nameOfFile;

            public ViewHolder(@NonNull View itemView) {// individual list items
                super(itemView);
                nameOfFile = itemView.findViewById(R.id.pdfID);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = recyclerView.getChildLayoutPosition(v);
                        Upload upload = urls.get(position);
                        Intent intent = new Intent();
                        intent.setType(Intent.ACTION_VIEW);// this help to view  something..
                        intent.setData(Uri.parse(upload.getUrl()));
                        context.startActivity(intent);
                    }
                });
            }

    }
}
