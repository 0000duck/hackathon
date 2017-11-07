package com.keba.keba.data.response;

import com.keba.keba.data.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spp on 07.11.2017.
 */

public class AllResponse {
    public List<AllResponseEntry> alarms;

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        if (alarms != null) {
            for (AllResponseEntry entry : alarms) {
                if (entry.questions != null)
                    questions.addAll(entry.questions);
            }
        }
        return questions;
    }
}
