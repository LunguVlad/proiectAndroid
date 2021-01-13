package com.example.proiectAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IntroActivity extends AppCompatActivity {

    //Atractii:
    //1. Turnul Chindiei
    //2. Manastirea Dealu
    //3. Muzeul de istorie
    //4. Casa Memoriala Grigore Alecsandrescu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }

    public void turnClick(View view) {
        Intent it = new Intent(this,MuzeuActivity.class);
        startActivity(it);
    }
}