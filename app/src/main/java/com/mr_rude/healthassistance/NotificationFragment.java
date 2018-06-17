package com.mr_rude.healthassistance;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mr_Rude on 24/04/2018.
 */

public class NotificationFragment extends Fragment {
    public NotificationFragment(){
    }

    String[] listviewTitle = new String[]{
            "From Doctor Attia"
    };


    int[] listviewImage = new int[]{
            R.drawable.doctor_image
    };

    String[] listviewShortDescription = new String[]{
            "Un rendez-vous demain aprés midi, cordialement"
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.notificationcontent,container,false);
        // Array of strings for ListView Title

        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < listviewTitle.length; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_discription", listviewShortDescription[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};

        SimpleAdapter simpleAdapter = new SimpleAdapter(rootView.getContext(), aList, R.layout.notification_list_view, from, to);
        ListView androidListView = (ListView) rootView.findViewById(R.id.list_view);
        androidListView.setAdapter(simpleAdapter);
        return rootView;
    }
}
