package com.ardakazanci.weatherapp.Common;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {

    /**
     * Static Values
     */
    public static String API_KEY = "2e91876f6ff3d3490a997238437497bc";
    public static String API_LINK = "http://api.openweathermap.org/data/2.5/weather";

    /**
     * @param lat : Latitude value.
     * @param lng : Longitude value.
     * @return String complete url.
     */
    public static String apiRequest(String lat, String lng) {
        StringBuilder sb = new StringBuilder(API_LINK);
        sb.append(String.format("?lat=%s&lon=%s&appid=%s&units=metric", lat, lng, API_KEY));
        return sb.toString();
    }
    
    /**
     * @param unixTimeStamp : UnixTime value.
     * @return : String time
     */
    public static String unixTimeStampToDateTime(long unixTimeStamp) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date(unixTimeStamp * 1000L);
        String formattedDate = dateFormat.format(date);
        Log.e("ZAMAN", dateFormat.toString());
        return formattedDate;
    }

    /**
     * @param icon : Api image name
     * @return : String image url
     */
    public static String getImage(String icon) {
        return String.format("http://api.openweathermap.org/img/w/%s.png", icon);
    }

    /**
     * @return Date now String.
     */
    public static String getDateNow() {
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }




}
