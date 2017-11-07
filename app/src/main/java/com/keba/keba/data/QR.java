package com.keba.keba.data;

/**
 * Created by spp on 06.11.2017.
 */

public class QR {
    // either be an alarm object
    public Alarm alarm;

    // or whatever else we require...

    @Override
    public String toString() {
        return "QR{" +
                "alarm=" + alarm +
                '}';
    }
}
