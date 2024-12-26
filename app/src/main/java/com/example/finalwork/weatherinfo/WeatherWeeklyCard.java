package com.example.finalwork.weatherinfo;

public class WeatherWeeklyCard {
    private String WeeklyTime;
    private int WeeklyIconResId;
    private String WeeklyTemH;
    private String WeeklyTemL;
    private String WeeklyWeaDes;

    public String getWeeklyWeaDes() {
        return WeeklyWeaDes;
    }

    public void setWeeklyWeaDes(String weeklyWeaDes) {
        WeeklyWeaDes = weeklyWeaDes;
    }

    public WeatherWeeklyCard(String weeklyTime, int weeklyIconResId, String weeklyTemH, String weeklyTemL, String weeklyWeaDes) {
        WeeklyTime = weeklyTime;
        WeeklyIconResId = weeklyIconResId;
        WeeklyTemH = weeklyTemH;
        WeeklyTemL = weeklyTemL;
        WeeklyWeaDes = weeklyWeaDes;
    }

    public String getWeeklyTime() {
        return WeeklyTime;
    }

    public void setWeeklyTime(String weeklyTime) {
        WeeklyTime = weeklyTime;
    }

    public int getWeeklyIconResId() {
        return WeeklyIconResId;
    }

    public void setWeeklyIconResId(int weeklyIconResId) {
        WeeklyIconResId = weeklyIconResId;
    }

    public String getWeeklyTemH() {
        return WeeklyTemH;
    }

    public void setWeeklyTemH(String weeklyTemH) {
        WeeklyTemH = weeklyTemH;
    }

    public String getWeeklyTemL() {
        return WeeklyTemL;
    }

    public void setWeeklyTemL(String weeklyTemL) {
        WeeklyTemL = weeklyTemL;
    }
}
