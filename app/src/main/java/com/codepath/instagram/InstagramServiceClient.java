package com.codepath.instagram;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * Created by yahuijin on 8/31/15.
 */
public class InstagramServiceClient {

    private static final String CLIENT_ID = "21088a3aa8244b8fa5b28d4342ee94f5";
    private static final String BASE_API_URL = "https://api.instagram.com";
    private static final String API_VERSION = "v1";

    private static final String POPULAR_URL = String.format(
            "%s/%s/media/popular?client_id=%s",
            BASE_API_URL,
            API_VERSION,
            CLIENT_ID);

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void fetchPopular(JsonHttpResponseHandler handler) {
        client.get(POPULAR_URL, null, handler);
    }
}
