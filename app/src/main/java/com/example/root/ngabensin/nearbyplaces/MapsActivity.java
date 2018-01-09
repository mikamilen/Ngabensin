package com.example.root.ngabensin.nearbyplaces;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Geocoder;
import android.location.Location;
import android.location.Address;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import com.example.root.ngabensin.Model.Example;
import com.example.root.ngabensin.Model.Leg;
import com.example.root.ngabensin.Model.Route;
import com.example.root.ngabensin.R;
import com.example.root.ngabensin.Services.MapsAPI;
import com.example.root.ngabensin.Services.MapsService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMarkerDragListener
{


    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentLocationMarker;
    public static final int REQUEST_LOCATION_CODE = 99;
    int PROXIMITY_RADIUS = 10000;
    double latitude, longitude;
    double end_latitude, end_longitude;
    String url;
    private MapsAPI mapsAPI;
    ArrayList markerPoints= new ArrayList();
    private List<LatLng> latLngList;
    private MarkerOptions marker;
    private LatLng myLocation;

    private TextView txtDistance, txtDuration, txtCurrentLoc, txtToLoc;
    private RelativeLayout relativeLocationInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.root.ngabensin.R.layout.activity_maps);
        MapsService mapsService = new MapsService();
        mapsAPI = mapsService.initRetrofit();

        txtDistance = (TextView)findViewById(R.id.distance);
        txtDuration = (TextView)findViewById(R.id.duration);
        txtCurrentLoc = (TextView)findViewById(R.id.curentLocation);
        txtToLoc = (TextView)findViewById(R.id.toLocation);
        relativeLocationInfo = (RelativeLayout)findViewById(R.id.locationInfo);


        latLngList = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        //Check if Google Play Services Available or not
        if (!CheckGooglePlayServices()) {
            Log.d("onCreate", "Finishing test case since Google Play Services are not available");
            finish();
        }
        else {
            Log.d("onCreate","Google Play Services available.");
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    private boolean CheckGooglePlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        0).show();
            }
            return false;
        }
        return true;
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }


