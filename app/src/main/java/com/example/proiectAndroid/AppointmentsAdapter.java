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
        Appointment appointment = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_template, parent, false);
        }
        TextView tvObiectiv = (TextView) convertView.findViewById(R.id.tvObiectiv);
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);

        tvObiectiv.setText(appointment.obiectiv);
        tvDate.setText(appointment.date);
        tvTime.setText(appointment.time);

        return convertView;
    }


}