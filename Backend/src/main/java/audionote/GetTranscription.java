package audionote;

import com.amazonaws.services.transcribe.AmazonTranscribe;
import com.amazonaws.services.transcribe.AmazonTranscribeClientBuilder;
import com.amazonaws.services.transcribe.model.TranscriptionJob;
import com.amazonaws.services.transcribe.model.GetTranscriptionJobRequest;
import com.amazonaws.services.transcribe.model.GetTranscriptionJobResult;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;

class GetTranscription {
    public static void getResult() {
        // value for request
        String job_name = "samplefile01";

        // create a new request and set the value
        GetTranscriptionJobRequest this_request = new GetTranscriptionJobRequest();
        this_request.setTranscriptionJobName(job_name);

        System.out.format("Get transcription job %s ...\n", job_name);
        BasicAWSCredentials creds = new BasicAWSCredentials("AKIAYO3M5WMIVYVDX7EF", "Mp6oRNrC6Bl/QcM9ywECHlCfgkUNcnO0CK0LwIe6");
        final AmazonTranscribe aws_transcribe = AmazonTranscribeClientBuilder.standard().withRegion("us-west-2")
        .withCredentials(new AWSStaticCredentialsProvider(creds))
        .build();
        
        try {
            GetTranscriptionJobResult this_result = aws_transcribe.getTranscriptionJob(this_request);
            TranscriptionJob this_job = this_result.getTranscriptionJob();

            // the toString() returns the Json file as a string
            String jobStr = this_job.toString();
            System.out.println(jobStr);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
        System.out.println("Done!");
    }
}