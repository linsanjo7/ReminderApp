package com.example.suyog.locationtracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Hardik on 9/9/2017.
 */

public class ReminderListFragment extends Fragment
{

    private ReminderAdapter mAdapter;
    private RecyclerView mReminderRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_reminder_list, container, false);

        mReminderRecyclerView = (RecyclerView) view.findViewById(R.id.reminder_recycler_view);
        mSwipeRefreshLayout=(SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        mReminderRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        updateUI();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateUI();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }

    private class ReminderHolder extends RecyclerView.ViewHolder
    {
        private TextView mCaptionTextView;
        private TextView mTimeTextView;
        private TextView mLocationTextView;
        private Reminder mReminder;


        public ReminderHolder(LayoutInflater inflater, ViewGroup parent)
        {
            super(inflater.inflate(R.layout.list_reminder_item, parent, false));
            mCaptionTextView = (TextView) itemView.findViewById(R.id.caption);
            mTimeTextView = (TextView) itemView.findViewById(R.id.time);
            mLocationTextView = (TextView) itemView.findViewById(R.id.location);
        }

        public void bind(Reminder r){
            mReminder = r;
            mCaptionTextView.setText(mReminder.getReminderName());
            /*Date date=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(mReminder.getReminderStartTime());
            Log.d("Only Date", String.valueOf(date.getDate())); */
            mTimeTextView.setText(mReminder.getReminderStartTime());
            mLocationTextView.setText(mReminder.getPlacename());
        }
    }

    private class ReminderAdapter extends RecyclerView.Adapter<ReminderHolder>
    {
        private List<Reminder> mReminders;

        public ReminderAdapter(List<Reminder> reminders)
        {

            mReminders = reminders;
        }

        @Override

        public ReminderHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ReminderHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ReminderHolder holder, int position) {
            Reminder reminder = mReminders.get(position);
                holder.bind(reminder);


        }

        @Override
        public int getItemCount()
        {

            return mReminders.size();
        }
    }

    private void updateUI() {
        
        ReminderSet reminderSet = ReminderSet.get(getActivity());
        List<Reminder> reminders = reminderSet.getReminders();
        mAdapter = new ReminderAdapter(reminders);
        mReminderRecyclerView.setAdapter(mAdapter);
    }

}
