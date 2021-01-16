package com.example.proiectAndroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.MessagePattern;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import java.util.List;

import Models.Actions.AppointmentActions;
import Models.Entities.Appointment;
import Models.Entities.User;

public class ChartActivity extends AppCompatActivity {

    int countMuzeu = 0,countTurn = 0, countCasa = 0, countManastire = 0;
    public void drawGraph (List<Appointment> listaAppointments){
        System.out.println(listaAppointments);


        for(Appointment appointment : listaAppointments){
            if(appointment.obiectiv.equals("Turnul Chindiei") ){
                countTurn ++;
            }

            if(appointment.obiectiv.equals("Casa Memoriala")){
                countCasa ++;
            }

            if(appointment.obiectiv.equals("Manastirea Dealu")){
                countManastire ++;
            }

            if(appointment.obiectiv.equals("Muzeul de Istorie")){
                countMuzeu ++;
            }


        }



        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Muzeu", countMuzeu));
        data.add(new ValueDataEntry("Turn", countTurn));
        data.add(new ValueDataEntry("Casa", countCasa));
        data.add(new ValueDataEntry("Manastire ", countManastire));


        Column column = cartesian.column(data);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("Nr{%Value}{groupsSeparator: }");

        cartesian.animation(true);
        cartesian.title("Top objectives chosen by tourists");

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("Nr{%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Objective");
        cartesian.yAxis(0).title("Number of visitors");

        anyChartView.setChart(cartesian);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        FirebaseDatabase rootNode = FirebaseDatabase.getInstance("https://proiectdam-2b128-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference reference = rootNode.getReference("Users");

        final List<List<String>> emailList = new ArrayList<List<String>>();
        emailList.add(new ArrayList<String>());


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    String userkey  = dsp.getKey();
                    String user = dsp.child("email").getValue().toString();
                    emailList.get(0).add(user);
                }


                AppointmentActions appointmentActions = new AppointmentActions(getApplicationContext());



                for(String email : emailList.get(0)){

                    final List<Appointment>[] listaAppointments = new ArrayList[]{
                            new ArrayList<Appointment>()
                    };

                    AsyncTask asyncTask =  new AsyncTask<Void,Void, List<Appointment>>(){

                        @Override
                        protected List<Appointment> doInBackground(Void... voids) {
                            return appointmentActions.getAppointmentsByEmail(email);
                        }

                        @Override
                        protected void onPostExecute(List<Appointment> appointments) {
                            listaAppointments[0] = (ArrayList<Appointment>) appointments;
                            System.out.println(listaAppointments[0]);
                            drawGraph(listaAppointments[0]);
                        }
                    }.execute();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });








//        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
//        anyChartView.setProgressBar(findViewById(R.id.progress_bar));
//
//        Cartesian cartesian = AnyChart.column();
//
//        List<DataEntry> data = new ArrayList<>();
//        data.add(new ValueDataEntry("Muzeu", 10));
//        data.add(new ValueDataEntry("Turn", 15));
//        data.add(new ValueDataEntry("Casa", 5));
//        data.add(new ValueDataEntry("Manastire ", 20));
//
//
//        Column column = cartesian.column(data);
//
//        column.tooltip()
//                .titleFormat("{%X}")
//                .position(Position.CENTER_BOTTOM)
//                .anchor(Anchor.CENTER_BOTTOM)
//                .offsetX(0d)
//                .offsetY(5d)
//                .format("Nr{%Value}{groupsSeparator: }");
//
//        cartesian.animation(true);
//        cartesian.title("Top objectives chosen by tourists");
//
//        cartesian.yScale().minimum(0d);
//
//        cartesian.yAxis(0).labels().format("Nr{%Value}{groupsSeparator: }");
//
//        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
//        cartesian.interactivity().hoverMode(HoverMode.BY_X);
//
//        cartesian.xAxis(0).title("Objective");
//        cartesian.yAxis(0).title("Number of visitors");
//
//        anyChartView.setChart(cartesian);

    }
}