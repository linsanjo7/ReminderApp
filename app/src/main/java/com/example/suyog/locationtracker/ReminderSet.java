package com.example.suyog.locationtracker;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hardik on 11-Sep-17.
 */

public class ReminderSet
{
    private List<Reminder> mReminders;
    private static ReminderSet sReminderSet;
    public static ReminderSet get(Context context) {
        if (sReminderSet== null) {
            sReminderSet = new ReminderSet(context);
        }
        return sReminderSet;
    }
    private ReminderSet(Context context)
    {
        mReminders = new ArrayList<>();
    }

    public List<Reminder> getReminders() {
        return mReminders;
    }
    public Reminder getReminder(String key) {
        for (Reminder crime : mReminders) {
            if (crime.getKey().equals(key)) {
                return crime;
            }
        }
        return null;
    }

}
