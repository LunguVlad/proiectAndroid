package com.example.proiectAndroid.ParserJSON;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ParseJson extends AsyncTask<String,Void, List<Double>> {

    @Override
    protected List<Double> doInBackground(String... strings) {
        List<Double> lista=new ArrayList<>();
        try {
            //obtinem url-ul din string
            URL url = new URL(strings[0]);
            //deschidem conexiunea pe baza url-ului
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();

            //creez un inputStream
            InputStream stream=httpURLConnection.getInputStream();

            //pt inputstream cream un reader
            InputStreamReader inputStreamReader=new InputStreamReader(stream);

            BufferedReader reader=new BufferedReader(inputStreamReader);

            String linie="";
            StringBuilder builder=new StringBuilder();

            while((linie = reader.readLine())!=null){
                builder.append(linie);

            }

            String textFull=builder.toString();

            JSONObject object=new JSONObject(textFull);
            JSONArray colectie=object.getJSONArray("DailyForecasts");
            //primul obiect este ZIUA
            JSONObject primulObiect=colectie.getJSONObject(0);

            //obtinem obiectul temperatura
            JSONObject temperatura=primulObiect.getJSONObject("Temperature");
            JSONObject minim=temperatura.getJSONObject("Minimum");
            JSONObject maxim=temperatura.getJSONObject("Maximum");

            double minValue=minim.getDouble("Value");
            System.out.println(minim.toString());
            double maxValue=maxim.getDouble("Value");

            lista.add(minValue);
            lista.add(maxValue);

        }
        catch(MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
