package com.keba.keba.data;

import java.io.Serializable;

/**
 * Created by spp on 07.11.2017.
 */

public class Tag implements Serializable {
    public String key;

    public Tag(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "key='" + key + '\'' +
                '}';
    }
}
