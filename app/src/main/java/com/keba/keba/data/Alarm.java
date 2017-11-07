package com.keba.keba.data;

/**
 * Created by spp on 06.11.2017.
 */

public class Alarm {
    public String id;
    public String time;
    public String text;
    public String category;

    public Alarm() {
    }

    public Alarm(String id, String time, String text, String category) {
        this.id = id;
        this.time = time;
        this.text = text;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", text='" + text + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
