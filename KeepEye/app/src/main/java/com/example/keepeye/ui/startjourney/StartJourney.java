package com.example.keepeye.ui.startjourney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.keepeye.MainActivity;
import com.example.keepeye.MapsActivity;
import com.example.keepeye.R;
import com.example.keepeye.StartJourneyActivity;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StartJourney#} factory method to
 * create an instance of this fragment.
 */
public class StartJourney extends Fragment {
    Button btn;

    public StartJourney() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_start_journey, container, false);
        btn=view.findViewById(R.id.sjBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.prefConfig.writeJourneyStatus(true);
                Intent intent = new Intent(getActivity(), StartJourneyActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
