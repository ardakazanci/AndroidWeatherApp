package com.ardakazanci.weatherapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.ardakazanci.weatherapp.Common.Common;
import com.ardakazanci.weatherapp.Helper.Helper;
import com.ardakazanci.weatherapp.Model.OpenWeatherMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity implements LocationListener {


    TextView txtCity, txtLastUpdate, txtDescription, txtHumidity, txtTime, txtCelsius;
    ImageView imageView;

    LocationManager locationManager;
    String provider;
    static double lat, lng;
    OpenWeatherMap openWeatherMap;

    int MY_PERMISSION = 0;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI Control
        txtCity = findViewById(R.id.txtCity);
        txtCelsius = findViewById(R.id.txtCelsius);
        txtDescription = findViewById(R.id.txtDescription);
        txtHumidity = findViewById(R.id.txtHumidity);
        txtLastUpdate = findViewById(R.id.txtLastUpdate);
        txtTime = findViewById(R.id.txtTime);
        imageView = findViewById(R.id.imageView);

        // Location Control
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);


        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{

                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE


            }, MY_PERMISSION);

        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location == null) {
            Log.e("MainActivity", "NO LOCATION");
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{

                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE


            }, MY_PERMISSION);
        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    @Override
    public void onLocationChanged(Location location) {

        lat = location.getLatitude();
        lng = location.getLongitude();

        new GetWeather().execute(Common.apiRequest(String.valueOf(lat), String.valueOf(lng)));


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    private class GetWeather extends AsyncTask<String, Void, String> {

        ProgressDialog pd = new ProgressDialog(MainActivity.this);


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setTitle("Please Wait");
            pd.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s.contains("Error: Not found city")) {
                pd.dismiss();
                return;
            }

            Gson gson = new Gson();
            Type mType = new TypeToken<OpenWeatherMap>() {
            }.getType();
            openWeatherMap = gson.fromJson(s, mType);
            pd.dismiss();

            txtCity.setText(String.format("%s,%s", openWeatherMap.getName(), openWeatherMap.getSys().getCountry()));

            txtLastUpdate.setText(String.format("Last Updated: %s", Common.getDateNow()));

            txtDescription.setText(String.format("%s", openWeatherMap.getWeather().get(0).getDescription()));

            txtHumidity.setText(String.format("%d%%", openWeatherMap.getMain().getHumidity()));

            txtTime.setText(String.format("%s / %s", Common.unixTimeStampToDateTime(openWeatherMap.getSys().getSunrise()), Common.unixTimeStampToDateTime(openWeatherMap.getSys().getSunset())));

            Log.e("ZAMAN", Common.unixTimeStampToDateTime(openWeatherMap.getSys().getSunrise()));

            txtCelsius.setText(String.format("%.2f °C", openWeatherMap.getMain().getTemp()));

            Picasso.get().load(Common.getImage(openWeatherMap.getWeather().get(0).getIcon())).into(imageView);


        }

        @Override
        protected String doInBackground(String... strings) {
            String stream = null;
            String urlString = strings[0];

            Helper http = new Helper();
            stream = http.getHttpData(urlString);
            return stream;
        }


    }


}
