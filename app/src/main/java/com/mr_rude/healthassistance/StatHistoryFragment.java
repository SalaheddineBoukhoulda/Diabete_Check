package com.mr_rude.healthassistance;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mr_rude.healthassistance.Data.DataBaseHelper;
import com.mr_rude.healthassistance.Data.DatabaseContract;

import java.util.ArrayList;

/**
 * Created by Mr_Rude on 24/04/2018.
 */

public class StatHistoryFragment extends Fragment {
    public StatHistoryFragment(){

    }

    public ArrayList<StatisticsAnalyses> statisticsAnalyses = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.statisticshistorique,container,false);
        getDataFromDataBase(rootView.getContext());
        StatisticAdapter statisticAdapter = new StatisticAdapter(rootView.getContext(),statisticsAnalyses);
        ListView listView = (ListView) rootView.findViewById(R.id.list_view_statistics);
        listView.setAdapter(statisticAdapter);
        return rootView;
    }


    public void getDataFromDataBase(Context context){
        statisticsAnalyses.clear();
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
            statisticsAnalyses.add(new StatisticsAnalyses(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
                    ));
        }
    }
}
