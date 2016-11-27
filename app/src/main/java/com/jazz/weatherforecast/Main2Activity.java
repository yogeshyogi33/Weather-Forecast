package com.jazz.weatherforecast;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Main2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String place = getIntent().getExtras().getString("name");

        FragmentCurrent fragmentCurrent = FragmentCurrent.newInstance(place);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragmentCurrent, "current")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE) // if you need transition
                .addToBackStack("current")
                .commit();

    }

}
