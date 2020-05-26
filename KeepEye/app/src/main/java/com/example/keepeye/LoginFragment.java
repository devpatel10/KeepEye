package com.example.keepeye;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.keepeye.MainActivity.prefConfig;

public class LoginFragment extends Fragment {

    private TextView RegText;
    private EditText Username,Password;
    private Button LoginBn;

    OnLoginFormActivityListener loginFormActivityListener;
    public interface OnLoginFormActivityListener
    {
        public void performRegister();
        public void performLogIn(String name,String username);
    }
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        RegText=view.findViewById(R.id.registerTextView);
        Username=view.findViewById(R.id.usernameEditText);
        Password=view.findViewById(R.id.passwordEditText);
        LoginBn=view.findViewById(R.id.loginBtn);
        LoginBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            performLogin();
            }
        });
        RegText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            loginFormActivityListener.performRegister();
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity=(Activity) context;
        loginFormActivityListener=(OnLoginFormActivityListener)activity;
    }
    private void performLogin()
    {
        final String username=Username.getText().toString();
        final String password=Password.getText().toString();

        Call<User> call=MainActivity.apiInterface.performUserLogin(username,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok"))
                {
                   MainActivity.prefConfig.writeLoginStatus(true);
                    loginFormActivityListener.performLogIn(response.body().getName(),username);
                }
                else if(response.body().getResponse().equals("failed"))
                {
                    prefConfig.displayToast("Login Failed...Please Try Again");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                prefConfig.displayToast(username+" "+password);
            }
        });
        Username.setText("");
        Password.setText("");
    }
}