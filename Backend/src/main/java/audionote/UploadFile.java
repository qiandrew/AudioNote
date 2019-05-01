package audionote;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.AmazonServiceException;
import java.io.File;
import java.nio.file.Paths;

class UploadFile{
    public static void upload(File this_file)  {
        // file_path and key_name change depending on the file
        String bucket_name = "audionoteucsb";
        String key_name = "samplefile.mp3";

        System.out.format("Uploading to S3 bucket %s...\n", bucket_name);
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        try {
            s3.putObject(bucket_name,key_name,this_file);
        }   catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
        System.out.println("Down!");
    }
}