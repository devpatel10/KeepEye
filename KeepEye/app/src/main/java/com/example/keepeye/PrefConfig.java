package com.example.keepeye;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class PrefConfig
{
    private SharedPreferences sharedPreferences;
    private Context context;


    public PrefConfig(Context context)
    {
        this.context=context;
        sharedPreferences= context.getSharedPreferences(context.getString(R.string.pref_file),Context.MODE_PRIVATE);

    }
    public void writeLoginStatus(boolean status)
    {
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status),status);
        editor.commit();
    }
    public boolean readLoginStatus()
    {
        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status),false);
    }
    public void writeJourneyStatus(boolean status)
    {
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_journey_status),status);
        editor.commit();
    }
    public boolean readJourneyStatus()
    {
        return sharedPreferences.getBoolean(context.getString(R.string.pref_journey_status),false);
    }
    public void writeName(String name)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_name),name);
        editor.commit();
    }
    public String readName()
    {
        return sharedPreferences.getString(context.getString(R.string.pref_name),"parent");
    }
    public void writeType(String type)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_type),type);
        editor.commit();
    }
    public String readType()
    {
        return sharedPreferences.getString(context.getString(R.string.pref_type),"parent");
    }

    public void writeUsername(String name)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_username),name);
        editor.commit();
    }
    public String readUsername()
    {
        return sharedPreferences.getString(context.getString(R.string.pref_username),"User");
    }
    public void displayToast(String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

}
