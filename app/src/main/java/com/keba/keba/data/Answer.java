package com.keba.keba.data;

import java.io.Serializable;

/**
 * Created by spp on 07.11.2017.
 */

public class Answer  implements Serializable {
    public int votes;
    public String title;
    public Body body;
    public String author;
    public String time;
    public boolean isAccepted;
    public String id;
    public String langId;
}
