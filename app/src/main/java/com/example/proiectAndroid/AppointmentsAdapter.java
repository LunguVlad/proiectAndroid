package com.example.proiectAndroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import Models.Entities.Appointment;

public class AppointmentsAdapter extends ArrayAdapter<Appointment> {
    public AppointmentsAdapter(Context context, ArrayList<Appointment> appointments) {
        super(context, 0, appointments);
//        System.out.println(appointments);
    }

    @Nullable
    @Override
    public Appointment getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Appointment appointment = getItem(position);
//        System.out.println(appointment);
//        System.out.println(convertView);
//        System.out.println(parent);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_template, parent, false);
        }
        // Lookup view for data population
        TextView tvObiectiv = (TextView) convertView.findViewById(R.id.tvObiectiv);
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);

        // Populate the data into the template view using the data object
        tvObiectiv.setText(appointment.obiectiv);
        tvDate.setText(appointment.date);
        tvTime.setText(appointment.time);
        // Return the completed view to render on screen

        System.out.println(convertView);
        return convertView;
    }


}
