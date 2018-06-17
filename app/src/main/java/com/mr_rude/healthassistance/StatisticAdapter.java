package com.mr_rude.healthassistance;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mr_Rude on 17/06/2018.
 */

public class StatisticAdapter extends ArrayAdapter<StatisticsAnalyses> {
    public StatisticAdapter(Context context, ArrayList<StatisticsAnalyses> analyses){
        super(context,0,analyses);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.statistics_list_view,parent,false);
        }
        StatisticsAnalyses statisticsAnalyses = getItem(position);

        TextView ketone = (TextView) listItemView.findViewById(R.id.ketone_s);
        ketone.setText(statisticsAnalyses.getKetone());
        TextView temp = (TextView) listItemView.findViewById(R.id.temp_s);
        temp.setText(statisticsAnalyses.getTemperature());
        TextView humidity = (TextView) listItemView.findViewById(R.id.humidity_s);
        humidity.setText(statisticsAnalyses.getHumidity());
        TextView time = (TextView) listItemView.findViewById(R.id.time_s);
        time.setText(statisticsAnalyses.getDay() + "\n" + statisticsAnalyses.getDate());
        return listItemView;
    }
}
