package audionote;

import java.io.File;

import com.amazonaws.AmazonClientException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    // This is the main method which gets called by Maven when the application
    // starts up.
    // All we have to do is start Spring.
    public static void main(String[] args) throws AmazonClientException, InterruptedException {

        // Start Spring application
        SpringApplication.run(Application.class, args);

        // Uncomment to run a test transcription from a local file
        // runTestTranscription("transcribe-sample.mp3");
    }

    private static void runTestTranscription(String file_path) {

        // Print test
        System.out.println("Running test transcription...");

        // Create an upload file
        File this_file = new File(file_path);
        try {
            UploadFile.upload(this_file, false);
        } catch (InterruptedException ex) {
            System.out.println("Test transcription did fail");
        }

        // Transcribe
        StartTranscribe.startTranscribe(this_file.getName());

    }
}