package com.example.keepeye;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.keepeye.MainActivity.prefConfig;

public class StartJourneyActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_journey2);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
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
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    if(prefConfig.readType().equals("parent"))
                    {
                        mMap.clear();
                        final String[] latlng = new String[1];
                        Call<User> call=MainActivity.apiInterface.getLatLng(prefConfig.readUsername());
                        call.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {

                                latlng[0] =response.body().getResponse().toString();
                                prefConfig.displayToast("LatLng read");

                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                prefConfig.displayToast("Error");
                            }
                        });
                        String[] Latlng = latlng[0].split(",");
                        double latitude = Double.parseDouble(Latlng[0]);
                        double longitude = Double.parseDouble(Latlng[1]);
                        LatLng latLng =new LatLng(latitude,longitude);
                        Geocoder geocoder=new Geocoder(getApplicationContext());
                        try {
                            List<Address> addressList=geocoder.getFromLocation(latitude,longitude,1);
                            String str= addressList.get(0).getAddressLine(0);
                            str+=" , "+addressList.get(0).getLocality();
                            mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,18f));

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        mMap.clear();
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        LatLng latLng =new LatLng(latitude,longitude);
                        String mLatLong = "" + latLng.latitude + ", " + latLng.longitude;

                        Call<User> call=MainActivity.apiInterface.updateLatLng(prefConfig.readUsername(),mLatLong);
                        call.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                if (response.body().getResponse().equals("ok")) {
                                    prefConfig.displayToast("Writing LatLng");
                                }
                                else if(response.body().getResponse().equals("error"))
                                {
                                    prefConfig.displayToast("Kuch Load hua hai");
                                }
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                prefConfig.displayToast("Error");
                            }
                        });
                        Geocoder geocoder=new Geocoder(getApplicationContext());
                        try {
                            List<Address> addressList=geocoder.getFromLocation(latitude,longitude,1);
                            String str= addressList.get(0).getAddressLine(0);
                            str+=" , "+addressList.get(0).getLocality();
                            mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,18f));

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

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
            });
        }
        else if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
        {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    if(prefConfig.readType()=="parent")
                    {
                        mMap.clear();
                        final String[] latlng = new String[1];
                        Call<User> call=MainActivity.apiInterface.getLatLng(prefConfig.readUsername());
                        call.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {

                                latlng[0] =response.body().getResponse();
                                prefConfig.displayToast("LatLng read");

                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                prefConfig.displayToast("Error");
                            }
                        });
                        String[] Latlng = latlng[0].split(",");
                        double latitude = Double.parseDouble(Latlng[0]);
                        double longitude = Double.parseDouble(Latlng[1]);
                        LatLng latLng =new LatLng(latitude,longitude);
                        Geocoder geocoder=new Geocoder(getApplicationContext());
                        try {
                            List<Address> addressList=geocoder.getFromLocation(latitude,longitude,1);
                            String str= addressList.get(0).getAddressLine(0);
                            str+=" , "+addressList.get(0).getLocality();
                            mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,18f));

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        mMap.clear();
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        LatLng latLng =new LatLng(latitude,longitude);
                        String mLatLong = "" + latLng.latitude + ", " + latLng.longitude;

                        Call<User> call=MainActivity.apiInterface.updateLatLng(prefConfig.readUsername(),mLatLong);
                        call.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                if (response.body().getResponse().equals("ok")) {
                                    prefConfig.displayToast("Writing LatLng");
                                }
                                else if(response.body().getResponse().equals("error"))
                                {
                                    prefConfig.displayToast("Kuch Load hua hai");
                                }
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                prefConfig.displayToast("Error");
                            }
                        });
                        Geocoder geocoder=new Geocoder(getApplicationContext());
                        try {
                            List<Address> addressList=geocoder.getFromLocation(latitude,longitude,1);
                            String str= addressList.get(0).getAddressLine(0);
                            str+=" , "+addressList.get(0).getLocality();
                            mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,18f));

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


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
                private void requestLocation(){
                    Criteria criteria=new Criteria();
                    criteria.setAccuracy(Criteria.ACCURACY_FINE);
                    criteria.setPowerRequirement(Criteria.POWER_HIGH);

                }
            });
        }


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

        // Add a marker in Sydney and move the camera
        //  LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,10.2f));
    }
}
