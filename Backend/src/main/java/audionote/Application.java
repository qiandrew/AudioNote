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

        // file_path and key_name change depending on the file
        String file_path = "transcribe-sample.dac1d22492611d998262c8c856b98a74180a1a8f.mp3";

        // create a File object to upload the file
        // or we can use a url instead of the pathname to do this
        File this_file = new File(file_path);
        
        UploadFile.upload(this_file);
        StartTranscribe.startTranscribe();
        //GetTranscription.getResult();
    }
}