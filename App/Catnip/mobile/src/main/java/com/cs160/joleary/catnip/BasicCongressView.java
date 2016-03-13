package com.cs160.joleary.catnip;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class BasicCongressView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_congress_view);

        Button learn_button1 = (Button) findViewById(R.id.button_learn1);
        Button learn_button2 = (Button) findViewById(R.id.button_learn2);
        Button learn_button3 = (Button) findViewById(R.id.button_learn3);

        learn_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent congressGoIntent = new Intent(BasicCongressView.this, congressionalDeets.class);
                startActivity(congressGoIntent);
            }


        });





    }

}
