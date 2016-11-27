package com.jazz.weatherforecast;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

public class FragmentCurrent extends Fragment {

    private String key = "1df044d666454695ae8121458162711";
    View v;
    String place;

    public static FragmentCurrent newInstance(String pl) {
        FragmentCurrent myFragment = new FragmentCurrent();

        Bundle args = new Bundle();
        args.putString("name",pl);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_current_weather, container, false);
        Bundle args = getArguments();
        place = args.getString("name");
        TextView placetv= (TextView) v.findViewById(R.id.curent_place);
        placetv.setText("Location: "+place);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final TextView temp_c = (TextView) v.findViewById(R.id.current_temp_c);
        final TextView temp_f = (TextView) v.findViewById(R.id.current_temp_f);
        final TextView condition = (TextView) v.findViewById(R.id.current_condition);
        final TextView wind_mph = (TextView) v.findViewById(R.id.current_wind_mph);
        final TextView wind_kph = (TextView) v.findViewById(R.id.current_wind_kph);
        final TextView wind_degree = (TextView) v.findViewById(R.id.current_wind_degree);
        final TextView wind_dir = (TextView) v.findViewById(R.id.current_wind_dir);
        final TextView pressure_mb = (TextView) v.findViewById(R.id.current_pressure_mb);
        final TextView pressure_in = (TextView) v.findViewById(R.id.current_pressure_in);
        final TextView precip_mm = (TextView) v.findViewById(R.id.current_precip_mm);
        final TextView percip_in = (TextView) v.findViewById(R.id.current_precip_in);
        final TextView humidity = (TextView) v.findViewById(R.id.current_humidity);
        final TextView cloud = (TextView) v.findViewById(R.id.current_cloud);
        final TextView feelslike_c = (TextView) v.findViewById(R.id.current_feelslike_c);
        final TextView feelslike_f = (TextView) v.findViewById(R.id.current_feelslike_f);



        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();
        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
        place = place.replaceAll(" ", "%20");

        StringRequest request = new StringRequest(Request.Method.GET, "http://api.apixu.com/v1/current.json?key="+key+"&q="+place,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
//                            Log.e("response",s);
                            JSONObject jsonObject = new JSONObject(s);
                            String t = jsonObject.getString("current");
                            JSONObject jsonObject1 = new JSONObject(t);
//                            Log.e("response",jsonObject1.getString("precip_in"));

                            temp_c.setText(jsonObject1.getString("temp_c"));
                            temp_f.setText(jsonObject1.getString("temp_f"));
                            t = jsonObject1.getString("condition");
                            JSONObject jsonObject2 = new JSONObject(t);
                            condition.setText(jsonObject2.getString("text"));
                            wind_mph.setText(jsonObject1.getString("wind_mph"));
                            wind_kph.setText(jsonObject1.getString("wind_kph"));
                            wind_degree.setText(jsonObject1.getString("wind_degree"));
                            wind_dir.setText(jsonObject1.getString("wind_dir"));
                            pressure_mb.setText(jsonObject1.getString("pressure_mb"));
                            pressure_in.setText(jsonObject1.getString("pressure_in"));
                            precip_mm.setText(jsonObject1.getString("precip_mm"));
                            percip_in.setText(jsonObject1.getString("precip_in"));
                            humidity.setText(jsonObject1.getString("humidity"));
                            cloud.setText(jsonObject1.getString("cloud"));
                            feelslike_c.setText(jsonObject1.getString("feelslike_c"));
                            feelslike_f.setText(jsonObject1.getString("feelslike_f"));
                        } catch (JSONException e) {
                            Log.e("e1", "error");
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
