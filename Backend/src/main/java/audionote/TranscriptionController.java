package audionote;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // This tells Spring that the controller contains actions accessible by URL.
public class TranscriptionController {
    
    /*
    This function is called when a GET request is sent to the URL ".../transcription".
    The function returns a small piece of JSON containing a dummy transcription.

    You can include an "audio" to change what the dummy transcription says. Just
    add "?audio=anything" to the url to try it out.

    @GetMapping is used to tell Spring to call this function when a get reqeust is
    sent to "/transcription". @PostMapping could be used instead to map POST requests to this function.

    @RequestParam maps the parameter "audio" to the String audio. This means that if you
    inclide an audio parameter in the url, it will be available in this function using
    the audio variable.
    */
    @GetMapping("/transcription") // Maps this method to the path ".../transcription"
    public String transcribeAudio(@RequestParam(value="audio", defaultValue="No audio.") String audio, @RequestParam(value="token") String token) {
        if (token.equals("test"))    
            return "{\"transcription\":" + audio + "}"; // We return a basic JSON  
        else
            return "UNAUTHORIZED!";              
    }
}