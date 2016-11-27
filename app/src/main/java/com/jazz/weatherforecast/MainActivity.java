package com.jazz.weatherforecast;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String key = "1df044d666454695ae8121458162711";
    private ArrayList<String> placesList = new ArrayList<>();
    private ArrayAdapter<String> listAdapter ;
    TextView place;
    ListView places_listview;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        place = (TextView) findViewById(R.id.place);
        places_listview = (ListView) findViewById(R.id.places_list);
        search = (Button) findViewById(R.id.button_search);

        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, placesList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.BLACK);
                return view;
            }
        };
        places_listview.setAdapter(listAdapter);
        places_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(), placesList.get(i), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                intent.putExtra("name", placesList.get(i).split(" ")[0]);
                intent.putExtra("pname", placesList.get(i));
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p = place.getText().toString().trim();
                PlaceRequest(p);
            }
        });
    }

    public void PlaceRequest(final String mplace) {
        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest request = new StringRequest(Request.Method.GET, "http://api.apixu.com/v1/search.json?key="+key+"&q="+mplace,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONArray jsonArray = new JSONArray(s);
                    placesList.clear();
                    if(jsonArray.length()==0)
                        Toast.makeText(getApplicationContext(),"Location not found", Toast.LENGTH_LONG).show();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        placesList.add(object.getString("name"));
                    }
                    /*Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                    Log.e("e1", s);*/
                    listAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, placesList) {
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            View view = super.getView(position, convertView, parent);
                            TextView text = (TextView) view.findViewById(android.R.id.text1);
                            text.setTextColor(Color.BLACK);
                            return view;
                        }
                    };
                    places_listview.setAdapter(listAdapter);
                    places_listview.invalidate();

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
                Toast.makeText(getApplicationContext(), "Please check your network connection", Toast.LENGTH_LONG).show();
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
