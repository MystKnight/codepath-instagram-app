package com.codepath.instagram;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yahuijin on 8/31/15.
 */
public class Instagram {

    public Author author;
    public String photoUrl;
    public String date;
    public String location;
    public String message;
    public CommentCollection commentCollection;

    public int likeCount;
    public int commentCount;

    public Instagram(Author author, String photoUrl, String date, int likeCount, int commentCount) {
        this.author = author;
        this.photoUrl = photoUrl;
        this.date = date;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
    }

    public Instagram(JSONObject item) {
        // Process everything else
        this.processInstagram(item);
    }

    public String getLikeCountDisplay() {
        return String.format("%d likes", this.likeCount);
    }

    private void processInstagram(JSONObject item) {
        try {
            // Get author
            JSONObject user = item.getJSONObject("user");
            String username = user.getString("username");
            String avatarUrl = user.getString("profile_picture");

            // Get standard resolution image
            JSONObject images = item.getJSONObject("images");
            JSONObject standardResolution = images.getJSONObject("standard_resolution");
            String photoUrl = standardResolution.getString("url");

            // Get Likes
            JSONObject likes = item.getJSONObject("likes");
            int likeCount = likes.getInt("count");

            // Get Date
            String date = item.getString("created_time");

            // Get Caption
            if (!item.isNull("caption")) {
                JSONObject caption = item.getJSONObject("caption");
                String message = caption.getString("text");
                this.message = message;
            }

            // Get Location
            if (!item.isNull("location")) {
                JSONObject location = item.getJSONObject("location");
                if (location.has("name")) {
                    String locationName = location.getString("name");
                    this.location = locationName;
                }
            }

            // Get Comments
            if (!item.isNull("comments")) {
                JSONObject comments = item.getJSONObject("comments");
                this.commentCount = comments.getInt("count");
                this.commentCollection = new CommentCollection(comments);
            }

            this.photoUrl = photoUrl;
            this.date = date;
            this.likeCount = likeCount;
            this.author = new Author(username, avatarUrl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
