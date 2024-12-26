package com.example.finalwork.weatherinfo;

public class WeatherHourlyCard {
    private String hourlytime;
    private int weatherIconResId;  // 天气图标的资源ID
    private String temperature;

    public WeatherHourlyCard(String hourlytime, int weatherIconResId, String temperature) {
        this.hourlytime = hourlytime;
        this.weatherIconResId = weatherIconResId;
        this.temperature = temperature;
    }

    public String getHourlytime() {
        return hourlytime;
    }

    public void setHourlytime(String hourlytime) {
        this.hourlytime = hourlytime;
    }

    public int getWeatherIconResId() {
        return weatherIconResId;
    }

    public void setWeatherIconResId(int weatherIconResId) {
        this.weatherIconResId = weatherIconResId;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
