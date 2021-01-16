package com.example.proiectAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Rating;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared_preferences",Context.MODE_PRIVATE);
        Float rating = sharedPreferences.getFloat(getString(R.string.rating),0);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setRating(rating);


    }

    public void savePrefrences(View view) {
        Context context = getApplicationContext();
//        SharedPreferences sharedPreferences = context.getSharedPreferences(getString(R.string.font_size),Context.MODE_PRIVATE);

        SharedPreferences sharedPreferences = context.getSharedPreferences("shared_preferences",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        EditText fontValue = (EditText) findViewById(R.id.fontValue);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        if(!fontValue.getText().toString().equals(""))
            editor.putInt(getString(R.string.font_size),Integer.parseInt(fontValue.getText().toString()));
        editor.putFloat(getString(R.string.rating),ratingBar.getRating());
        editor.apply();

    }
}