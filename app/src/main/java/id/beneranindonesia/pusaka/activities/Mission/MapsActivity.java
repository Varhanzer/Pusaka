package id.beneranindonesia.pusaka.activities.Mission;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.activities.Mission.directionhelpers.FetchURL;
import id.beneranindonesia.pusaka.activities.Mission.directionhelpers.GPSTracker;
import id.beneranindonesia.pusaka.activities.Mission.directionhelpers.TaskLoadedCallback;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, TaskLoadedCallback {

        private GoogleMap mMap;
        private MarkerOptions place1, place2;
        Button getDirection;
        private Polyline currentPolyline;
        private LocationManager locationManager;
        private GPSTracker gps;



    @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_maps);
            locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);


            getDirection = findViewById(R.id.btnMulai);

            getDirection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gps = new GPSTracker(MapsActivity.this);
                    new FetchURL(MapsActivity.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");
                    if(gps.canGetLocation()){
                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongitude();

                        place1 = new MarkerOptions().position(new LatLng(latitude, longitude)).title("You are here");
                        place2 = new MarkerOptions().position(new LatLng(27.667491, 85.3208583)).title("Location 2");
                    } else{
                        gps.showSettingsAlert();
                    }

                }
            });
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            mapFragment.getMapAsync(this);
        }


    @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            Log.d("mylog", "Added Markers");
            mMap.addMarker(place1);
            mMap.addMarker(place2);
        }


        private String getUrl(LatLng origin, LatLng dest, String directionMode) {
            // Origin of route
            String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
            // Destination of route
            String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
            // Mode
            String mode = "mode=" + directionMode;
            // Building the parameters to the web service
            String parameters = str_origin + "&" + str_dest + "&" + mode;
            // Output format
            String output = "json";
            // Building the url to the web service
            String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
            return url;
        }

        @Override
        public void onTaskDone(Object... values) {
            if (currentPolyline != null)
                currentPolyline.remove();
            currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
        }

}
