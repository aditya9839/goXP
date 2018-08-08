package com.example.acer.gooxpp.Activity;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.acer.gooxpp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



/**
 * A simple {@link Fragment} subclass.
 */
public class Map extends Fragment implements OnMapReadyCallback {

    CustomMapView mapView;
    GoogleMap map;
    Activity parentActivity;
    Button mget_directions,mopen_in_google_maps;
    double mlatitude,mlongitude;

    public void referance(Activity activity){
        this.parentActivity = activity;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = view.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
        mget_directions = view.findViewById(R.id.get_directions);
        mget_directions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr=My+Location&daddr=28.614341,77.377"));
                startActivity(intent);
            }
        });
        mopen_in_google_maps = view.findViewById(R.id.open_in_google_maps);
        mopen_in_google_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String uri = String.format(Locale.ENGLISH, "geo:%f,%f",mlatitude, mlongitude);
                String label = "ABC Label";
                String uriBegin = "geo:" + mlatitude + "," + mlongitude;
                String query = mlatitude + "," + mlongitude + "(" + label + ")";
                String encodedQuery = Uri.encode(query);
                String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                Uri uri = Uri.parse(uriString);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


//        mapView = (MapView) view.findViewById(R.id.mapview);
//        mapView.onCreate(savedInstanceState);
//
//        mapView.getMapAsync(parentActivity);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setMyLocationButtonEnabled(false);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);

        //in old Api Needs to call MapsInitializer before doing any CameraUpdateFactory call
        MapsInitializer.initialize(this.getActivity());
        mlatitude = 28.614341;
        mlongitude = 77.3774819;

        LatLng latLng = new LatLng(mlatitude,mlongitude);

        // Updates the location and zoom of the MapView
//        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 11);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng) // Sets the center of the map to
                .zoom(12)                   // Sets the zoom
                .bearing(0) // Sets the orientation of the camera to east
                .tilt(70)    // Sets the tilt of the camera to 30 degrees
                .build();
        MarkerOptions marker = new MarkerOptions().position(latLng).title("Hello Maps");
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon));
        map.animateCamera(CameraUpdateFactory.newCameraPosition(
                cameraPosition));
        map.addMarker(new MarkerOptions()
                .title("New Marker")
                .snippet("Check out this place.")
                .position(latLng).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("marker_icon",60,60))));

//            map.moveCamera(CameraUpdateFactory.newLatLngZoom(23,0));

    }
    public Bitmap resizeMapIcons(String iconName, int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getActivity().getPackageName()));
        return Bitmap.createScaledBitmap(imageBitmap, width, height, false);
    }

}