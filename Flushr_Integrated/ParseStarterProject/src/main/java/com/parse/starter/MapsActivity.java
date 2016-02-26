package com.parse.starter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

public class MapsActivity extends FragmentActivity implements LocationListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

    ImageButton createPageButton, viewWallButton;
    LocationManager locationManager;
    String provider;

    public static String markerID;

    public static Double userLat;
    public static Double userLng;

    public void createPage(View view) {

        Intent intent = new Intent(this, CreatePageActivity.class);
        startActivity(intent);

    }


    public void viewWall(View view) {

        Intent intent = new Intent(this, ToiletWallActivity.class);
        startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        //mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);

        createPageButton = (ImageButton) findViewById(R.id.createButton);
        viewWallButton = (ImageButton) findViewById(R.id.drawingWallButton);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {

    }

    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
////        LatLng sydney = new LatLng(-34, 151);
////        mMap.addMarker(new MarkerOptions().position(new LatLng(51.513892, -0.1000887)).title("St Paul's"));
////        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.513892, -0.1000887), 15));
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            mMap.setMyLocationEnabled(true);
//        }
//        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//        mMap.setTrafficEnabled(true);
//        mMap.setIndoorEnabled(true);
//        mMap.setBuildingsEnabled(true);
//        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    @Override
    public void onLocationChanged(Location location) {

        userLat = location.getLatitude();
        userLng = location.getLongitude();

        Log.i("latitude", userLat.toString());
        Log.i("longitude", userLng.toString());

        mMap.clear();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Restroom");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.i("Objects found", Integer.toString(objects.size()));
                    for (ParseObject object : objects) {
                        mMap.addMarker(new MarkerOptions().position(new LatLng(object.getDouble("latitude"),
                                object.getDouble("longitude"))).title(String.valueOf(object.get("nameofres")))
                                .snippet(String.valueOf(object.getObjectId())));
                    }
                } else {
                    Log.i("FATAL", "");
                }

            }
        });


        mMap.addMarker(new MarkerOptions().position(new LatLng(userLat, userLng)).title("Your location"));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(userLat, userLng), 12));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                markerID = marker.getSnippet();
                Log.i("Marker2", markerID);
                getIntoInfo();
                return true;
            }
        });

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

    @Override
    public void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.i("Marker", "Marked clicked");
        return false;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void getIntoInfo(){
        Intent i = new Intent(this, RestroomInfoActivity.class);
        startActivity(i);
    }
}
