package com.example.proiectAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.proiectAndroid.ParserJSON.ParseJson;

import java.util.Calendar;
import java.util.List;

import Models.Actions.AppointmentActions;
import Models.Entities.Appointment;
import Models.Entities.User;

public class MuzeuIstorieActivity extends AppCompatActivity {


    TimePickerDialog timePicker;
    DatePickerDialog datePicker;
    EditText editTextDate;
    EditText editTextTime;
    Button btnGet;
    private User user;
    private AppointmentActions appointmentActions;
   String obiectv = "Muzeul de Istorie";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muzeu_istorie);

        appointmentActions = new AppointmentActions(this);

        Intent it =  getIntent();
        Bundle extras =  it.getExtras();
        User user = (User) extras.get("logged_user");
        this.user = user;

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
                datePicker = new DatePickerDialog(MuzeuIstorieActivity.this,
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


                timePicker = new TimePickerDialog(MuzeuIstorieActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                editTextTime.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                timePicker.show();
            }
        });

        ParseJson parser = new ParseJson(){
            @Override
            protected void onPostExecute(List<Double> doubles) {
                try {
                    TextView viewTemp = (TextView) findViewById(R.id.textViewTemp);
                    StringBuilder builder = new StringBuilder();
                    builder.append("Temperatura: ").append(doubles.get(1)).append(" °C ");
                    viewTemp.setText(builder.toString());
                }catch  (Exception ex){
                    TextView viewTemp = (TextView) findViewById(R.id.textViewTemp);
                    viewTemp.setText(R.string.unavailable);
                }
            }
        };

        parser.execute("https://dataservice.accuweather.com/forecasts/v1/daily/1day/272938?apikey=SQe2ERcFZxrYGKxNy7YhVah0iGDKWcVP&language=ro&metric=true");
    }

    private  class InsertAppointmentAsyncTask extends AsyncTask<Void, Void, Integer> {



        private Appointment appointment;
        private AppointmentActions appointmentActions;

        public InsertAppointmentAsyncTask(Appointment appointment, AppointmentActions appointmentActions ) {

            this.appointment = appointment;
            this.appointmentActions = appointmentActions ;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            try {
                appointmentActions.insertAppointment(appointment);
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
            return 0;
        }

        @Override
        protected void onPostExecute(Integer agentsCount) {
            Toast.makeText(MuzeuIstorieActivity.this, "APPOINTMENT CREATED!", Toast.LENGTH_SHORT).show();
            EditText editTime = (EditText) findViewById(R.id.editTextTime);
            EditText editDate = (EditText) findViewById(R.id.editTextDate);
            editTime.setText("");
            editDate.setText("");
        }
    }



    public void handleCreateAppointment(View view){
        EditText editTime = (EditText) findViewById(R.id.editTextTime);
        String time = editTime.getText().toString();

        EditText editDate = (EditText) findViewById(R.id.editTextDate);
        String date = editDate.getText().toString();

        if(!time.equals("") && !date.equals("")) {

            Appointment appointment = new Appointment(date, time, user.getEmail(), obiectv);

            new InsertAppointmentAsyncTask(appointment, appointmentActions).execute();

        }else{
            Toast.makeText(this, "ALL FIELDS MUST BE COMPLETED!", Toast.LENGTH_SHORT).show();
        }

    }

    public void showLocation(View view){



        final double locationX= 44.930957083564465;
        final double locationY=25.46056833068602;
        Intent intent = new Intent(this,LocationActivity.class);
        intent.putExtra("location_x",locationX);
        intent.putExtra("location_y",locationY);
        startActivity(intent);

    }
}
