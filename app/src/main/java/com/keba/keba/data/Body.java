package com.keba.keba.data;

import java.io.Serializable;

/**
 * Created by spp on 07.11.2017.
 */

public class Body implements Serializable {

    public static final String MIME_PICTURE = "picture";
    public static final String MIME_VIDEO = "video";
    public static final String MIME_TEXT = "text";

    public String mime;
    /**
     * If type == MIME_PICTURE the body contains a link to a picture.
     * If type == MIME_VIDEO the body contains a link to a YouTube video.
     * If type == MIME_TEXT the body contains a rich text / html page.
     */
    public String content;

    @Override
    public String toString() {
        return "Body{" +
                "mime='" + mime + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
