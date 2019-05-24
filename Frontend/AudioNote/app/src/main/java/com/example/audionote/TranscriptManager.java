package com.example.audionote;

import android.os.Environment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class TranscriptManager {

    // Cache in memory
    private static ArrayList<Transcript> transcriptCache;

    private static String fileName = "transcripts.tmp";

    // Fetch from disk
    public static ArrayList<Transcript> fetchTranscripts() {

        // Check if cache exists
        if (transcriptCache != null) {
            return transcriptCache;
        }

        // Fetch from disk
        try {
            FileInputStream inStream = new FileInputStream(transcriptFilePath());
            ObjectInputStream objectInStream = new ObjectInputStream(inStream);
            ArrayList<Transcript> transcripts = (ArrayList<Transcript>) objectInStream.readObject();
            objectInStream.close();
            transcriptCache = transcripts;
            System.out.println("fetched transcripts: " + transcripts);
        } catch (IOException ex) {
            System.out.println("fetch transcript io exception: " + ex);
            transcriptCache = new ArrayList<Transcript>();
        } catch (ClassNotFoundException ex) {
            System.out.println("fetch transcript class exception: " + ex);
            transcriptCache = new ArrayList<Transcript>();
        }

        // Return
        return transcriptCache;
    }

    // Save to disk
    public static void saveNewTranscript(Transcript transcript) {

        // Check if fetch is needed
        if (transcriptCache == null) {
            fetchTranscripts();
        }

        // Add to local cache for saving
        transcriptCache.add(0, transcript);

        // Save
        saveTranscriptionCache();

    }

    // Updates transcript
    public static void updateTranscription(Transcript transcript) {

        // Check for current index
        int index = transcriptCache.indexOf(transcript);
        if (index == -1) {
            System.out.println("Attempted to update unsaved transcript");
            return;
        }

        // Replace at index
        transcriptCache.set(index, transcript);

        // Save
        saveTranscriptionCache();

    }

    // Saves to disk
    private static void saveTranscriptionCache() {
        // Save
        try {
            FileOutputStream outStream = new FileOutputStream(transcriptFilePath());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
            objectOutputStream.writeObject(transcriptCache);
            objectOutputStream.close();
            System.out.println("saved new transcripts");
        } catch (IOException ex) {
            System.out.println("save transcript io exception: " + ex);
        }
    }

    // Creates the url where transcripts are stored
    private static String transcriptFilePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + fileName;
    }


}
