package com.example.keepeye.ui.associate;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.keepeye.MainActivity;
import com.example.keepeye.R;
import com.example.keepeye.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.keepeye.MainActivity.prefConfig;

public class AssociateFragment extends Fragment {
    private EditText Relate;
    Button btn;
    public AssociateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_associate, container, false);
        Relate=view.findViewById(R.id.associateEditText);
        btn=view.findViewById(R.id.associateBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performUpdate();
            }
        });
        return view;
    }

    private void performUpdate() {
        final String username=MainActivity.prefConfig.readUsername();
        String relate=Relate.getText().toString();
        Call<User> call=MainActivity.apiInterface.performUpdate(username,relate);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok"))
                {
                    prefConfig.displayToast("Relation Established");
                }
                else if(response.body().getResponse().equals("failed"))
                {
                    prefConfig.displayToast("Child-Child not possible");
                }
                else if(response.body().getResponse().equals("failed1"))
                {
                    prefConfig.displayToast("Parent-Parent not possible");
                }
                else if(response.body().getResponse().equals("unavailable"))
                {
                    prefConfig.displayToast("No such user exists");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                prefConfig.displayToast("Error");
            }
        });
    }

}
