package com.jazz.weatherforecast;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Josh on 27-11-2016.
 */

public class FragmentWeekly extends Fragment {
    private String key = "1df044d666454695ae8121458162711";
    View v;
    String place;
    String displayPlace;

    public static FragmentWeekly newInstance(String p1, String p2) {
        FragmentWeekly myFragment = new FragmentWeekly();
        Bundle args = new Bundle();
        args.putString("name", p1);
        args.putString("pname", p2);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_weekly_weather, container, false);
        Bundle args = getArguments();
        place = args.getString("name");
        displayPlace = args.getString("pname");
        TextView placetv = (TextView) v.findViewById(R.id.curent_place);
        placetv.setText("Location: " + displayPlace);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();
        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
        place = place.replaceAll(" ", "%20");

        StringRequest request = new StringRequest(Request.Method.GET, "http://api.apixu.com/v1/forecast.json?key="+key+"&q="+place+"&days=7",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
//                            Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
                            Log.e("weekly", "error1");
                            JSONObject jsonObject = new JSONObject(s);
                            Log.e("weekly", "error2");
                            String t = jsonObject.getString("forecast");
                            JSONObject jsonObject1 = new JSONObject(t);
                            Log.e("weekly", "error3");
                            t = jsonObject1.getString("forecastday");
                            JSONArray jsonArray = new JSONArray(t);
                            Log.e("weekly", "error4");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                Log.e("weekly", "error5");
                                ForecastItem forecastItem = new ForecastItem();
                                forecastItem.setDate(object.getString("date"));
                                t = object.getString("day");
                                JSONObject object1 = new JSONObject(t);
                                Log.e("weekly", "error6");
                                forecastItem.setMaxtemp_c(object1.getString("maxtemp_c"));
                                forecastItem.setMaxtemp_f(object1.getString("maxtemp_f"));
                                forecastItem.setMintemp_c(object1.getString("mintemp_c"));
                                forecastItem.setMintemp_f(object1.getString("mintemp_f"));
                                forecastItem.setAvgtemp_c(object1.getString("avgtemp_c"));
                                forecastItem.setAvgtemp_f(object1.getString("avgtemp_f"));
                                forecastItem.setMaxwind_mph(object1.getString("maxwind_mph"));
                                forecastItem.setMaxwind_kph(object1.getString("maxwind_kph"));
                                forecastItem.setTotalprecip_mm(object1.getString("totalprecip_mm"));
                                forecastItem.setTotalprecip_in(object1.getString("totalprecip_in"));
                                t=object1.getString("condition");
                                JSONObject jsonObject2 = new JSONObject(t);
                                Log.e("weekly", "error7");
                                forecastItem.setCondition(jsonObject2.getString("text"));

                                FragmentDOW fragmentDOW = FragmentDOW.newInstance(forecastItem);

                                switch (i) {
                                    case 0:
                                        getFragmentManager()
                                                .beginTransaction()
                                                .replace(R.id.container1, fragmentDOW, "current")
                                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE) // if you need transition
                                                .commit();
                                        break;
                                    case 1:
                                        getFragmentManager()
                                                .beginTransaction()
                                                .replace(R.id.container2, fragmentDOW, "current")
                                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE) // if you need transition
                                                .commit();
                                        break;
                                    case 2:
                                        getFragmentManager()
                                                .beginTransaction()
                                                .replace(R.id.container3, fragmentDOW, "current")
                                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE) // if you need transition
                                                .commit();
                                        break;
                                    case 3:
                                        getFragmentManager()
                                                .beginTransaction()
                                                .replace(R.id.container4, fragmentDOW, "current")
                                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE) // if you need transition
                                                .commit();
                                        break;
                                    case 4:
                                        getFragmentManager()
                                                .beginTransaction()
                                                .replace(R.id.container5, fragmentDOW, "current")
                                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE) // if you need transition
                                                .commit();
                                        break;
                                    case 5:
                                        getFragmentManager()
                                                .beginTransaction()
                                                .replace(R.id.container6, fragmentDOW, "current")
                                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE) // if you need transition
                                                .commit();
                                        break;
                                    case 6:
                                        getFragmentManager()
                                                .beginTransaction()
                                                .replace(R.id.container7, fragmentDOW, "current")
                                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE) // if you need transition
                                                .commit();
                                        break;

                                }

                            }


                        } catch (JSONException e) {
                            Log.e("weekly", "error");
                            e.printStackTrace();
                        }
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                pDialog.hide();
                Toast.makeText(getActivity(), "Please check your network connection", Toast.LENGTH_LONG).show();
            }
        }) {
            /*@Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", key);
                params.put("q",mplace);
                return params;
            }*/
        };
        requestQueue.add(request);
    }


}
