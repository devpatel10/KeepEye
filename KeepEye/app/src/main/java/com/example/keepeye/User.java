package com.example.keepeye;

import com.google.gson.annotations.SerializedName;

import retrofit2.Response;

public class User {
    @SerializedName("response")
    private String response;
    @SerializedName("name")
    private String name;
    public String getResponse()
    {
        return response;
    }
    public String getName()
    {
        return name;
    }
}
