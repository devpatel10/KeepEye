package com.example.keepeye.ui.monitor;

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


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonitorFragment#} factory method to
 * create an instance of this fragment.
 */
public class MonitorFragment extends Fragment {
    Button btn;

    public MonitorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_monitor, container, false);
        btn=view.findViewById(R.id.monitorBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }
}
