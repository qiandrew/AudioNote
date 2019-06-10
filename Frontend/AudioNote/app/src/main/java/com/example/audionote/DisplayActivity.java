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
    ImageButton btn_play, btn_stop;
    boolean playing, first_play;
    KeyWord[] keyWords;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
//
//        //
//        ArrayList<Transcript> dummyTranscripts = new ArrayList<>();
//        Word[] words = new Word[10];
//        for (int i = 0; i < 10; i++) {
//            words[i] = new Word("dummy", 1.00, 0.00, 1.00);
//        }
//
//        KeyWord[] keyWords = new KeyWord[5];
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
//
//        //
//
//        dummyTranscripts.get(0).getKeyWords()[0] = new KeyWord("Pythagorean");
//        dummyTranscripts.get(0).getKeyWords()[1] = new KeyWord("Solar System");
//        dummyTranscripts.get(0).getKeyWords()[2] = new KeyWord("AudioNote");
//        dummyTranscripts.get(0).getKeyWords()[3] = new KeyWord("Android Studio");
//        dummyTranscripts.get(0).getKeyWords()[4] = new KeyWord("Frontend");
//
//        String text = "Yesterday\n" +
//                "All my AudioNote troubles Pythagorean seemed so far away\n" +
//                "Now it looks as though they're here to stay\n" +
//                "Oh, I believe in yesterday\n" +
//                "Suddenly\n" +
//                "I'm not half the man I used to be\n" +
//                "There's a shadow hanging over me\n" +
//                "Oh, yesterday Solar System came suddenly\n" +
//                "Why she had to go, I don't know\n" +
//                "Frontend She wouldn't say \n" +
//                "I said something wrong\n" +
//                "Now I long for Android Studio yesterday\n" +
//                "Yesterday\n" +
//                "Love was such an easy game to play\n" +
//                "Now I need a Frontend place to hide away\n" +
//                "Oh, I believe in yesterday\n" +
//                "Why she had to go AudioNote, I don't know\n" +
//                "She wouldn't say Pythagorean\n" +
//                "I said something wrong\n" +
//                "Now I long for yesterday\n" +
//                "Yesterday\n" +
//                "Love was such an easy Android Studio game to play\n" +
//                "Now I need a place to hide away\n" +
//                "Oh, Solar System I believe in yesterday\n";
//
//        //dummyTranscripts.get(0).setText(text);
//        Transcript transcript = dummyTranscripts.get(0);
//        transcript.setText(text);
//

        //

        //transcript_view = findViewById(R.id.transcript_view);
        textView = findViewById(R.id.transcript_text);
        btn_play = findViewById(R.id.btn_play);
        btn_stop = findViewById(R.id.btn_stop);

        first_play = true;
        playing = false;
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (first_play) {

                    btn_play.setImageResource(R.drawable.display_pause_audio);
                    playing = true;
                    first_play = false;
                    Toast.makeText(DisplayActivity.this, "Playing", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (playing) {

                        btn_play.setImageResource(R.drawable.display_play_audio);
                        playing = false;
                        Toast.makeText(DisplayActivity.this, "Paused", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        btn_play.setImageResource(R.drawable.display_pause_audio);
                        playing = true;
                        Toast.makeText(DisplayActivity.this, "Playing", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_play.setImageResource(R.drawable.display_play_audio);
                Toast.makeText(DisplayActivity.this, "Stopped", Toast.LENGTH_SHORT).show();
            }
        });

        Transcript transcript = (Transcript)getIntent().getSerializableExtra("transcript");
        displayTranscript(transcript);


    }

    public void displayTranscript(Transcript transcript) {
        String text = transcript.getText();
        keyWords = transcript.getKeyWords();
        SpannableString ss = new SpannableString(text);
        SpannableStringBuilder ssb = new SpannableStringBuilder(text);
        int word_length;
        String word;

        for (int i = 0; i < keyWords.length; i++) {
            Log.d("", "keyWords.length: " + keyWords.length);
            word_length = keyWords[i].getWord().length();
            word = keyWords[i].getWord();


            for (int j = -1; (j = text.indexOf(word, j + 1)) != -1; j++) {
                //System.out.println(i);
//
                ss.setSpan(new BackgroundColorSpan(Color.YELLOW), j, j+word_length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


            }


            textView.setText(ss);

        }

    }
}
