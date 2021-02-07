package com.example.earthquakereport;

import android.content.Context;

import androidx.core.content.ContextCompat;

import static java.security.AccessController.getContext;

public class Earthquake  {

    private double magnitude;
    private String place;
    private String date;
    private String url;

    public Earthquake(double emagnitude,String eplace,String edate,String eurl) {

        magnitude=emagnitude;
        place=eplace;
        date=edate;
        url=eurl;
    }

    public double getMagnitude(){
        return magnitude;
    }
    public String getPlace(){
        return place;
    }
    public String getDate(){
        return date;
    }
    public String getUrl(){
        return url;
    }
    public int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return magnitudeColorResourceId;

    }



}
