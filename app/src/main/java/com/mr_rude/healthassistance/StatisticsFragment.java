package com.mr_rude.healthassistance;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Mr_Rude on 18/06/2018.
 */

public class StatisticsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.statisticscontent,container,false);

        final StatHistoryFragment statHistoryFragment = new StatHistoryFragment();
        final OptionsFragment optionsFragment = new OptionsFragment();

        final FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.stat_content, statHistoryFragment).commit();


        BottomNavigationView bottomNavigationView = (BottomNavigationView) rootView.findViewById(R.id.stat_bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.stat_historique:
                                fragmentManager.beginTransaction().replace(R.id.stat_content,statHistoryFragment).commit();
                                break;
                            case R.id.stat_chart:
                                fragmentManager.beginTransaction().replace(R.id.stat_content,optionsFragment).commit();
                                break;
                            default:break;
                        }
                        return true;
                    }
                }
        );
        return rootView;
    }
}
