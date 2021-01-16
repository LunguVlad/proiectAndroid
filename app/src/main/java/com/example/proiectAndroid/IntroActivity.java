package com.example.proiectAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;

import Models.Entities.User;

public class IntroActivity extends AppCompatActivity {

    // TODO:  Stilizarea aplicației mobile – pentru minim trei proprietăți ale componentelor vizuale -- (se creează o temă nouă în fișierul styles.xml sau stil nou); (0.5 p)
    // TODO: Adaugare validari campuri



    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent it =  getIntent();
        Bundle extras =  it.getExtras();
        User user = (User) extras.get("logged_user");
        this.user = user;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

    }

    public void turnClick(View view) {
        Intent intent = new Intent(this, AppointmentsActivity.class);
        intent.putExtra("logged_user",this.user);
        startActivity(intent);
    }
    public void casaClick(View view) {
        Intent intent = new Intent(this, CasaMemorialaActivity.class);
        intent.putExtra("logged_user",this.user);
        startActivity(intent);
    }
    public void muzeuClick(View view) {
        Intent intent = new Intent(this, MuzeuIstorieActivity.class);
        intent.putExtra("logged_user",this.user);
        startActivity(intent);
    }
    public void manastireClick(View view) {
        Intent intent = new Intent(this, ManastireaDealuActivity.class);
        intent.putExtra("logged_user",this.user);
        startActivity(intent);
    }



    public void appointmentsClick(View view) {
        Intent intent = new Intent(this, AppointmentsActivity.class);
        intent.putExtra("logged_user",this.user);
        startActivity(intent);
    }

    public void chartClick(View view) {
        Intent intent = new Intent(this, ChartActivity.class);
        intent.putExtra("logged_user",this.user);
        startActivity(intent);
    }

    public void appointmentsListClick(View view) {
        Intent intent = new Intent(this, AppointmentsActivity.class);
        intent.putExtra("logged_user",this.user);
        startActivity(intent);
    }
}