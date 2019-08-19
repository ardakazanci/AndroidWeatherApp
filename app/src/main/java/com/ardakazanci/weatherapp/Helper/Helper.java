package com.ardakazanci.weatherapp.Helper;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Helper {

    static String stream = null;

    public Helper() {

    }

    /**
     * @param apiUrl : Working url
     * @return : Response to String info.
     */
    public String getHttpData(String apiUrl) {

        try {

            URL url = new URL(apiUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            if (httpURLConnection.getResponseCode() == 200) {
                BufferedReader r = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    sb.append(line);
                }
                stream = sb.toString();
                httpURLConnection.disconnect();
            }

        } catch (MalformedURLException e) {

            Log.e("Helper.Java", "URL Format Exception" + e.toString());

        } catch (IOException e) {

            Log.e("Helper.Java", "Open Connection Exception" + e.toString());

        }


        return stream;

    }

}
