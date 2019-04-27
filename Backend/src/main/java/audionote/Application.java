package audionote;

import com.amazonaws.services.s3.transfer.Upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    // This is the main method which gets called by Maven when the application starts up.
    // All we have to do is start Spring.
    public static void main(String[] args) {
        // Start Spring application
        SpringApplication.run(Application.class, args);

        //StartTranscribe.startTranscribe();
        //UploadFile.upload();
        //GetTranscription.getResult();
    }
}