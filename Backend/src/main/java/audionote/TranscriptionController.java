package audionote;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

//import org.springframework.beans.factory.annotation.Value;

@RestController // This tells Spring that the controller contains actions accessible by URL.
public class TranscriptionController {
    
    /*
    This function is called when a GET request is sent to the URL ".../transcription".
    The function returns a small piece of JSON containing a dummy transcription.
    You can include an "audio" to change what the dummy transcription says. Just
    add "?audio=anything" to the url to try it out.
    @GetMapping is used to tell Spring to call this function when a get request is
    sent to "/transcription". @PostMapping could be used instead to map POST requests to this function.
    @RequestParam maps the parameter "audio" to the String audio. This means that if you
    inclide an audio parameter in the url, it will be available in this function using
    the audio variable.
    */
    @GetMapping("/transcription") // Maps this method to the path ".../transcription"
    public ResponseEntity<String> transcribeAudio(@RequestParam(value="audio", defaultValue="No audio.") String audio, @RequestHeader(value="Token") String Token) {
        String response;
        // String token = System.getenv("SECRET_ACCESS_KEY");
        // @Value("${TOKEN}")
        // private String token;
        if (Token.equals("test")) {
            response = "{\"transcription\":" + audio + "}";
            return new ResponseEntity<String>(response, HttpStatus.OK); // We return an OK status code along with a basic JSON 
        }   
        else {
            response = "UNAUTHORIZED!";              
            return new ResponseEntity<String>(response, HttpStatus.UNAUTHORIZED); // returns an UNAUTHORIZED status code along with a message
        }
    }
}