package audionote;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.AmazonS3URI;
import com.amazonaws.services.transcribe.AmazonTranscribe;
import com.amazonaws.services.transcribe.AmazonTranscribeClientBuilder;
import com.amazonaws.services.transcribe.model.TranscriptionJob;
import com.amazonaws.services.transcribe.model.TranscriptionJobStatus;
import com.amazonaws.services.transcribe.model.GetTranscriptionJobRequest;
import com.amazonaws.services.transcribe.model.GetTranscriptionJobResult;
import com.amazonaws.AmazonServiceException;

class GetTranscription {
    public static String getResult(String job_name) throws AmazonServiceException {

        // Get transcribe
        final AmazonTranscribe aws_transcribe = AmazonTranscribeClientBuilder.defaultClient();

        // Create a new request to fetch the job
        GetTranscriptionJobRequest this_request = new GetTranscriptionJobRequest();
        this_request.setTranscriptionJobName(job_name);
        
        // Get the results
        GetTranscriptionJobResult this_result = aws_transcribe.getTranscriptionJob(this_request);
        TranscriptionJob this_job = this_result.getTranscriptionJob();
        String jobStatus = this_job.getTranscriptionJobStatus();

        // Check if the job has completed
        if (!jobStatus.equals(TranscriptionJobStatus.COMPLETED.toString())) {
            System.out.println("early");
            return String.format("{ \"status\": \"%s\" }", jobStatus);
        }

        // Get the uri for the transcription file so that we can
        String uri = this_job.getTranscript().getTranscriptFileUri();
        AmazonS3URI s3uri = new AmazonS3URI(uri);

        // Create s3 instance
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();

        // Download the file to json string
        String jsonString = s3.getObjectAsString(s3uri.getBucket(), s3uri.getKey());

        // Return string
        Transcription t = new Transcription(jsonString);
        return t.toJSON();
    }
}