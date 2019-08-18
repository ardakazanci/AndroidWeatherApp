package com.ardakazanci.weatherapp.Common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {

    /**
     * Static Values
     */
    static String API_KEY = "cb87360599f7c2d43478feb24a455f62";
    static String API_LINK = "http://api.openweathermap.org/data/2.5/weather";

    /**
     * @param lat : Latitude value.
     * @param lng : Longitude value.
     * @return String complete url.
     */
    static String apiRequest(String lat, String lng) {
        StringBuilder sb = new StringBuilder(API_LINK);
        sb.append(String.format("?lat=%s&lon=%s&APP_ID=%s&units=metric", lat, lng, API_KEY));
        return sb.toString();
    }

    /**
     * @param unixTimeStamp : UnixTime value.
     * @return : String time
     */
    static String unixTimeStampToDateTime(double unixTimeStamp) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        date.setTime((long) (unixTimeStamp * 1000));
        return dateFormat.toString();
    }

    /**
     * @param icon : Api image name
     * @return : String image url
     */
    static String getImage(String icon) {
        return String.format("http://api.openweathermap.org/img/w/%s.png", icon);
    }

    /**
     * @return Date now String.
     */
    static String getDateNow() {
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }




}
