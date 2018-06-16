package com.mr_rude.healthassistance;

import android.app.FragmentManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.mr_rude.healthassistance.Data.DataBaseHelper;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FlowingDrawer mDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: started");


        // We Initialize the fragments
        final DashboardFragment dashboardFragment = new DashboardFragment();
        final NotificationFragment notificationFragment = new NotificationFragment();
        final StatisticsFragment statisticsFragment = new StatisticsFragment();
        final OptionsFragment optionsFragment = new OptionsFragment();

        //Set the Dashboardfragment as the starting one
        final FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.main_content, dashboardFragment).commit();

        //Initialize the drawer
        mDrawer = findViewById(R.id.drawerlayout);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_FULLSCREEN);
        //Set listeners for navigationView items and set the dashboardItem checked
        NavigationView navigationView = findViewById(R.id.nav_view);
        MenuItem dashboardItem = (MenuItem) navigationView.getMenu().findItem(R.id.nav_dashboard);
        dashboardItem.setChecked(true);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        switch (menuItem.getItemId()){
                            case R.id.nav_dashboard:
                                fragmentManager.beginTransaction().replace(R.id.main_content,dashboardFragment).commit();
                                Log.d(TAG, "onNavigationItemSelected: Dashboard");
                                break;
                            case R.id.nav_notifications:
                                fragmentManager.beginTransaction().replace(R.id.main_content,notificationFragment).commit();
                                Log.d(TAG, "onNavigationItemSelected: Notifications");
                                break;
                            case R.id.nav_statistics:
                                fragmentManager.beginTransaction().replace(R.id.main_content,statisticsFragment).commit();
                                Log.d(TAG, "onNavigationItemSelected: Statistics");
                                break;
                            case R.id.nav_Options:
                                fragmentManager.beginTransaction().replace(R.id.main_content,optionsFragment).commit();
                                Log.d(TAG, "onNavigationItemSelected: Options");
                                break;
                            default:break;
                        }
                        // close drawer when item is tapped
                        mDrawer.closeMenu(true);

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
    }

}
