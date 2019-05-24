package com.example.audionote;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import com.example.audionote.ui.main.SectionsPagerAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Transcript> dummyTranscripts = new ArrayList<Transcript>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // DUMMY TRANSCRIPTS BELOW

        Word[] words = new Word[10];
        for (int i = 0; i < 10; i++) {
            words[i] = new Word("dummy", 1.00, 0.00, 1.00);
        }

        KeyWord[] keyWords = new KeyWord[1];
        keyWords[0] = new KeyWord("dummy");

        try {
            URL dummyURL = new URL("https://www.google.com");
            Transcript dummyTranscript1 = new Transcript("stub");
            dummyTranscript1.update("Hello, world!", words, keyWords, dummyURL);
            dummyTranscripts.add(dummyTranscript1);

            Transcript dummyTranscript2 = new Transcript("stub2");
            dummyTranscript2.update("Goodbye, world!", words, keyWords, dummyURL);
            dummyTranscripts.add(dummyTranscript2);
        }
        catch(MalformedURLException e) {
            System.out.println("ERROR IN MAIN ACTIVITY.");
        }

        // DUMMY TRANSCRIPTS ABOVE

        // Save example

//        ArrayList<Transcript> transcripts = TranscriptManager.fetchTranscripts();
//        TranscriptManager.saveNewTranscript(dummyTranscripts.get(0));

        setContentView(R.layout.activity_main);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        final ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab_record = findViewById(R.id.fab_record);
        fab_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecordActivity();
            }
        });

        FloatingActionButton fab_upload = findViewById(R.id.fab_upload);
        // home page to file picker page

    }

    public void openRecordActivity() {
        Intent intent = new Intent(this, RecordActivity.class);
        startActivity(intent);
    }

    public void getTheJob(final String jobID) {
        String url = "https://audionoteucsb.herokuapp.com/transcription/" + jobID;
        StringRequest sr = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject this_json = new JSONObject(response);
                            Transcript this_job = new Transcript(jobID);
                            this_job.jsonParser(this_json);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error: ", error.getMessage());
                    }
                }
        ) {
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Token", "1234");
                return headers;
            }
        };
        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        mQueue.add(sr);
    }
}