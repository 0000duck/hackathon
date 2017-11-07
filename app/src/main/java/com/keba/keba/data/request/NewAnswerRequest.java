package com.keba.keba.data.request;

import com.keba.keba.data.Answer;

/**
 * Created by spp on 07.11.2017.
 */

public class NewAnswerRequest {
    public String id;
    public Answer answer;

    public NewAnswerRequest(String id, Answer answer) {
        this.id = id;
        this.answer = answer;
    }
}
