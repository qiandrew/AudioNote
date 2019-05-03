package audionote;

import java.io.File;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.transfer.Upload;

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
        String bucket_name = "audionoteucsb";
        String file_path = "/Users/shihengwang/Desktop/samplefile.mp3";
        String key_name = "samplefile.mp3";

        // create a File object to upload the file
        // or we can use a url instead of the pathname to do this
        File this_file = new File(file_path);
        
        UploadFile.upload(this_file);

        String job_name = "Sample FIle";
        StartTranscribe.startTranscribe(job_name);
        //GetTranscription.getResult();
    }
}