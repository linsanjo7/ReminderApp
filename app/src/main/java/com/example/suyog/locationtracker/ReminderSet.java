package com.example.suyog.locationtracker;

import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

/**
 * Created by Hardik on 11-Sep-17.
 */

public class ReminderSet
{
    private  List<Reminder> mReminders;
    private static ReminderSet sReminderSet;
    DatabaseReference mReminderRef;
    FirebaseAuth  mAuth;
    public static ReminderSet get(Context context) {
        if (sReminderSet== null) {
            sReminderSet = new ReminderSet(context);
        }
        Log.d("in suyog: ","suyog");

        return sReminderSet;
    }

    public ReminderSet(Context context)
    {

        mReminders = new ArrayList<>();
        mAuth=FirebaseAuth.getInstance();
        mReminderRef=FirebaseDatabase.getInstance().getReference();
        mReminderRef.child("appusers").child(mAuth.getCurrentUser().getUid()).child("reminder").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot d : dataSnapshot.getChildren()){
                    Reminder reminder=d.getValue(Reminder.class);
                    Log.d("remindersuyog: ",reminder.getPlacename().toString());
                    mReminders.add(reminder);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void addReminder(Reminder r) {
        mAuth=FirebaseAuth.getInstance();
        mReminderRef=FirebaseDatabase.getInstance().getReference();
        mReminderRef.child("appusers").child(mAuth.getCurrentUser().getUid()).child("reminder").child(r.getKey()).setValue(r);
        mReminders.add(r);
    }

    public List<Reminder> getReminders() {

        Log.d("in suyoggetReminders: ","suyog");

        return mReminders;
    }

    public Reminder getReminder(String key) {
        for (Reminder reminder : mReminders) {
            if (reminder.getKey().equals(key)) {
                return reminder;
            }
        }
        return null;
    }

}
