package com.example.acer.gooxpp.Permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.util.List;
//optimized code to getCurrent location or last known location but it will work if gps is on

public class CurrentLocation {

    private static GoogleApiClient googleApiClient;
    public final static int REQUEST_LOCATION = 199;
    static double d = 0,d1 = 0;
    private static LocationManager mLocationManager;
    private static Activity parentActivity;

    public static Location  getLastKnownLocation(Activity parentActivity) {
        CurrentLocation.parentActivity = parentActivity;
        mLocationManager = (LocationManager) parentActivity.getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;

        locationOn();
        for (String provider : providers) {
            if (ActivityCompat.checkSelfPermission(parentActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(parentActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(parentActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
            Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = l;
            }
            d = bestLocation.getLatitude();
            d1 = bestLocation.getLongitude();
            Log.d("Location", "d :" + d + "  " + d1);
            Toast.makeText(parentActivity, "Latitude :"+d+"\nLongitude :"+d1, Toast.LENGTH_SHORT).show();
        }
        Log.d("loc",""+bestLocation);
        return bestLocation;
    }

    private static void locationOn(){
        //parentActivity.setFinishOnTouchOutside(true);

        if (!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ) {
            Log.e("keshav","Gps already enabled");
            Toast.makeText(parentActivity,"Gps not enabled",Toast.LENGTH_SHORT).show();
            enableLoc();
        }
    }
    private static void enableLoc() {

        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(parentActivity)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                        @Override
                        public void onConnected(Bundle bundle) {
                        }
                        @Override
                        public void onConnectionSuspended(int i) {
                            Log.d("suspended","");
                            googleApiClient.connect();

                        }
                    })
                    .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(ConnectionResult connectionResult) {

                            Log.d("Location error","Location error " + connectionResult.getErrorCode());
                        }
                    }).build();
            googleApiClient.connect();
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(30 * 1000);
            locationRequest.setFastestInterval(5 * 1000);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);

            builder.setAlwaysShow(true);
            PendingResult<LocationSettingsResult> result =
                    LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    Log.d("REs",""+result);
                    final Status status = result.getStatus();
                    Log.d("REs",""+status);

                    switch (status.getStatusCode()) {

                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            Log.d("REs",""+status);
                            try {
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                status.startResolutionForResult(parentActivity, REQUEST_LOCATION);
                            } catch (IntentSender.SendIntentException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                }
            });
        }
    }
}