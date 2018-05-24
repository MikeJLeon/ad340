package com.example.mike.mikeleonad340;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

public class MapDriver implements GoogleMap.InfoWindowAdapter{
    /**
     * Created by User on 10/2/2017.
     */


        private final View mWindow;
        private Context mContext;
        private List<CamsDetail> cList;

        @SuppressLint("InflateParams")
        MapDriver(Context context, List<CamsDetail> cList){
            mContext = context;
            this.cList = cList;
            mWindow = LayoutInflater.from(context).inflate(R.layout.activity_mapsinfo, null);
        }

        private void rendowWindowText(Marker marker, View view){

            String location = marker.getSnippet();
            String title = marker.getTitle();
            TextView camTitle = view.findViewById(R.id.title);
            ImageView camera_thumbnail = view.findViewById(R.id.imageView2);
            camTitle.setText(String.format("Name:%s", title));
            camTitle.append("\n"+"Location:"+location);
            Log.d("Yo", (String)marker.getTag());
            String url = (String)marker.getTag();
            Log.d("Yo", camera_thumbnail.toString());
            Glide.with(mContext).asBitmap().load(url).into(camera_thumbnail);
        }

        @Override
        public View getInfoWindow(Marker marker) {
            rendowWindowText(marker, mWindow);

            return mWindow;
        }

        @Override
        public View getInfoContents(Marker marker) {
            rendowWindowText(marker, mWindow);
            return mWindow;
        }

}

