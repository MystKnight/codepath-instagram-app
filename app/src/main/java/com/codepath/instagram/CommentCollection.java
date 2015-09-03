package com.codepath.instagram;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yahuijin on 8/31/15.
 */
public class CommentCollection {

    List<Comment> comments;
    int count;

    public CommentCollection(JSONObject comments) {
        try {
            this.count = comments.getInt("count");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.processComments(comments);
    }

    private List<com.codepath.instagram.Comment> processComments(JSONObject comments) {
        this.comments = new ArrayList<Comment>();

        try {
            JSONArray data = comments.getJSONArray("data");

            for (int i = 0; i < data.length(); i++) {
                JSONObject c = data.getJSONObject(i);
                JSONObject from = c.getJSONObject("from");

                String message = c.getString("text");
                String createdTime = c.getString("created_time");
                String username = from.getString("username");
                String avatarUrl = from.getString("profile_picture");
                Comment comment = new Comment(message, username, avatarUrl, createdTime);

                this.comments.add(comment);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return this.comments;
    }
}
