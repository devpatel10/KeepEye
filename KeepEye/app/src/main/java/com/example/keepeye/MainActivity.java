package com.example.keepeye;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.keepeye.ui.home.HomeFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLoginFormActivityListener {
    public static PrefConfig prefConfig;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState )
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefConfig= new PrefConfig(this);
        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);
        if(findViewById(R.id.fragment_container)!=null)
        {
            if(savedInstanceState!=null)
            {
                return ;
            }
            if(prefConfig.readLoginStatus())
            {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }
            else
            {
               // Intent intent = new Intent(this, HomeActivity.class);
               // startActivity(intent);
              getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new LoginFragment()).commit();
            }
        }
    }

    @Override
    public void performRegister() {
    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new RegisterFragment()).addToBackStack(null).commit();
    }

    @Override
    public void performLogIn(String name,String username) {
        prefConfig.writeName(name);
        prefConfig.writeUsername(username);
        final String[] type = new String[1];
        Call<User> call=MainActivity.apiInterface.getType(username);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                type[0]=response.body().getResponse();
                prefConfig.writeType(type[0]);
                prefConfig.displayToast(prefConfig.readType());
                type[0] =response.body().getResponse();
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                prefConfig.displayToast("Error");
            }
        });


        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}