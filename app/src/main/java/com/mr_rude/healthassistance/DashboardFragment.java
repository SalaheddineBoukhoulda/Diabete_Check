package com.mr_rude.healthassistance;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.mr_rude.healthassistance.Data.DataBaseHelper;
import com.mr_rude.healthassistance.Data.DatabaseContract;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Mr_Rude on 24/04/2018.
 */

public class DashboardFragment extends Fragment {




    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;

    private Context context;
    public String results[] = new String[5];
    private boolean results_null = true;

    public TextView keton;
    public TextView temperature;
    public TextView humidity;
    public TextView day1;
    public TextView day2;
    public TextView day3;
    public TextView date1;
    public TextView date2;
    public TextView date3;

    public TextView stateText;
    CircleImageView stateImage;

    ImageView ketoneArrow;
    ImageView tempArrow;
    ImageView humidityArrow;

    private float lastKetone;
    private float lastTemp;
    private float lastHumidity;


    public DashboardFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.dashboardcontent, container, false);
        context = rootView.getContext();

        keton = rootView.findViewById(R.id.ketone_value);
        temperature = rootView.findViewById(R.id.temperature_value);
        humidity = rootView.findViewById(R.id.humidity_value);
        day1 = rootView.findViewById(R.id.day1);
        day2 = rootView.findViewById(R.id.day2);
        day3 = rootView.findViewById(R.id.day3);
        date1 = rootView.findViewById(R.id.date1);
        date2 = rootView.findViewById(R.id.date2);
        date3 = rootView.findViewById(R.id.date3);

        ketoneArrow = rootView.findViewById(R.id.ketoneArrow);
        tempArrow = rootView.findViewById(R.id.tempArrow);
        humidityArrow = rootView.findViewById(R.id.humidityArrow);

        ketoneArrow.setVisibility(View.INVISIBLE);
        tempArrow.setVisibility(View.INVISIBLE);
        humidityArrow.setVisibility(View.INVISIBLE);

        stateText = rootView.findViewById(R.id.stateText);
        stateText.setText("");
        stateImage = rootView.findViewById(R.id.stateImage);
        stateImage.setVisibility(View.INVISIBLE);

        if (!results_null) {
            keton.setText(results[0]);
            temperature.setText(results[1] + "°");
            humidity.setText(results[2] + "%");
            day1.setText(results[3]);
            day2.setText(results[3]);
            day3.setText(results[3]);
            date1.setText(results[4]);
            date2.setText(results[4]);
            date3.setText(results[4]);
            setState();
        }


        AHBottomNavigation bottomNavigation = rootView.findViewById(R.id.bottom_navigation);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Message", R.drawable.ic_email, R.color.LogoColor);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Analyse", R.drawable.launch_object, R.color.gradientColorOne);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Call", R.drawable.call_icon, R.color.colorWhite);
//        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#4FC3F7"));
//        // Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#1E88E5"));
        bottomNavigation.setInactiveColor(Color.parseColor("#FAFAFA"));
//        bottomNavigation.setForceTint(true);
        bottomNavigation.setTranslucentNavigationEnabled(true);
//        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
//        bottomNavigation.setColored(true);
//        bottomNavigation.setCurrentItem(1);
//        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));
//        bottomNavigation.setNotification("1", 3);


// Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (position == 1){
                    new AsyncLogin().execute("" + MainActivity.idObj);
                }else if (position == 2){
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"));
                    startActivity(intent);
                }else if (position == 0){
                    //Mail Intent
                }
                return true;
            }
        });
        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override public void onPositionChange(int y) {
                // Manage the new y position
            }
        });
        return rootView;
    }


    public void setState(){
        try {
            boolean badState = false;
            String rawStateText = "";
            if(Float.parseFloat(results[0])>= 7){
                rawStateText = "Critically High Ketone";
                badState = true;
            }else if(Float.parseFloat(results[0])>=3) {
                rawStateText = "High ketone insuline required   ";
                badState = true;
            }
            if(Float.parseFloat(results[1])<= 35.8f){
                badState = true;
                if (rawStateText.isEmpty()) {
                    rawStateText = "Low temperature";
                }else {
                    rawStateText += "\n" + "Low temperature";
                }
            }else if(Integer.parseInt(results[1])>= 38.1){
                badState = true;
                if (rawStateText.isEmpty()) {
                    rawStateText = "High temperature";
                }else {
                    rawStateText += "\n" + "High temperature";
                }
            }
            if(badState){
                stateText.setTextColor(Color.RED);
                stateText.setText(rawStateText);
                stateImage.setImageResource(R.drawable.bad_state);
                stateImage.setVisibility(View.VISIBLE);
            }else {
                stateText.setTextColor(Color.GREEN);
                stateText.setText("Good");
                stateImage.setImageResource(R.drawable.good_state);
                stateImage.setVisibility(View.VISIBLE);
            }

        } catch (Exception e) {
            Log.d("Dashboard", "onCreateView: ParsingError");
        }
    }

    private class AsyncLogin extends AsyncTask<String, String, String>
    {
        HttpURLConnection conn;
        URL url = null;



        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL("http://10.0.2.2/test_android/analyse.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("idobj", params[0]);
                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    // Pass data to onPostExecute method
                    return(result.toString());
                }else{

                    return("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equalsIgnoreCase("false")){

                // If username and password does not match display a error message
                Toast.makeText(getContext(), "Make sure you have a valid objID", Toast.LENGTH_LONG).show();

            } else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {

                Toast.makeText(getContext(), "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

            }else {
                results = result.split(",");
                keton.setText(results[0]);
                temperature.setText(results[1] + "°");
                humidity.setText(results[2] + "%");
                day1.setText(results[3]);
                day2.setText(results[3]);
                day3.setText(results[3]);
                date1.setText(results[4]);
                date2.setText(results[4]);
                date3.setText(results[4]);
                try {
                    if(!results_null){
                        if(lastKetone < Float.parseFloat(results[0])){
                            ketoneArrow.setImageResource(R.drawable.ic_arrow_upward_black_24dp);
                        }else if (lastKetone > Float.parseFloat(results[0])){
                            ketoneArrow.setImageResource(R.drawable.ic_arrow_downward_black_24dp);
                        }else {
                            ketoneArrow.setImageResource(R.drawable.ic_drag_handle_black_24dp);
                        }

                        if(lastTemp < Float.parseFloat(results[1])){
                            tempArrow.setImageResource(R.drawable.ic_arrow_upward_black_24dp);
                        }else if (lastTemp > Float.parseFloat(results[1])){
                            tempArrow.setImageResource(R.drawable.ic_arrow_downward_black_24dp);
                        }else {
                            tempArrow.setImageResource(R.drawable.ic_drag_handle_black_24dp);
                        }

                        if(lastHumidity < Float.parseFloat(results[2])){
                            humidityArrow.setImageResource(R.drawable.ic_arrow_upward_black_24dp);
                        }else if (lastHumidity > Float.parseFloat(results[2])){
                            humidityArrow.setImageResource(R.drawable.ic_arrow_downward_black_24dp);
                        }else {
                            humidityArrow.setImageResource(R.drawable.ic_drag_handle_black_24dp);
                        }
                        ketoneArrow.setVisibility(View.VISIBLE);
                        tempArrow.setVisibility(View.VISIBLE);
                        humidityArrow.setVisibility(View.VISIBLE);
                    }
                    lastKetone = Float.parseFloat(results[0]);
                    lastTemp = Float.parseFloat(results[1]);
                    lastHumidity = Float.parseFloat(results[2]);
                }catch (Exception e){
                    Log.d("Dashboard", "onPostExecute: ParseError");
                }
                results_null = false;
                setState();
                DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
                SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
                ContentValues contentValues = new ContentValues();
                String[] projection = {
                        DatabaseContract.HistoriqueEntry._ID,
                        DatabaseContract.HistoriqueEntry.ID_OBJ,
                        DatabaseContract.HistoriqueEntry.KETONE,
                        DatabaseContract.HistoriqueEntry.TEMPERATURE,
                        DatabaseContract.HistoriqueEntry.HUMIDITY,
                        DatabaseContract.HistoriqueEntry.DATE,
                        DatabaseContract.HistoriqueEntry.TIME
                };

                String selection = DatabaseContract.HistoriqueEntry.DATE + " = ? AND " + DatabaseContract.HistoriqueEntry.TIME + " = ?";
                String selectionArgs[] = {results[3], results[4]};

                Cursor cursor = db.query(
                        DatabaseContract.HistoriqueEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null
                );
                if (!cursor.moveToNext()) {
                    //Insert record into database
                    contentValues.put(DatabaseContract.HistoriqueEntry.ID_OBJ, ""+MainActivity.idObj);
                    contentValues.put(DatabaseContract.HistoriqueEntry.KETONE, results[0]);
                    contentValues.put(DatabaseContract.HistoriqueEntry.TEMPERATURE, results[1]);
                    contentValues.put(DatabaseContract.HistoriqueEntry.HUMIDITY, results[2]);
                    contentValues.put(DatabaseContract.HistoriqueEntry.DATE, results[3]);
                    contentValues.put(DatabaseContract.HistoriqueEntry.TIME, results[4]);
                    long rows = db.insert(DatabaseContract.HistoriqueEntry.TABLE_NAME, null, contentValues);
                }
                db.close();
            }
        }

    }
}
