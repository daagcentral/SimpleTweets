package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;


import cz.msebera.android.httpclient.Header;


public class ComposeActivity extends AppCompatActivity {

    TwitterClient client;
    Tweet tweet;
    EditText etCompose;
    Button btTweet;
    TweetAdapter tweetAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        etCompose = (EditText) findViewById(R.id.etCompose);
        btTweet = (Button) findViewById(R.id.btTweet);
        client = TwitterApp.getRestClient(this);
        btTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeTweet(etCompose);
            }
        });


    }

    public void composeTweet(View view) {

        client.sendTweet(etCompose.getText().toString(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    tweet = Tweet.fromJSON(response);

                    Intent data = new Intent();
                    data.putExtra("tweet", Parcels.wrap(tweet));
                    setResult(RESULT_OK, data);
                    finish();
                    //tweetAdapter.notifyItemInserted(tweet.);
                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("SendTweet", errorResponse.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("SendTweet", errorResponse.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("SendTweet", responseString);
            }
        });
    }


}
