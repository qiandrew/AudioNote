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

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // public ArrayList<Transcript> dummyTranscripts = new ArrayList<Transcript>();
    private static final String TAG = "MainActivity";
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, new ArrayList());
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("got pos: " + position);
                Transcript transcription = TranscriptManager.fetchTranscripts().get(position);
                if (!transcription.getIsFinished()) {
                    Toast.makeText(getApplicationContext(), "Transcription not finished", Toast.LENGTH_LONG).show();
                    refreshSavedItems();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                intent.putExtra("transcript", transcription);
                startActivity(intent);
            }
        });

        // DUMMY TRANSCRIPTS BELOW
//
//        Word[] words = new Word[10];
//        for (int i = 0; i < 10; i++) {
//            words[i] = new Word("dummy", 1.00, 0.00, 1.00);
//        }
//
//        KeyWord[] keyWords = new KeyWord[1];
//        keyWords[0] = new KeyWord("dummy");
//
//        try {
//            URL dummyURL = new URL("https://www.google.com");
//            Transcript dummyTranscript1 = new Transcript("stub");
//            dummyTranscript1.update("Hello, world!", words, keyWords, dummyURL);
//            dummyTranscripts.add(dummyTranscript1);
//
//            Transcript dummyTranscript2 = new Transcript("stub2");
//            dummyTranscript2.update("Goodbye, world!", words, keyWords, dummyURL);
//            dummyTranscripts.add(dummyTranscript2);
//        }
//        catch(MalformedURLException e) {
//            System.out.println("ERROR IN MAIN ACTIVITY.");
//        }
//
//        // DUMMY TRANSCRIPTS ABOVE
//
//        // Save example
//
//        //ArrayList<Transcript> transcripts = TranscriptManager.fetchTranscripts();
//        //TranscriptManager.saveNewTranscript(dummyTranscripts.get(0));


        FloatingActionButton fab_record = findViewById(R.id.fab_record);
        fab_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecordActivity();
            }
        });
        FloatingActionButton fab_upload = findViewById(R.id.fab_upload);
        fab_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUploadActivity();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        // Update
        updateList();

        // Refresh jobs
        refreshSavedItems();
    }

    public void openRecordActivity() {
        Intent intent = new Intent(this, RecordActivity.class);
        startActivity(intent);
    }
    public void openUploadActivity() {
        Intent intent  = new Intent(this, activity_upload.class);
        startActivity(intent);
    }


    public void updateList() {

        System.out.println("Updated list");

        // Get transcriptions
        ArrayList<Transcript> transcripts = TranscriptManager.fetchTranscripts();

        // Get titles for transcriptions
        ArrayList<String> jobIds = new ArrayList<>();
        for (int i = 0; i < transcripts.size(); i++) {
            if (!transcripts.get(i).getIsFinished()) {
                jobIds.add(transcripts.get(i).getJobID() + " (In Progress)");
            } else {
                jobIds.add(transcripts.get(i).getJobID());
            }
        }

        System.out.println("items: " + jobIds);

        // Update adapter
        adapter.clear();
        adapter.addAll(jobIds);
        adapter.notifyDataSetChanged();

    }

    public void refreshSavedItems() {

        // Get transcriptions
        ArrayList<Transcript> transcripts = TranscriptManager.fetchTranscripts();

        // Fetch if needed

        for (int i = 0; i < transcripts.size(); i++) {
            if (!transcripts.get(i).getIsFinished()) {
                String jobId = transcripts.get(i).getJobID();
                getTheJob(jobId);
                System.out.println("Updating job: " + jobId);
            }
        }

    }


    public void getTheJob(final String jobID) {
        String url = "https://audionoteucsb.herokuapp.com/transcription/" + jobID;
        System.out.println("ffull: " + url);
        StringRequest sr = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("response: " + response);
                            JSONObject this_json = new JSONObject(response);
                            Transcript this_job = new Transcript(jobID);
                            this_job.jsonParser(this_json);
                            if (this_job.getIsFinished()) {
                                System.out.println("fetched completed job: " + this_job.getJobID());
                                TranscriptManager.updateTranscription(this_job);
                                updateList();
                            } else {
                                System.out.println("job still in progress: " + this_job.getJobID());
                            }
                        } catch (JSONException e) {
                            System.out.println("job still in progress error: " + jobID);
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Log.d("Error: ", error.getMessage());
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
        sr.setShouldCache(false);
        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        mQueue.add(sr);
    }




}