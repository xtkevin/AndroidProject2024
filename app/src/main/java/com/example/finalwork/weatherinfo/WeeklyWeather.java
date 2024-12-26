package com.example.finalwork.weatherinfo;

import java.util.List;

public class WeeklyWeather {
    public String city;
    List<WeeklyWea> data;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<WeeklyWea> getData() {
        return data;
    }

    public void setData(List<WeeklyWea> data) {
        this.data = data;
    }
}
