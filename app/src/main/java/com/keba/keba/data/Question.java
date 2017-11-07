package com.keba.keba.data;

import com.keba.keba.util.DateConverter;

import java.util.Date;
import java.util.List;

/**
 * Created by spp on 07.11.2017.
 */

public class Question {
    public String id;
    public List<Tag> tags;
    public int votes;
    public String title;
    public Body body;
    public String author;
    public String time;
    public String langId;
    public QR qr;
    public List<Answer> answers;

    public Question() {
    }

    public Question(String id, List<Tag> tags, int votes, String title, Body body, String author, Date time, String langId, QR qr) {
        this.id = id;
        this.tags = tags;
        this.votes = votes;
        this.title = title;
        this.body = body;
        this.author = author;
        this.time = DateConverter.toString(time);
        this.langId = langId;
        this.qr = qr;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", tags=" + tags +
                ", votes=" + votes +
                ", title='" + title + '\'' +
                ", body=" + body +
                ", author='" + author + '\'' +
                ", time=" + time +
                ", langId='" + langId + '\'' +
                ", qr=" + qr +
                '}';
    }
}
