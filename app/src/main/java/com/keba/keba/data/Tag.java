package com.keba.keba.data;

/**
 * Created by spp on 07.11.2017.
 */

public class Tag {
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
