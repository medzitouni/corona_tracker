package com.dsi31g4.corona_tracker;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.fragment.app.Fragment;


public class VolunteerFragment extends Fragment {

    ImageButton btnAllo ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_volunteer, container, false);

        btnAllo=v.findViewById(R.id.btncall);
        btnAllo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri admintelephon = Uri.parse("tel:20327836");
                Intent intent= new Intent(Intent.ACTION_DIAL,admintelephon);
                startActivity(intent);


            }
        });

        return v ;
    }

}