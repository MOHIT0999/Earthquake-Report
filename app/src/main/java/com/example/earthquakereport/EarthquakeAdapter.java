package com.example.earthquakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {


    public EarthquakeAdapter(@NonNull Context context, ArrayList<Earthquake> earthquakes) {
        super(context,0,earthquakes);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


//        View listItemView=convertView;        // beacuse i get rabdom list on scrolling again and again
//        if (listItemView==null) {
           View listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_items, parent, false);

            Earthquake currentEarthquake = (Earthquake) getItem(position);

            String location = currentEarthquake.getPlace();
            String[] place = location.split(",");
            String near;
            String acualplace;

            if (place.length != 2) {
                near = null;
                acualplace = place[0];

            } else {
                near = place[0];
                acualplace = place[1];

            }


            TextView magnitudeView = listItemView.findViewById(R.id.magnitude);
            magnitudeView.setText(currentEarthquake.getMagnitude() + "");

            TextView placeView = listItemView.findViewById(R.id.near);
            placeView.setText(near);
            TextView actauView = listItemView.findViewById(R.id.actualplace);
            actauView.setText(acualplace);

            TextView dateView = listItemView.findViewById(R.id.date);
            dateView.setText(currentEarthquake.getDate());

            // Set the proper background color on the magnitude circle.
            // Fetch the background from the TextView, which is a GradientDrawable.
            GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

            // Get the appropriate background color based on the current earthquake magnitude
            int magnitudeColor = currentEarthquake.getMagnitudeColor(currentEarthquake.getMagnitude());
            //to get the color hex value from color id
            //to convert the color resource ID into a color integer value
            int magnitudebyColor = ContextCompat.getColor(getContext(), magnitudeColor);

            // Set the color on the magnitude circle
            magnitudeCircle.setColor(magnitudebyColor);


            return listItemView;

    }

}
