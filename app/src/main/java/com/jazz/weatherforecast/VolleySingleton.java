package com.jazz.weatherforecast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Josh on 09-11-2016.
 */

public class VolleySingleton {
    private static  VolleySingleton mInstance=null;
    private RequestQueue requestQueue;
    private VolleySingleton(){
        requestQueue= Volley.newRequestQueue(MyApp.getAppContext());
    }

    public static  VolleySingleton getInstance(){
        if(mInstance==null)
        {
            mInstance=new VolleySingleton();
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }
}
