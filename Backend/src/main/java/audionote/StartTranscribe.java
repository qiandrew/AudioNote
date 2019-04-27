package audionote;

import com.amazonaws.services.transcribe.AmazonTranscribe;
import com.amazonaws.services.transcribe.AmazonTranscribeClient;
import com.amazonaws.services.transcribe.AmazonTranscribeClientBuilder;
import com.amazonaws.services.transcribe.model.StartTranscriptionJobRequest;
import com.amazonaws.services.transcribe.model.Media;
import com.amazonaws.AmazonServiceException;

class StartTranscribe {
    public static void startTranscribe() {
        // keys for Transcription job
        // s3_url, media_format change depending on the file
        // job_name changes depending on user and time?
        String language_code = "en-US";
        String s3_url = "s3://audionoteucsb/samplefile.mp3";
        String media_format = "mp3";
        String job_name = "sampleaudio";

        // create a Media object for this file
        Media this_file = new Media();
        this_file.setMediaFileUri(s3_url);

        // create a StartTranscriptionJobRequest object for this file
        StartTranscriptionJobRequest transcription_request = new StartTranscriptionJobRequest();
        transcription_request.setLanguageCode(language_code);
        transcription_request.setMediaFormat(media_format);
        transcription_request.setTranscriptionJobName(job_name);
        transcription_request.setMedia(this_file);


        System.out.format("Start transcription job %s ...\n", job_name);
        final AmazonTranscribe aws_transcribe = AmazonTranscribeClientBuilder.defaultClient();
        try {
            aws_transcribe.startTranscriptionJob(transcription_request);
        } catch (AmazonServiceException e){
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
        System.out.println("Down!");
    }
}