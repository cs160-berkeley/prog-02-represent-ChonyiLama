package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends Activity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "UgrDhQFO4guYM0WoPO0cWajME";
    private static final String TWITTER_SECRET = "lx3kQIBDxMocVfweH4f6klEztAW4JrXnBLsw0GaSXSpYvv6y9a";


    private TextView mTextView;
    private Button mFeedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);

        mFeedBtn = (Button) findViewById(R.id.feed_btn);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        System.out.println("IN THE CLOOOOOOOOSET");
        if (extras != null) {
            String zipCode = extras.getString("ZIP_CODE");
            // mFeedBtn.setText("Hello " + zipCode);
            System.out.println("GABE IS GAAAAAAAY" + zipCode);
            /**
            String catName = extras.getString("CAT_NAME");
            mFeedBtn.setText("Feed " + catName);
            **/
            final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
            pager.setAdapter(new SampleGridPagerAdapter(this, getFragmentManager()));

            /**
            Intent congress1ViewIntent = new Intent(MainActivity.this, Congress1View.class);
            startActivity(congress1ViewIntent);
             **/

        }

        mFeedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
                System.out.println("STARTING WATCH TO PHONE SERVICE");
                startService(sendIntent);


            }
        });
    }
}
