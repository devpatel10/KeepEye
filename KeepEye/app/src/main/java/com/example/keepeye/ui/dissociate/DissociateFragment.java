package com.example.keepeye.ui.dissociate;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.keepeye.MainActivity;
import com.example.keepeye.R;
import com.example.keepeye.User;
import com.example.keepeye.ui.monitor.MonitorFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.keepeye.MainActivity.prefConfig;


/**
 * A simple {@link Fragment} subclass.
 *
 * create an instance of this fragment.
 */
public class DissociateFragment extends Fragment {
  Button btn;

    public DissociateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dissociate, container, false);
        btn=view.findViewById(R.id.dissociateBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRelation();
            }
        });
       return view;
    }
    private void deleteRelation() {
    String username= MainActivity.prefConfig.readUsername();
        Call<User> call=MainActivity.apiInterface.deleteRelation(username);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok"))
                {
                    prefConfig.displayToast("Deleted");
                }
                else if(response.body().getResponse().equals("no"))
                {
                    prefConfig.displayToast("Nothing to Dissociate");
                }
                else if(response.body().getResponse().equals("failed"))
                {
                    prefConfig.displayToast("Sorry you are a child");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                prefConfig.displayToast("Error");
            }
        });
    }
}
