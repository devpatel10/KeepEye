package com.example.keepeye;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavType;

import com.example.keepeye.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {

    private EditText Name, Username,Password,Phone;
    private RadioGroup Group;
    private Button BnRegister;
    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // return inflater.inflate(R.layout.fragment_register, container, false);
       // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_register, container, false);
        Name = view.findViewById(R.id.nameEditText);
        Username=view.findViewById(R.id.usernameEditText);
        Password=view.findViewById(R.id.passwordEditText);
        Phone=view.findViewById(R.id.phoneEditText);
        BnRegister=view.findViewById(R.id.registerBtn);
        Group=view.findViewById(R.id.group);

        Group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                 RadioButton Type=(RadioButton)group.findViewById(checkedId);
            }
        });
        BnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegistration();
            }
        });

        return view;
    }
    public void performRegistration(){
        String name = Name.getText().toString();
        String phone=Phone.getText().toString();
        String username=Username.getText().toString();
        String password=Password.getText().toString();

        RadioButton type=(RadioButton)Group.findViewById(Group.getCheckedRadioButtonId());
      //  MainActivity.prefConfig.displayToast(type.getText().toString());

        Call<User> call=MainActivity.apiInterface.performRegistration(name,phone,username,password,type.getText().toString());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getResponse().equals("ok")) {
                    MainActivity.prefConfig.displayToast("Registration Success...");
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    getActivity().startActivity(intent);
                } else if (response.body().getResponse().equals("exist")) {
                    MainActivity.prefConfig.displayToast("User Already Exists...");
                }
                else
                {
                    MainActivity.prefConfig.displayToast("Oops Something went wrong...Try Again");
                }
            }


            @Override
            public void onFailure(Call<User> call, Throwable t) {
                MainActivity.prefConfig.displayToast("Oops Something went wrong...Try Again111");
            }
        });
        Name.setText("");
        Username.setText("");
        Password.setText("");
        Phone.setText("");

        }

    }

