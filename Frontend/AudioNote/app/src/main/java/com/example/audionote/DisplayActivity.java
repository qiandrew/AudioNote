package com.example.audionote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.Image;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DisplayActivity extends AppCompatActivity {

    ScrollView transcript_view;
    TextView textView;
    boolean playing, first_play;
    KeyWord[] keyWords;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        // Get text view
        textView = findViewById(R.id.transcript_text);

        // Fetch transcript for intent and display
        Transcript transcript = (Transcript)getIntent().getSerializableExtra("transcript");
        displayTranscript(transcript);

    }

    public void displayTranscript(Transcript transcript) {

        // Get text
        String text = transcript.getText();

        // Highlight keywords
        keyWords = transcript.getKeyWords();
        SpannableString ss = new SpannableString(text);
        SpannableStringBuilder ssb = new SpannableStringBuilder(text);

        // Loop through words
        int word_length;
        String word;
        for (int i = 0; i < keyWords.length; i++) {

            // Get the word and its length
            Log.d("", "keyWords.length: " + keyWords.length);
            word_length = keyWords[i].getWord().length();
            word = keyWords[i].getWord();

            // Highlight each occurrence of the word
            for (int j = -1; (j = text.indexOf(word, j + 1)) != -1; j++) {
                ss.setSpan(new BackgroundColorSpan(Color.YELLOW), j, j+word_length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

        }

        // Display final text
        textView.setText(ss);

    }
}
