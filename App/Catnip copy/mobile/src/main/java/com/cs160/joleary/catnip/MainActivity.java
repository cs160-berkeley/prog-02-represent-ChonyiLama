package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends Activity {
    //there's not much interesting happening. when the buttons are pressed, they start
    //the PhoneToWatchService with the cat name passed in.

    private Button mFredButton;
    private Button mLexyButton;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("in the mobile, i have started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mFredButton = (Button) findViewById(R.id.fred_btn);
        mLexyButton = (Button) findViewById(R.id.lexy_btn);

        Button goButton = (Button) findViewById(R.id.button_go);


        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText zipcodeInput = (EditText) findViewById(R.id.zipcode);
                final String zipcodeString = zipcodeInput.getText().toString();
                System.out.println("HIIIIIII" + zipcodeString);
/**
                Intent congressGoIntent = new Intent(MainActivity.this, BasicCongressView.class);
                startActivity(congressGoIntent);
**/
                Intent sendZipcodeIntent = new Intent(MainActivity.this, PhoneToWatchService.class);
                sendZipcodeIntent.putExtra("ZIP_CODE", zipcodeString);
                startService(sendZipcodeIntent);

                Intent openMainTwo = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(openMainTwo);
            }
        });

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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


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
