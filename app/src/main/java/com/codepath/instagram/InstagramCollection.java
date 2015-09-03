package com.codepath.instagram;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yahuijin on 8/31/15.
 */
public class InstagramCollection {

    public List<Instagram> instagrams;

    public InstagramCollection(JSONObject responseObject) {
        this.instagrams = this.processResponse(responseObject);
    }

    private List<Instagram> processResponse(JSONObject responseObject) {
        List<Instagram> instagrams = new ArrayList<Instagram>();

        try {
            JSONArray data = responseObject.getJSONArray("data");

            for (int i = 0; i < data.length(); i++) {
                JSONObject item = data.getJSONObject(i);

                Instagram instagram = new Instagram(item);
                instagrams.add(instagram);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return instagrams;
    }
}
