package com.jazz.weatherforecast;

/**
 * Created by Josh on 27-11-2016.
 */

public class ForecastItem {
    private String date;
    private String maxtemp_c;
    private String maxtemp_f;
    private String mintemp_c;
    private String mintemp_f;
    private String avgtemp_c;
    private String avgtemp_f;
    private String maxwind_mph;
    private String maxwind_kph;
    private String totalprecip_mm;
    private String totalprecip_in;
    private String condition;

    public void setDate(String date) {
        this.date = date;
    }

    public void setAvgtemp_c(String avgtemp_c) {
        this.avgtemp_c = avgtemp_c;
    }

    public void setAvgtemp_f(String avgtemp_f) {
        this.avgtemp_f = avgtemp_f;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setMaxtemp_c(String maxtemp_c) {
        this.maxtemp_c = maxtemp_c;
    }

    public void setMaxtemp_f(String maxtemp_f) {
        this.maxtemp_f = maxtemp_f;
    }

    public void setMaxwind_kph(String maxwind_kph) {
        this.maxwind_kph = maxwind_kph;
    }

    public void setMaxwind_mph(String maxwind_mph) {
        this.maxwind_mph = maxwind_mph;
    }

    public void setMintemp_c(String mintemp_c) {
        this.mintemp_c = mintemp_c;
    }

    public void setMintemp_f(String mintemp_f) {
        this.mintemp_f = mintemp_f;
    }

    public void setTotalprecip_in(String totalprecip_in) {
        this.totalprecip_in = totalprecip_in;
    }

    public void setTotalprecip_mm(String totalprecip_mm) {
        this.totalprecip_mm = totalprecip_mm;
    }

    public String getDate() {
        return date;
    }

    public String getAvgtemp_c() {
        return avgtemp_c;
    }

    public String getAvgtemp_f() {
        return avgtemp_f;
    }

    public String getCondition() {
        return condition;
    }

    public String getMaxtemp_c() {
        return maxtemp_c;
    }

    public String getMaxtemp_f() {
        return maxtemp_f;
    }

    public String getMaxwind_kph() {
        return maxwind_kph;
    }

    public String getMaxwind_mph() {
        return maxwind_mph;
    }

    public String getMintemp_c() {
        return mintemp_c;
    }

    public String getMintemp_f() {
        return mintemp_f;
    }

    public String getTotalprecip_in() {
        return totalprecip_in;
    }

    public String getTotalprecip_mm() {
        return totalprecip_mm;
    }
}
