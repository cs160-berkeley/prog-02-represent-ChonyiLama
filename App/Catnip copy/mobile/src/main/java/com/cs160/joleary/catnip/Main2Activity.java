package com.cs160.joleary.catnip;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends Activity {
    ListView listView;
    int[]move_poster_resource={R.drawable.senator1,R.drawable.senator2,R.drawable.senator3,
            };
    String[]move_title;
    String[]move_rating;
    String[]tweets;
    String[]websites;
    String[]parties;

    MoveAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView=(ListView)findViewById(R.id.list_view);
        move_rating=getResources().getStringArray(R.array.move_rating);
        move_title=getResources().getStringArray(R.array .move_title);
        websites = getResources().getStringArray(R.array.websites);
        tweets = getResources().getStringArray(R.array.tweets);
        parties = getResources().getStringArray(R.array.parties);



        int i=0;
        adapter=new MoveAdapter(getApplicationContext(),R.layout.second_layout);
        listView.setAdapter(adapter);
        for (String title:move_title) {
            MoveDataProvider dataProvider=new MoveDataProvider(move_poster_resource[i],
                    title,move_rating[i],websites[i],tweets[i], parties[i]);
            adapter.add(dataProvider);
            i++;

        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), position + "is selected", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(view.getContext(), congressionalDeets.class);
                myIntent.putExtra("POSITION",position);
                startActivity(myIntent);
            }
        });

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


}
