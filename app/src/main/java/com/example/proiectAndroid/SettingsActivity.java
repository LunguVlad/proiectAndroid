package com.example.proiectAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void savePrefrences(View view) {
       Context context = getApplicationContext();
//        SharedPreferences sharedPreferences = context.getSharedPreferences(getString(R.string.font_size),Context.MODE_PRIVATE);

        SharedPreferences sharedPreferences = context.getSharedPreferences("shared_preferences",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        EditText fontValue = (EditText) findViewById(R.id.fontValue);
        editor.putInt(getString(R.string.font_size),Integer.parseInt(fontValue.getText().toString()));
        editor.apply();
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

        RadioButton radioButton = (RadioButton) findViewById(R.id.radioButton);
        if(radioButton.isChecked()){

        }
    }
}