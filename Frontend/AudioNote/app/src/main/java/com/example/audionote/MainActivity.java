package com.example.audionote;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.github.clans.fab.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.example.audionote.ui.main.SectionsPagerAdapter;

import static com.example.audionote.R.layout.activity_main;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Key;
import java.util.ArrayList;

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
}