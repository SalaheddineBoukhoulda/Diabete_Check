package com.mr_rude.healthassistance;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

/**
 * Created by Mr_Rude on 24/04/2018.
 */

public class DashboardFragment extends Fragment {

    public DashboardFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.dashboardcontent,container,false);
        AHBottomNavigation bottomNavigation = rootView.findViewById(R.id.bottom_navigation);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Message", R.drawable.ic_email, R.color.LogoColor);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Control", R.drawable.launch_object, R.color.gradientColorOne);
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
                Toast.makeText(rootView.getContext(),"Clicked on item " + position,Toast.LENGTH_SHORT).show();
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
}
