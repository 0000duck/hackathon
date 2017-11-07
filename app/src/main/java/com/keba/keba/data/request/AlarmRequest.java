package com.keba.keba.data.request;

import com.keba.keba.data.QR;

/**
 * Created by spp on 06.11.2017.
 */

public class AlarmRequest {
    public String langId;
    public QR qr;

    public AlarmRequest() {
    }

    public AlarmRequest(String langId, QR qr) {
        this.langId = langId;
        this.qr = qr;
    }

    @Override
    public String toString() {
        return "AlarmRequest{" +
                "langId='" + langId + '\'' +
                ", qr=" + qr +
                '}';
    }
}
