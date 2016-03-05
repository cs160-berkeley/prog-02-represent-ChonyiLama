package com.cs160.joleary.catnip;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

public class congressionalDeets extends Activity {

    TextView name_text;
    TextView endterm_text;
    TextView bill_text;
    TextView committees_text;
    String[]names;
    String[]bills;
    String[]committees;
    String[]endterms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congressional_deets);

        name_text = (TextView) findViewById(R.id.senator_name);
        endterm_text = (TextView) findViewById(R.id.term_end);
        bill_text = (TextView) findViewById(R.id.bills);
        committees_text = (TextView) findViewById(R.id.committees);

        names=getResources().getStringArray(R.array.move_title);
        endterms=getResources().getStringArray(R.array.endterms);
        bills = getResources().getStringArray(R.array.bills);
        committees = getResources().getStringArray(R.array.committees);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            int position= extras.getInt("POSITION");
            name_text.setText(names[position]);
            endterm_text.setText(endterms[position]);
            bill_text.setText(bills[position]);
            committees_text.setText(committees[position]);

        }

    }

}
