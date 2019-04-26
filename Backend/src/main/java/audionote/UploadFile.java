package audionote;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.AmazonServiceException;
import java.io.File;
import java.nio.file.Paths;

class UploadFile{
    public static void upload() {
        String bucket_name = "audionoteucsb";
        String file_path = "/Users/shihengwang/Desktop";
        String key_name = "sampleaudio.mp3";

        System.out.format("Uploading %s to S3 bucket %s...\n", file_path, bucket_name);
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        try {
            s3.putObject(bucket_name,key_name,file_path);
        }   catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
        System.out.println("Down!");
    }
}