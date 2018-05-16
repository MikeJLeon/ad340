package com.example.mike.mikeleonad340;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CamsRVA extends RecyclerView.Adapter<CamsRVA.ViewHolder>{

    private Context context;
    private List<CamsDetail> cList;

    CamsRVA(Context context, List<CamsDetail> cList) {
        this.context = context;
        this.cList = cList;
    }


    @NonNull
    @Override
    public CamsRVA.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.camsrva_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CamsRVA.ViewHolder holder, int position) {
        final CamsDetail camera = cList.get(position);
        holder.camera_name.setText(camera.getId());
        holder.camera_description.setText(camera.getDescription());
        Glide.with(context).asBitmap().
                load(camera.getImageURL()).
                into(holder.camera_thumbnail);
        //load camera image
    }

    @Override
    public int getItemCount() {
        return cList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView camera_name;
        TextView camera_description;
        ImageView camera_thumbnail;
        RelativeLayout view_container;

        ViewHolder(View itemView) {
            super(itemView);
            view_container = itemView.findViewById(R.id.parentLayout);
            camera_name = itemView.findViewById(R.id.name);
            camera_description = itemView.findViewById(R.id.description);
            camera_thumbnail = itemView.findViewById(R.id.imageView);
        }
    }
}