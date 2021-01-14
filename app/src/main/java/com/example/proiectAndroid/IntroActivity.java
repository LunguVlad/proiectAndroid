package com.example.proiectAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import Models.Entities.User;

public class IntroActivity extends AppCompatActivity {

    //Atractii:
    //1. Turnul Chindiei
    //2. Manastirea Dealu
    //3. Muzeul de istorie
    //4. Casa Memoriala Grigore Alecsandrescu

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
        Intent intent = new Intent(this, TurnulChindieiActivity.class);
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
}