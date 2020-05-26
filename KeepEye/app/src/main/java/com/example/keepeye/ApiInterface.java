package com.example.keepeye;


import android.widget.RadioButton;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("register.php")
    Call<User> performRegistration(@Query("name") String name, @Query("phone") String phone, @Query("username") String username, @Query("password") String password, @Query("type")  String type);

    @GET("login.php")
    Call<User> performUserLogin(@Query("username") String username, @Query("password") String password);

    @GET("associate.php")
    Call<User> performUpdate(@Query("username") String username, @Query("relate") String relate);

    @GET("dissociate.php")
    Call<User> deleteRelation(@Query("username") String username);
    @GET("getType.php")
    Call<User> getType(@Query("username") String username);
    @GET("getLatLng.php")
    Call<User> getLatLng(@Query("username") String username);
    @GET("updateLatLng.php")
    Call<User> updateLatLng(@Query("username") String username,@Query("latLng") String latLng);

}
