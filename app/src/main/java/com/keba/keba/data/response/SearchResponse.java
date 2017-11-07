package com.keba.keba.data.response;

import com.keba.keba.data.Information;
import com.keba.keba.data.Question;

import java.util.List;

/**
 * Created by spp on 07.11.2017.
 */

public class SearchResponse {
    public List<Question> questions;
    public List<Information> manufacturer;

    @Override
    public String toString() {
        return "SearchResponse{" +
                "questions=" + questions +
                ", manufacturer=" + manufacturer +
                '}';
    }
}
