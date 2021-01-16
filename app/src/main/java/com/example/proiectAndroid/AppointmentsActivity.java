package com.example.proiectAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Models.Actions.AppointmentActions;
import Models.Entities.Appointment;
import Models.Entities.User;

public class AppointmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        AppointmentActions appointmentActions = new AppointmentActions(this);

        Intent it = getIntent();
        Bundle extras = it.getExtras();
        User user = (User)extras.get("logged_user");
        String email = user.getEmail();



        final ArrayList<Appointment>[] arrayOfAppointments = new ArrayList[]{new ArrayList<Appointment>()};

        AsyncTask asyncTask =  new AsyncTask<Void,Void, List<Appointment>>(){

            @Override
            protected List<Appointment> doInBackground(Void... voids) {
                return appointmentActions.getAppointmentsByEmail(email);
            }

            @Override
            protected void onPostExecute(List<Appointment> appointments) {
                arrayOfAppointments[0] = (ArrayList<Appointment>) appointments;
                AppointmentsAdapter adapter = new AppointmentsAdapter(getApplicationContext(), arrayOfAppointments[0]);

                ListView listView = (ListView) findViewById(R.id.listViewAppointments);
                System.out.println(listView);
                listView.setAdapter(adapter);


            }
        }.execute();

    }


}