package com.example.suyog.locationtracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by SUYOG on 9/8/2017.
 */

public class Register extends Fragment {
    private EditText registerEmail;
    private EditText registerPassword;
    private Button registerbtn;
    private TextView already;
    FirebaseAuth auth;
    ProgressDialog progressDialog;
    DatabaseReference userReference;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view =inflater.inflate(R.layout.register,container,false);
        auth=FirebaseAuth.getInstance();
        //userReference= FirebaseDatabase.getInstance().getReference("appusers");
        progressDialog=new ProgressDialog(getActivity());
        registerEmail=(EditText) view.findViewById(R.id.registerEmail);
        registerPassword=(EditText) view.findViewById(R.id.registerPassword);
        registerbtn=(Button) view.findViewById(R.id.registerbtn);
        already=(TextView) view.findViewById(R.id.linksignin);

        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).setActionBarTitle("Login");
                ((MainActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,new Login())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();

            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=registerEmail.getText().toString().trim();
                String pass=registerPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(getActivity() , "Enter Valid Email" ,Toast.LENGTH_LONG ).show();
                    return;
                }

                if(TextUtils.isEmpty(pass)) {
                    Toast.makeText(getActivity() , "Enter Valid password" ,Toast.LENGTH_LONG ).show();
                    return;
                }

                progressDialog.setMessage("Registering User...");
                progressDialog.show();

                auth.createUserWithEmailAndPassword(email,pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Users user=new Users("suyog","9820641815","Maha","india");
                                userReference=FirebaseDatabase.getInstance().getReference();
                                userReference.child("appusers").child(auth.getCurrentUser().getUid()).setValue(user);
                                Intent intent=new Intent(getActivity(),AddRemainder.class);
                                getActivity().startActivity(intent);
                                getActivity().finish();
                            }
                            else{
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(),"Registration failed",Toast.LENGTH_LONG);

                            }
                        }
                    });
            }
        });





        return view;
    }
}
