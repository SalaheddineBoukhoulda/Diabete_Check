package com.mr_rude.healthassistance;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mr_rude.healthassistance.Data.DataBaseHelper;
import com.mr_rude.healthassistance.Data.DatabaseContract;

import java.util.ArrayList;

/**
 * Created by Mr_Rude on 24/04/2018.
 */

public class StatisticsFragment extends Fragment {
    public StatisticsFragment(){

    }
    public ArrayList<String> ketones = new ArrayList<>();
    public ArrayList<String> temperatures = new ArrayList<>();
    public ArrayList<String> humidities = new ArrayList<>();
    public ArrayList<String> days = new ArrayList<>();
    public ArrayList<String> dates = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentcontent,container,false);
        TextView txt = (TextView) rootView.findViewById(R.id.frame_text);
        txt.setText("Statistics");
        getDataFromDataBase(rootView.getContext());
        return rootView;
    }


    public void getDataFromDataBase(Context context){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        String[] projection = {
                DatabaseContract.HistoriqueEntry._ID,
                DatabaseContract.HistoriqueEntry.KETONE,
                DatabaseContract.HistoriqueEntry.TEMPERATURE,
                DatabaseContract.HistoriqueEntry.HUMIDITY,
                DatabaseContract.HistoriqueEntry.DATE,
                DatabaseContract.HistoriqueEntry.TIME
        };
        Cursor cursor = db.query(DatabaseContract.HistoriqueEntry.TABLE_NAME,projection,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            ketones.add(cursor.getString(1));
            temperatures.add(cursor.getString(2));
            humidities.add(cursor.getString(3));
            days.add(cursor.getString(4));
            dates.add(cursor.getString(5));
        }
    }
}
