package com.example.mike.mikeleonad340;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    ArrayList<String> title;
    ArrayList<String> img;
    ArrayList<String> director;
    ArrayList<String> year;
    ArrayList<String> summary;
    Context context;
    View view1;
    ViewHolder viewHolder1;
    TextView textView;

    public RecyclerViewAdapter(Context context1,ArrayList<String> t, ArrayList<String> i, ArrayList<String> y, ArrayList<String> d, ArrayList<String> s)
    {

        title = t;
        img = i;
        director = d;
        year = y;
        summary = s;
        context = context1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView listTitle;
        public TextView listYear;
        RelativeLayout parentLayout;

        public ViewHolder(View v){

            super(v);

            listTitle = itemView.findViewById(R.id.title);
            listYear = itemView.findViewById(R.id.year);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position){
        System.out.println(title.get(position));
        holder.listTitle.setText(title.get(position));
        holder.listYear.setText(year.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(context, listDetails.class);
                intent.putExtra("detail_title", title.get(position));
                intent.putExtra("detail_year", year.get(position));
                intent.putExtra("detail_director", director.get(position));
                intent.putExtra("detail_img", img.get(position));
                intent.putExtra("detail_summary", summary.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){

        return title.size();
    }
}