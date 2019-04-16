package audionote;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // This tells Spring to map actions to this controller.
public class TranscriptionController {

    /*

    # Overview

    We are using Spring to create a REST API. Basically, a REST API lets us run
    our code from anywhere by calling a URL.

    Here's a brakedown of how a client would use this Spring backend:
    1.  When a client wants to perform an action using the backend,
        it will call the url associated with the action. It can also pass various
        parameters.
    2.  The URL request will trigger our code to be executed.
    3.  Our code returns a result in JSON to the client.
    4.  The client can display the results to the user.
    
    EX: To transcibe an audio file, a client would call the URL "audionote.com/transcribe"
    with an audio file included as a parameter. It would recieve back the transcript in the
    form of JSON.

    # What is the point of a backend

    The reason to make this backend in the first place is so that we don't have to duplicate
    code across clients. Without this backend, we would have to re-write our transcription and
    key word detection code for every single client.

    Instead, we write all of this code on the backend and allow clients to interact with
    it by calling various URLs. This means that all the client has to do is call a URL
    and display the results. Instead of duplicating our code, we now have it in one centralized
    location. If we want to update our code, we just change the code on the backend instead
    of having to change for each and every client. This also makes it much simpler to create
    new clients.

    Also, not sure we'll get into this on this project, but another benefit of having a centralized
    backend is that we could set up a database to share data between users. For instance, take
    sharing a photo on Instagram as an example. If Instagram only ever ran code locally on user's phones,
    how does a photo I take end up on one of my followers phones? Instead, when I take a photo
    on Instagram, it uploads it to their backend. When another user wants to view the photo, they
    send a request to the backend, which returns it to them. In this way, the backend is the connection
    between different devices.

    # What is a REST API

    In general, an API is just a way of describing how you can interact with a program.

    REST is a set of conventions that describe how URLs can be used to perform various actions.
    A REST API is simply any API that coforms to the REST conventions.

    The purpose of REST is to correctly map a request you make to the correct action. When you
    perform a request, you specify a method, url, and parameters.

    REST supports the following methods:

    POST - Used when you are creating a new object. Ex: Create a new user in your database.
    PUT - Update an exisiting object. Ex: Change a user's name.
    GET - Fetch an existing object. Ex: Given an ID, get all the info on a user.
    INDEX - Fetch a group of existing objects. Ex: Get all users.
    DELETE - Delete an existing object.


    */
    
    /*
    A function that will be invoced when the "/transcription" url path is called.
    */
    @GetMapping("/transcription") // Maps this method to the path ".../transcription"
    public String greeting(@RequestParam(value="audio", defaultValue="No audio.") String audio) {
        return "{\"transcription\":" + audio + "}";
    }
}