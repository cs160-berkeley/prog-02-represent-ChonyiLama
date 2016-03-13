package com.cs160.joleary.catnip;

import android.app.ListFragment;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.app.Activity;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.widget.ListAdapter;

public class LegislatorsActivity extends ListActivity {

    public static final String TAG = LegislatorsActivity.class.getSimpleName();
    private ArrayList<Legislator> mLegislators;
    private ArrayList<String> mLegislatorList;
    private String mZipcode;
    private static ListAdapter mAdapter;
    double latitude;
    double longitude;
    ListView listView;
    MoveAdapter adapter;

    String[] names = new String[3];
    String[] parties = new String[3];
    String[] websites = new String[3];
    String[] emails = new String[3];
    String[] tweets = new String[3];
    String[] move_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("STARTING");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legislators);

        move_title = getResources().getStringArray(R.array.move_title);
        listView = (ListView) findViewById(R.id.list_view);
        adapter = new MoveAdapter(getApplicationContext(), R.layout.second_layout);
        listView.setAdapter(adapter);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (extras.getString("ZIPORCURRENT").equals("current")) {
                latitude = extras.getDouble("LATITUDE");
                longitude = extras.getDouble("LONGITUDE");
                System.out.println("IN LEGISLATURE" + latitude);
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                try {
                    List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                    System.out.println("the addresses list" + addresses);
                    Address address = addresses.get(0);
                    mZipcode = address.getPostalCode();
                }catch(IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("i should be here");
                mZipcode = String.valueOf(extras.getDouble("ZIPCODE"));
            }
        }
        // Toast.makeText(this, mZipcode, Toast.LENGTH_LONG).show();



        Runnable displayInfo = new Runnable() {
            @Override
            public void run() {
                /*  Old way of populating before using ListAdapter
                mLegislatorList = new ArrayList<String>();
                for (Legislator legislator : mLegislators) {
                    String firstName = legislator.getFirstName();
                    String lastName = legislator.getLastName();
                    String party = legislator.getParty();
                    String title = legislator.getTitle();
                    String info = title + " " + firstName + " " + lastName + "  (" + party + ")";
                    mLegislatorList.add(info);
                }
                mAdapter=new ArrayAdapter<String>(LegislatorsActivity.this, android.R.layout.simple_list_item_1, mLegislatorList);
                */

            //    mAdapter = new ListAdapter(LegislatorsActivity.this, mLegislators);
                setListAdapter(mAdapter);
            }
        };
        getLegislators(mZipcode, displayInfo);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), position + "is selected", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(view.getContext(), congressionalDeets.class);
                myIntent.putExtra("POSITION", position);
                startActivity(myIntent);
            }
        });
    }


    public void getLegislators(String zipcode, final Runnable runnable) {
        String apiKey = "c7dff4303dd844eeaaeebdce9ca1fa8d";

        String legislatorsURL = "https://congress.api.sunlightfoundation.com/legislators/locate?apikey=" + apiKey + "&zip=" + zipcode;
       // String committeesURL = "https://congress.api.sunlightfoundation.com/committees?apikey="apiKey +

        if (isNetworkAvailable()) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(legislatorsURL)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {

                @Override
                public void onFailure(Request request, IOException e) {
                    System.out.println("onFailure is erroring");
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);

                        if (response.isSuccessful()) {
                            mLegislators = getLegislatorDetails(jsonData);
                            runOnUiThread(runnable);
                        } else {
                            System.out.println("onResponse response is erroring");
                        }

                    } catch (IOException e) {
                        throw new IOException("IO Exception in getLegislators oops");
                    } catch (JSONException e) {
                        throw new IOException("JSON Exception in getLegislators oops");
                    }
                }
            });
        } else {
            Toast.makeText(this, "oops i messed up", Toast.LENGTH_LONG).show();
        }
    }

    private ArrayList<Legislator> getLegislatorDetails(String jsonData) throws JSONException {

        ArrayList<Legislator> legislatorArrayList = new ArrayList<>();

        JSONObject legislatorsData = new JSONObject(jsonData);

        String legislatorsInfo = legislatorsData.getString("results");

        Log.i("legislators Info", legislatorsInfo);
        JSONArray jsonArray = new JSONArray(legislatorsInfo);
        System.out.println("in getlegisltors ");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonPart = jsonArray.getJSONObject(i);
            String title = jsonPart.getString("title");

            String firstName = jsonPart.getString("first_name");
            String lastName = jsonPart.getString("last_name");
            String party = jsonPart.getString("party");

            String email = jsonPart.getString("oc_email");
            String website = jsonPart.getString("website");
            String fullName = firstName + " "+ lastName;


            Legislator thisLegislator = new Legislator(firstName, lastName, title, party, website, email);
            legislatorArrayList.add(thisLegislator);
            //tweets[i] = "life is terrible";

            System.out.println("THE NAME OF THIS LEGISLATOR IS" + firstName + lastName);

            final MoveDataProvider dataProvider = new MoveDataProvider(1, fullName, email, website, party);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.add(dataProvider);
                }
            });

            }

        return legislatorArrayList;
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

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        Legislator thisLegislator = (Legislator) mAdapter.getItem(position);
        Bundle bundle = new Bundle();
        bundle.putString("first_name", thisLegislator.getFirstName());
        bundle.putString("last_name", thisLegislator.getLastName());
        bundle.putString("party", thisLegislator.getParty());
        bundle.putString("title", thisLegislator.getTitle());
        bundle.putString("oc_email", thisLegislator.getEmail());

        bundle.putString("website", thisLegislator.getWebsite());
/**
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
**/
    }



}