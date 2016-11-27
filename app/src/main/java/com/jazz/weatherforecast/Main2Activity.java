package com.jazz.weatherforecast;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    String place;
    String pname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         place = getIntent().getExtras().getString("name");
         pname = getIntent().getExtras().getString("pname");

        FragmentCurrent fragmentCurrent = FragmentCurrent.newInstance(place,pname);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragmentCurrent, "current")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE) // if you need transition
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_daily) {
            FragmentCurrent fragmentCurrent = FragmentCurrent.newInstance(place,pname);

            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragmentCurrent, "current")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE) // if you need transition
                    .commit();
            return true;
        }
        else if(id == R.id.action_weekly){
            FragmentWeekly fragmentWeekly = FragmentWeekly.newInstance(place,pname);

            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragmentWeekly, "weekly")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE) // if you need transition
                    .commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
