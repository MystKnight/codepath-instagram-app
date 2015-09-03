package com.codepath.instagram;

import android.text.Spannable;

/**
 * Created by yahuijin on 8/31/15.
 */
public class Comment {

    public String message;
    public String name;
    public String avatarUrl;
    public String date;

    public Comment(String message, String name, String avatarUrl, String date) {
        this.message = message;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.date = date;
    }

    public Spannable getSpannableComment() {
        String fullComment = String.format("%s %s", this.name, this.message);
        return Utils.getSpannable(fullComment, this.name.length());
    }

    public Spannable getSpannableName() {
        return Utils.getSpannable(this.name, this.name.length());
    }
}
