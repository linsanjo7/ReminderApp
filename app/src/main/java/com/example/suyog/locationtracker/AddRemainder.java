package com.example.suyog.locationtracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.app.Activity.RESULT_OK;

public class AddRemainder extends Fragment {

    TextView placename;
    TextView placeadd;
    EditText remainderName;
    EditText remainderDate;
    EditText remainderTime;
    Button setRemainder;
    Button getLocation;
    Button logout;
    double logitude = 0, latitude = 0;
    DatabaseReference remaindersDatabase;
    private static final int PLACE_PICKER_REQUEST = 1;
    FirebaseAuth auth;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.activity_add_remainder,container,false);


        auth=FirebaseAuth.getInstance();
        remaindersDatabase = FirebaseDatabase.getInstance().getReference();
        progressDialog=new ProgressDialog(getActivity());
        placeadd=(TextView) view.findViewById(R.id.placeadd);
        placename=(TextView) view.findViewById(R.id.placename);
        remainderName=(EditText) view.findViewById(R.id.remainderName);
        remainderDate=(EditText) view.findViewById(R.id.remainderDate);
        remainderTime=(EditText) view.findViewById(R.id.remainderTime);
        setRemainder=(Button) view.findViewById(R.id.setRemainder);
        getLocation=(Button) view.findViewById(R.id.getadd);
        logout=(Button) view.findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Signing Out");
                auth.signOut();
                getActivity().finish();
                Intent intent=new Intent((ReminderActivity)getActivity() ,MainActivity.class);
                getActivity().startActivity(intent);
                //setActionBarTitle("Login");
            }
        });




        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    Intent intent = builder.build(getActivity());
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                    Log.d("placepicker:","myerror1");
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }


        });


        setRemainder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                addRemainder();

            }
        });



    return  view;
    }

    private void addRemainder() {
        String rname=remainderName.getText().toString().trim();
        String rdate=remainderDate.getText().toString().trim();
        String rtime=remainderTime.getText().toString().trim();
        String pname=placename.getText().toString().trim();
        String padd=placeadd.getText().toString().trim();

/*
       String pname="jshjdhsdjhs";
        String padd="jdhfjdhffjfdhfjfhfjsfjhdjsfsjfdbdfjshjfhfdjfhjhsjhfjsjhjfs mdffjshfsj";
*/
        if(!TextUtils.isEmpty(rname) && !TextUtils.isEmpty(pname) && !TextUtils.isEmpty(padd)) {

            String id=remaindersDatabase.push().getKey();
            Reminder reminder = new Reminder(id,rname,rdate,rtime,pname,padd,logitude,latitude);

            remaindersDatabase.child("appusers").child(auth.getCurrentUser().getUid()).child("reminder").child(id).setValue(reminder);
        }
        else{
            Toast.makeText(getActivity(), "Enter valid Details", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(getActivity(), data);
                placename.setText(place.getName());
                placeadd.setText(place.getAddress());
                logitude = place.getLatLng().longitude;
                latitude = place.getLatLng().latitude;


            }
        }


    }


}