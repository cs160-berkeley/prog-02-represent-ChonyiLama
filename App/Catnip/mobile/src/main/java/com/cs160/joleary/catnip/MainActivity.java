package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.wearable.Wearable;

import java.io.IOError;

public class MainActivity extends Activity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
    //there's not much interesting happening. when the buttons are pressed, they start
    //the PhoneToWatchService with the cat name passed in.

    private Button mFredButton;
    private Button mLexyButton;
    private boolean isConnected = false;
    private double latit;
    private double longit;
    private double zipcode;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isConnected = false;
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            System.out.println("google client null");
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addApi(Wearable.API)  // used for data layer API
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }

        // mFredButton = (Button) findViewById(R.id.fred_btn);
        // mLexyButton = (Button) findViewById(R.id.lexy_btn);

        Button goButton = (Button) findViewById(R.id.button_go);

        RadioButton currentLocation = (RadioButton) findViewById(R.id.radioButton_current_location);
        currentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("radio button clicked!");
              //  onResume();

                if (isConnected == true) {
                   // TextView latitude = (TextView) findViewById(R.id.latitude);
                   // TextView longitude = (TextView) findViewById(R.id.longitude);
                    Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                            mGoogleApiClient);
                    if (mLastLocation != null) {
                       // String latitude = String.valueOf(mLastLocation.getLatitude()));
                        //String longitude = String.valueOf(mLastLocation.getLongitude()));
                        latit = mLastLocation.getLatitude();
                        longit = mLastLocation.getLongitude();

                    } else {
                        System.out.println("yes i am null");
                    }
                }
            }
        });

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText zipcodeInput = (EditText) findViewById(R.id.zipcode);
                if (zipcodeInput.length() > 0) {
                    zipcode = Double.parseDouble(zipcodeInput.getText().toString());
                   // System.out.println("HELLO IS THIS WORKING");
                }
/**
 Intent congressGoIntent = new Intent(MainActivity.this, BasicCongressView.class);
 startActivity(congressGoIntent);
 **/
                /**
                Intent sendZipcodeIntent = new Intent(MainActivity.this, PhoneToWatchService.class);
                sendZipcodeIntent.putExtra("ZIP_CODE", zipcodeString);
                startService(sendZipcodeIntent);
                **/
                Intent openMainTwo = new Intent(MainActivity.this, LegislatorsActivity.class);
                if (isConnected == true) {
                    openMainTwo.putExtra("ZIPORCURRENT", "current");
                    openMainTwo.putExtra("LATITUDE", latit);
                    openMainTwo.putExtra("LONGITUDE", longit);

                } else {
                    openMainTwo.putExtra("ZIPORCURRENT", "zip");
                    openMainTwo.putExtra("ZIPCODE", zipcode);
                }
                startActivity(openMainTwo);
            }
        });

    }




/**
 mFredButton.setOnClickListener(new View.OnClickListener() {
@Override public void onClick(View v) {
Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
sendIntent.putExtra("CAT_NAME", "Fred");
startService(sendIntent);
}
});

 mLexyButton.setOnClickListener(new View.OnClickListener() {
@Override public void onClick(View v) {
Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
sendIntent.putExtra("CAT_NAME", "Lexy");
startService(sendIntent);
}
});
 **/


    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGoogleApiClient.disconnect();
    }
    @Override
    public void onConnected(Bundle bundle) {
        isConnected = true;

    }

    @Override
    public void onConnectionSuspended(int i) {
        System.out.println("CONNECTION SUSPENDED FUCK");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connResult) {
        System.out.println("CONNECTION FAILED FUCK");
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.cs160.joleary.catnip/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.cs160.joleary.catnip/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
