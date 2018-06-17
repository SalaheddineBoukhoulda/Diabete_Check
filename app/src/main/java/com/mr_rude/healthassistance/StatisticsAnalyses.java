package com.mr_rude.healthassistance;

/**
 * Created by Mr_Rude on 17/06/2018.
 */

public class StatisticsAnalyses {
    private String ketone;
    private String temperature;
    private String humidity;
    private String day;
    private String date;


    public StatisticsAnalyses(String ketone, String temperature, String humidity, String day, String date) {
        this.ketone = ketone;
        this.temperature = temperature;
        this.humidity = humidity;
        this.day = day;
        this.date = date;
    }

    public String getKetone() {

        return ketone;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getDay() {
        return day;
    }

    public String getDate() {
        return date;
    }

    public void setKetone(String ketone) {
        this.ketone = ketone;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
