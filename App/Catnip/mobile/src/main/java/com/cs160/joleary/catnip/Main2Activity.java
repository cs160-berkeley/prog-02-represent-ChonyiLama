package com.cs160.joleary.catnip;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appdatasearch.GetRecentContextCall;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;



public class Main2Activity extends ListActivity {
    ListView listView;
    int[] move_poster_resource = {R.drawable.senator1, R.drawable.senator2, R.drawable.senator3,
    };
    String[] move_title;
    String[] move_rating;
    String[] tweets;
    String[] websites;
    String[] parties;

    double latitude;
    double longitude;
    private ArrayList<Legislator> mLegislators;
    //private ArrayList<String> mLegislatorList;
    private String mZipcode;
    private static ListAdapter mAdapter;
    private static final String TAG = "HelloTAG";
    MoveAdapter adapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            latitude = extras.getInt("LATITUDE");
            longitude = extras.getInt("LONGITUDE");
        }


        /**
         // TODO: Use a more specific parent
         final ViewGroup parentView = (ViewGroup) getWindow().getDecorView().getRootView();
         // TODO: Base this Tweet ID on some data from elsewhere in your app
         long tweetId = 631879971628183552L;
         TweetUtils.loadTweet(tweetId, new Callback<Tweet>() {
        @Override public void success(Result<Tweet> result) {
        TweetView tweetView = new TweetView(MainActivity.this, result.data);
        parentView.addView(tweetView);
        }

        @Override public void failure(TwitterException exception) {
        Log.d("TwitterKit", "Load Tweet failure", exception);
        }
        });
         **/

        listView = (ListView) findViewById(R.id.list_view);
        move_rating = getResources().getStringArray(R.array.move_rating);
        move_title = getResources().getStringArray(R.array.move_title);
        websites = getResources().getStringArray(R.array.websites);
        tweets = getResources().getStringArray(R.array.tweets);
        parties = getResources().getStringArray(R.array.parties);


        int i = 0;
        adapter = new MoveAdapter(getApplicationContext(), R.layout.second_layout);
        listView.setAdapter(adapter);
        for (String title : move_title) {
            MoveDataProvider dataProvider = new MoveDataProvider(move_poster_resource[i],
                    title, move_rating[i], websites[i],parties[i]);
            adapter.add(dataProvider);
            i++;
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), position + "is selected", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(view.getContext(), congressionalDeets.class);
                myIntent.putExtra("POSITION", position);
                startActivity(myIntent);
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


    }



    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main2 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.cs160.joleary.catnip/http/host/path")
        );
        AppIndex.AppIndexApi.start(client2, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main2 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.cs160.joleary.catnip/http/host/path")
        );
        AppIndex.AppIndexApi.end(client2, viewAction);
        client2.disconnect();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }


}
