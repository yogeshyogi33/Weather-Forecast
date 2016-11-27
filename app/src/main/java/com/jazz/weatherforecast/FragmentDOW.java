package com.jazz.weatherforecast;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Josh on 27-11-2016.
 */

public class FragmentDOW extends Fragment {
    ForecastItem fi;
    View v;

    public static FragmentDOW newInstance(ForecastItem forecastItem) {
        FragmentDOW myFragment = new FragmentDOW();
        myFragment.fi=forecastItem;
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_dow, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView date = (TextView) v.findViewById(R.id.dow_date);
        TextView maxtempc = (TextView) v.findViewById(R.id.dow_maxtempc);
        TextView maxtempf = (TextView) v.findViewById(R.id.dow_maxtempf);
        TextView mintempc = (TextView) v.findViewById(R.id.dow_mintempc);
        TextView mintempf = (TextView) v.findViewById(R.id.dow_mintempf);
        TextView avgtempc = (TextView) v.findViewById(R.id.dow_avgtempc);
        TextView avgtempf = (TextView) v.findViewById(R.id.dow_avgtempf);
        TextView dow_maxwindm = (TextView) v.findViewById(R.id.dow_maxwindm);
        TextView dow_maxwindk = (TextView) v.findViewById(R.id.dow_maxwindk);
        TextView dow_pptmm = (TextView) v.findViewById(R.id.dow_pptmm);
        TextView dow_pptin = (TextView) v.findViewById(R.id.dow_pptin);
        TextView dow_condition = (TextView) v.findViewById(R.id.dow_condition);

        date.setText(fi.getDate());
        maxtempc.setText(fi.getMaxtemp_c());
        maxtempf.setText(fi.getMaxtemp_f());
        mintempc.setText(fi.getMintemp_c());
        mintempf.setText(fi.getMintemp_f());
        avgtempc.setText(fi.getAvgtemp_c());
        avgtempf.setText(fi.getAvgtemp_f());
        dow_maxwindm.setText(fi.getMaxwind_mph());
        dow_maxwindk.setText(fi.getMaxwind_kph());
        dow_pptmm.setText(fi.getTotalprecip_mm());
        dow_pptin.setText(fi.getTotalprecip_in());
        dow_condition.setText(fi.getCondition());
    }
}
