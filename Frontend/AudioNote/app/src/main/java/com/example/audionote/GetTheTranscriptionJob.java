package com.example.audionote;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GetTheTranscriptionJob {
    public void getTheJob(Activity this_activity,final String jobID) {
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
        RequestQueue mQueue = Volley.newRequestQueue(this_activity.getApplicationContext());
        mQueue.add(sr);
    }
}
