package com.keba.keba.data.request;

/**
 * Created by spp on 31.10.2017.
 */

public class SearchRequest {
    public String query;
    public int ping;
    public int pong;

    public SearchRequest(String query, int ping) {
        this.query = query;
        this.ping = ping;
    }

    @Override
    public String toString() {
        return "SearchRequest{" +
                "query='" + query + '\'' +
                ", ping=" + ping +
                ", pong=" + pong +
                '}';
    }
}
