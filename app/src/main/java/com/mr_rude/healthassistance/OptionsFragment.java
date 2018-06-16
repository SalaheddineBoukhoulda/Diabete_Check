package com.mr_rude.healthassistance;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Mr_Rude on 24/04/2018.
 */

public class OptionsFragment extends Fragment {
    public OptionsFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentcontent,container,false);
        TextView txt = (TextView) rootView.findViewById(R.id.frame_text);
        txt.setText("Settings");
        return rootView;
    }
}
