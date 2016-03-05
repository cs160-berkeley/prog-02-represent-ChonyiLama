package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mTextView;
    private Button mFeedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                startService(sendIntent);


            }
        });
    }
}
