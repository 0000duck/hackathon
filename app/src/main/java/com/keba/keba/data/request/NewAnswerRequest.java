package com.keba.keba.data.request;

import com.keba.keba.data.Answer;

/**
 * Created by spp on 07.11.2017.
 */

public class NewAnswerRequest {
    public String qId;
    public Answer answer;

    public NewAnswerRequest(String qId, Answer answer) {
        this.qId = qId;
        this.answer = answer;
    }
}
