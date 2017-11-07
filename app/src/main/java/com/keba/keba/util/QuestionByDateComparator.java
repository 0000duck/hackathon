package com.keba.keba.util;

import com.keba.keba.data.Question;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by spp on 07.11.2017.
 */

public class QuestionByDateComparator implements Comparator<Question> {
    @Override
    public int compare(Question first, Question second) {
        Date firstDate = DateConverter.toDate(first.time);
        Date secondDate = DateConverter.toDate(second.time);

        if (firstDate.getTime() - secondDate.getTime() == 0) {
            return 0;
        } else if (firstDate.getTime() - secondDate.getTime() < 0) {
            return 1;
        } else {
            return -1;
        }
    }
}
