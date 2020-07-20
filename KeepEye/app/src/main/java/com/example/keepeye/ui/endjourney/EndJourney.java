package com.example.keepeye.ui.endjourney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.keepeye.HomeActivity;
import com.example.keepeye.MainActivity;
import com.example.keepeye.R;
import com.example.keepeye.SpeedAnalysis;

public class EndJourney extends Fragment {
    Button ejBtn;
    public EndJourney() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_end_journey, container, false);
        ejBtn=view.findViewById(R.id.ejBtn);
        ejBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.prefConfig.writeJourneyStatus(false);
                Intent intent = new Intent(getActivity(), SpeedAnalysis.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

}
