package com.keba.keba.data;

import java.io.Serializable;

/**
 * Created by spp on 06.11.2017.
 */

public class QR  implements Serializable {
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
