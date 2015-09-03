package com.codepath.instagram;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InstagramMainActivity extends AppCompatActivity {

    List<Instagram> instagrams;
    InstagramAdapter adapter;
    ListView lv;
    SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_main);

        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.action_bar));

        swipeContainer = (SwipeRefreshLayout)findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData();
            }
        });

        this.instagrams = new ArrayList<Instagram>();
        this.adapter = new InstagramAdapter(this, this.instagrams);

        this.lv = (ListView)this.findViewById(R.id.lvPhotos);
        lv.setAdapter(adapter);

        this.lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                TextView tvViewAllComments = (TextView)view.findViewById(R.id.tvViewAllComments);
                final int pos = (int)tvViewAllComments.getTag();
                tvViewAllComments.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fm = getSupportFragmentManager();
                        InstagramCommentFragment dialogFragment = InstagramCommentFragment.newInstance("Comments", pos);
                        dialogFragment.show(fm, "fragment_comments");
                    }
                });
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeColors(Color.parseColor("#125688"));

        this.fetchData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_instagram_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void fetchData() {
        InstagramServiceClient.fetchPopular(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                InstagramCollection instagramCollection = new InstagramCollection(response);
                instagrams = instagramCollection.instagrams;

                // Clear the list view and then re-populate
                adapter.clear();
                adapter.addAll(instagramCollection.instagrams);

                // Get rid of refresh
                swipeContainer.setRefreshing(false);
            }
        });
    }
}
