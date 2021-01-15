package com.example.proiectAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;

import Models.Entities.User;

public class IntroActivity extends AppCompatActivity {
    // TODO:  Utilizarea fișierelor de preferințe pentru salvarea a cel puțin trei setări ale aplicației; (1 p.) DE TERMINAT
    // TODO:  Stilizarea aplicației mobile – pentru minim trei proprietăți ale componentelor vizuale -- (se creează o temă nouă în fișierul styles.xml sau stil nou); (0.5 p)
    // TODO: Rezolvat google maps
    // TODO:  Utilizarea bazelor de date la distanță (Firebase) (salvare/restaurare); Afișarea informațiilor din
    //Firebase să se realizeze prin intermediul componentelor vizuale (se pot folosi activitățile anterioare); (0.75 p.)
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