package com.example.proiectAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import Models.Entities.User;

public class CasaMemorialaActivity extends AppCompatActivity {


    TimePickerDialog timePicker;
    DatePickerDialog datePicker;
    EditText editTextDate;
    EditText editTextTime;
    Button btnGet;
    TextView tvw;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casa_memoriala);

        Intent it =  getIntent();
        Bundle extras =  it.getExtras();
        User user = (User) extras.get("logged_user");
        this.user = user;

        tvw = (TextView) findViewById(R.id.textViewDate);
        editTextDate = (EditText) findViewById(R.id.editTextDate);
        editTextTime = (EditText) findViewById(R.id.editTextTime);

        editTextDate.setInputType(InputType.TYPE_NULL);
        editTextTime.setInputType(InputType.TYPE_NULL);
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                datePicker = new DatePickerDialog(CasaMemorialaActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                editTextDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePicker.show();
            }
        });

        editTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);

                tvw.setText("Selected Date: " + editTextDate.getText());

                timePicker = new TimePickerDialog(CasaMemorialaActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                editTextTime.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                timePicker.show();
            }
        });
    }
}