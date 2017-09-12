package com.example.suyog.locationtracker;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser() != null){

            Intent intent=new Intent(MainActivity.this,Reminder.class);
            startActivity(intent);
            finish();

        }

        getSupportFragmentManager().beginTransaction().add(R.id.container,new Login(),"Log In").commit();
        setActionBarTitle("Login");
    }

    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }
}
