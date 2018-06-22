package com.mr_rude.healthassistance;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.mr_rude.healthassistance.Data.DataBaseHelper;
import com.mr_rude.healthassistance.Data.DatabaseContract;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by Mr_Rude on 18/06/2018.
 */

public class StatisticsFragment extends Fragment {

    public ArrayList<StatisticsAnalyses> statisticsAnalyses = new ArrayList<>();

    List<PointValue> keton_values = new ArrayList<PointValue>();
    List<PointValue> temperature_values = new ArrayList<PointValue>();
    List<PointValue> humidity_values = new ArrayList<PointValue>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.statisticschart,container,false);
        getDataFromDataBase(rootView.getContext());
        LineChartView keton_chart = rootView.findViewById(R.id.ketone_chart);
        LineChartView temperature_chart = rootView.findViewById(R.id.temperature_chart);
        LineChartView humidity_chart = rootView.findViewById(R.id.humidity_chart);

        keton_values.clear();
        temperature_values.clear();
        humidity_values.clear();
        try {
            if (statisticsAnalyses.size()>0){
                for (StatisticsAnalyses analyse:
                     statisticsAnalyses) {
                     keton_values.add(new PointValue(Float.parseFloat(analyse.getDay().replace("-","")),
                            Float.parseFloat(analyse.getKetone())));
                    Log.d("TEST", "onCreateView: " + analyse.getKetone());
                    temperature_values.add(new PointValue(Float.parseFloat(analyse.getDay().replace("-","")),
                            Float.parseFloat(analyse.getTemperature())));
                    humidity_values.add(new PointValue(Float.parseFloat(analyse.getDay().replace("-","")),
                            Float.parseFloat(analyse.getHumidity())));
                }

                Line keton_line = new Line(keton_values).setColor(Color.parseColor("#4FC3F7")).setFilled(true).setHasLabels(true);
                List<Line> keton_lines = new ArrayList<Line>();
                keton_lines.add(keton_line);

                LineChartData keton_data = new LineChartData();
                keton_data.setLines(keton_lines);
                keton_chart.setLineChartData(keton_data);

                Line temperature_line = new Line(temperature_values).setColor(Color.parseColor("#29B6F6")).setFilled(true).setHasLabels(true);
                List<Line> temperature_lines = new ArrayList<Line>();
                temperature_lines.add(temperature_line);

                LineChartData temperature_data = new LineChartData();
                temperature_data.setLines(temperature_lines);
                temperature_chart.setLineChartData(temperature_data);

                Line humidity_line = new Line(humidity_values).setColor(Color.parseColor("#03A9F4")).setFilled(true).setHasLabels(true);
                List<Line> humidity_lines = new ArrayList<Line>();
                humidity_lines.add(humidity_line);

                LineChartData humidity_data = new LineChartData();
                humidity_data.setLines(humidity_lines);
                humidity_chart.setLineChartData(humidity_data);
            }
        }catch (Exception e){
            Log.d("Statistic Chart", "onCreateView: ParseError");
        }

        return rootView;
    }


    public void getDataFromDataBase(Context context){
        statisticsAnalyses.clear();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        String[] projection = {
                DatabaseContract.HistoriqueEntry._ID,
                DatabaseContract.HistoriqueEntry.ID_OBJ,
                DatabaseContract.HistoriqueEntry.KETONE,
                DatabaseContract.HistoriqueEntry.TEMPERATURE,
                DatabaseContract.HistoriqueEntry.HUMIDITY,
                DatabaseContract.HistoriqueEntry.DATE,
                DatabaseContract.HistoriqueEntry.TIME
        };
        String selection = DatabaseContract.HistoriqueEntry.ID_OBJ + " = ?";
        String selectionArgs[] = {""+MainActivity.idObj};
        Cursor cursor = db.query(DatabaseContract.HistoriqueEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,null,null);        while (cursor.moveToNext()){
            statisticsAnalyses.add(new StatisticsAnalyses(
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            ));
        }
        db.close();
    }
}
