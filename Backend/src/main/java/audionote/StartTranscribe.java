package audionote;

import com.amazonaws.services.transcribe.AmazonTranscribe;
import com.amazonaws.services.transcribe.AmazonTranscribeClientBuilder;
import com.amazonaws.services.transcribe.model.StartTranscriptionJobRequest;
import com.amazonaws.services.transcribe.model.Media;

import java.util.Random;

import com.amazonaws.AmazonServiceException;

class StartTranscribe {
    public static String startTranscribe(String file_name) {

        // Get bucket for output
        String awsBucketName = System.getenv("AWS_BUCKET_NAME");

        // Create random job name
        String job_name = "job_" + generateRandomString();
        String s3_url = "s3://" + System.getenv("AWS_BUCKET_NAME") + "/" + file_name; // ex: s3://audionoteucsb/samplefile.mp3
        System.out.println("url: " + s3_url);

        String extension = "";
        int i = file_name.lastIndexOf('.');
        if (i > 0) {
            extension = file_name.substring(i + 1);
        }

        // create a Media object for this file
        Media this_file = new Media();
        this_file.setMediaFileUri(s3_url);

        // create a StartTranscriptionJobRequest object for this file
        StartTranscriptionJobRequest transcription_request = new StartTranscriptionJobRequest();
        transcription_request.setLanguageCode("en-US");
        transcription_request.setMediaFormat(extension);
        transcription_request.setTranscriptionJobName(job_name);
        transcription_request.setMedia(this_file);
        transcription_request.setOutputBucketName(awsBucketName);

        System.out.format("Start transcription job \'%s\' on file \'%s\'...\n", job_name, s3_url);
        final AmazonTranscribe aws_transcribe = AmazonTranscribeClientBuilder.defaultClient();
        try {
            aws_transcribe.startTranscriptionJob(transcription_request);
        } catch (AmazonServiceException e){
            System.err.println("Aws error" + e.getErrorMessage());
        }
        System.out.format("Started transcription job: %s\n", job_name);
        return job_name;
    }

    private static String generateRandomString() {
        String input = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(input.length());
            builder.append(input.charAt(index));
        }
        return builder.toString();
    }

}