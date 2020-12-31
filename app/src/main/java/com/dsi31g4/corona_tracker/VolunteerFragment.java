package com.dsi31g4.corona_tracker;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.URL;


public class VolunteerFragment extends Fragment {

    ImageButton btnAllo ;
    Button btnSend ;
    EditText editText ;
    String  url="https://corona-tracker-b82d0-default-rtdb.firebaseio.com/path/to/data" ;
    DatabaseReference ref ;
 VolunteerMessage message ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_volunteer, container, false);

        btnAllo=v.findViewById(R.id.btnAllo);
        btnAllo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri admintelephon = Uri.parse("tel:20327836");
                Intent intent= new Intent(Intent.ACTION_DIAL,admintelephon);
                startActivity(intent);


            }
        });

        editText=v.findViewById(R.id.Message) ;
        btnSend=v.findViewById(R.id.btnSend) ;
        message=new VolunteerMessage() ;

         ref=FirebaseDatabase.getInstance().getReferenceFromUrl(url);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          message.setMessage(editText.getText().toString().trim());
            ref.push().setValue(message);

                Toast.makeText(getActivity().getApplicationContext(),"Your Message has been send",Toast.LENGTH_SHORT).show();
            }
        });




        return v ;
    }

}