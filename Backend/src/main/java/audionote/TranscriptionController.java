package audionote;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.amazonaws.AmazonServiceException;

@RestController // This tells Spring that the controller contains actions accessible by URL.
public class TranscriptionController {

    // Get token from environmental variable. See Github for more info.
    private String token = System.getenv("AUDIONOTE_TOKEN");

    // Auth response
    private ResponseEntity<String> unauthorizedResponse = new ResponseEntity<String>("{ \"message\": \"Please set the Token header to a valid token.\" }", HttpStatus.UNAUTHORIZED);

    /*
     * This function is called when a GET request is sent to the URL
     * ".../transcription". The function returns a small piece of JSON containing a
     * dummy transcription. You can include an "audio" to change what the dummy
     * transcription says. Just add "?audio=anything" to the url to try it out.
     * 
     * @GetMapping is used to tell Spring to call this function when a get request
     * is sent to "/transcription". @PostMapping could be used instead to map POST
     * requests to this function.
     * 
     * @RequestParam maps the parameter "audio" to the String audio. This means that
     * if you inclide an audio parameter in the url, it will be available in this
     * function using the audio variable.
     */
    @RequestMapping(value = "/transcription", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> transcribeAudio(@RequestPart(value = "audio") MultipartFile audioFile, @RequestHeader(value = "Token", defaultValue = "") String token) {

        // Check for token
        if (!token.equals(this.token)) {
            return unauthorizedResponse;
        }

        // Try getting the file
        try {

            // Get file
            File converted = convertMultipartFileToNormalFile(audioFile);

            // Upload to s3
            UploadFile.upload(converted, true);

            // Start transcription
            String job = StartTranscribe.startTranscribe(converted.getName());

            // Send response with job
            String response = String.format("{ \"transcription_job\": \"%s\" }", job);
            return new ResponseEntity<String>(response, HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<String>("Invalid file", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/transcription/{job_name}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getTranscript(@PathVariable String job_name, @RequestHeader(value = "Token", defaultValue = "") String token) {

        // Check for token
        if (!token.equals(this.token)) {
            return unauthorizedResponse;
        }

        // Get transcript
        try {
            String result = GetTranscription.getResult(job_name);
            Transcription t = new Transcription(result);
            for(int i = 0; i < t.topWords().size();i++){
                System.out.println(t.topWords().get(i).getWord() + "  " + t.topWords().get(i).getFrequency());
            }
            return new ResponseEntity<String>(result, HttpStatus.OK);
        } catch (AmazonServiceException e) {
            System.out.println("Transcription failed: " + e);
            return new ResponseEntity<String>("Transcription fetch failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private File convertMultipartFileToNormalFile(MultipartFile multipartFile) throws IOException {
        File newFile = new File(multipartFile.getOriginalFilename());
        FileOutputStream stream = new FileOutputStream(newFile);
        stream.write(multipartFile.getBytes());
        stream.close();
        return newFile;
    }
}