//        mMap.setOnMarkerDragListener(this);
//        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if(latLngList.size() > 0){
                    refreshMap(mMap);
                    latLngList.clear();
                }
                latLngList.add(latLng);
                mMap.addMarker(marker);
                mMap.addMarker(new MarkerOptions().position(latLng));
                LatLng defaultLocation = marker.getPosition();
                myLocation = marker.getPosition();
                LatLng destinationLocation = latLng;
                System.out.println(defaultLocation.latitude);
                System.out.println(destinationLocation.latitude);
                getDirection(String.valueOf(defaultLocation.latitude)+","+String.valueOf(defaultLocation.longitude),
                        String.valueOf(destinationLocation.latitude)+","+String.valueOf(destinationLocation.longitude));
            }
        });
    }

    private void refreshMap(GoogleMap mapInstance){
        mapInstance.clear();
    }

    private void getDirection(String origin, String destination){
        mapsAPI.getDirection(origin,destination).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                List<LatLng> mDirections = getDirectionPolylines(response.body().getRoutes());
                txtDuration.setText(response.body().getRoutes().get(0).getLegs().get(0).getDuration().getText());
                relativeLocationInfo.setVisibility(View.VISIBLE);
                txtDistance.setText(response.body().getRoutes().get(0).getLegs().get(0).getDistance().getText());
                txtToLoc.setText(response.body().getRoutes().get(0).getLegs().get(0).getEndAddress());
                txtCurrentLoc.setText(response.body().getRoutes().get(0).getLegs().get(0).getStartAddress());
                drawRouteOnMap(mMap, mDirections);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(MapsActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void drawRouteOnMap(GoogleMap map, List<LatLng> positions){
        PolylineOptions options = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        options.addAll(positions);
        Polyline polyline = map.addPolyline(options);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(positions.get(1).latitude, positions.get(1).longitude))
                .zoom(15)
                .build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private List<LatLng> getDirectionPolylines(List<Route> routes){
        List<LatLng> directionList = new ArrayList<LatLng>();
        for(Route route : routes){
            List<Leg> legs = route.getLegs();
            for(Leg leg : legs){
                List<Leg.Step> steps = leg.getSteps();
                for(Leg.Step step : steps){
                    Leg.Polyline polyline = step.getPolyline();
                    String points = polyline.getPoints();
                    List<LatLng> singlePolyline = decodePoly(points);
                    for (LatLng direction : singlePolyline){
                        directionList.add(direction);
                    }
                }
            }
        }
        return directionList;
    }

    private List<LatLng> decodePoly(String encoded) {
        List<LatLng> poly = new ArrayList<>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;
        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;
            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;
            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }
        return poly;
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mGoogleApiClient.connect();
    }

    public void onClick(View v) {
        Object dataTransfer[] = new Object[2];
        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();

        switch (v.getId()) {
            case R.id.B_search: {
                if(latLngList.size() > 0){
                    refreshMap(mMap);
                    latLngList.clear();
                }
                EditText tf_location = (EditText) findViewById(R.id.TF_location);
                String location = tf_location.getText().toString();
                List<Address> addressList = null;
                MarkerOptions mo = new MarkerOptions();
                Log.d("location = ", location);

                if (!location.equals("")) {
                    Geocoder geocoder = new Geocoder(this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 5);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    for (int i = 0; i < addressList.size(); i++) {
                        Address myAddress = addressList.get(i);
                        LatLng latLng = new LatLng(myAddress.getLatitude(), myAddress.getLongitude());
                        mo.position(latLng);
                        mo.title("Your search result");
                        mMap.addMarker(mo);
                        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                        System.out.println(latLng+"-"+myLocation);
                        latLngList.add(latLng);
                        getDirection(String.valueOf(myLocation.latitude)+","+String.valueOf(myLocation.longitude),String.valueOf(latLng.latitude)+","+String.valueOf(latLng.longitude));
                    }
                }
            }
            break;
            case R.id.B_to:

//                dataTransfer = new Object[3];
//                url = getDirectionsUrl();
//                GetDirectionsData getDirectionsData = new GetDirectionsData();
//                dataTransfer[0] = mMap;
//                dataTransfer[1] = url;
//                dataTransfer[2] = new LatLng(end_latitude, end_longitude);
//                getDirectionsData.execute(dataTransfer);
                break;
        }
    }

        private String getDirectionsUrl(){
            StringBuilder googleDirectionsUrl = new StringBuilder("https://maps.googleapis.com/maps/api/directions/json?");
            googleDirectionsUrl.append("origin="+latitude+","+longitude);
            googleDirectionsUrl.append("&destination="+end_latitude+","+end_longitude);
            googleDirectionsUrl.append("&key="+"AIzaSyC24ixUxbAPPauZT5TJyo_LgK6NSxXfFig");

            return googleDirectionsUrl.toString();
        }

        private String getUrl(double latitude, double longitude, String nearbyPlaces){
            StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/");
            googlePlaceUrl.append("location"+latitude+","+longitude);
            googlePlaceUrl.append("&radius"+PROXIMITY_RADIUS);
            googlePlaceUrl.append("&type"+nearbyPlaces);
            googlePlaceUrl.append("&sensor=true");
            googlePlaceUrl.append("&key="+"AIzaSyDU8v870M58jbytAvhQVLbxCiKK8gIRxk4");

            return  googlePlaceUrl.toString();
        }

        @Override
        public void onConnected (@Nullable Bundle bundle){
            locationRequest = new LocationRequest();

            locationRequest.setInterval(1000);
            locationRequest.setFastestInterval(1000);
            locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, this);
            }
        }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            }
            return false;
        } else
            return true;
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;

        if (currentLocationMarker != null) {
            currentLocationMarker.remove();
        }

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());


        marker = new MarkerOptions();
        marker.position(latLng);
        marker.title("Current Location");
        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        myLocation = marker.getPosition();

        currentLocationMarker = mMap.addMarker(marker);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(10));

        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission is granted
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                        ;
                    {
                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
                }
                return;
        }
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.setDraggable(true);
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        end_latitude = marker.getPosition().latitude;
        end_longitude = marker.getPosition().longitude;
    }
}
