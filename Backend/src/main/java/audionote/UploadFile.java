package audionote;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.transfer.Upload;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import java.io.File;
import java.nio.file.Paths;

class UploadFile {
    public static void upload(File this_file) throws AmazonClientException, InterruptedException {
        // file_path and key_name change depending on the file
        String bucket_name = "audionoteucsb";
        String key_name = "samplefile.mp3";

        System.out.format("Uploading to S3 bucket %s...\n", bucket_name);
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        try {
            TransferManager tm = TransferManagerBuilder.standard().withS3Client(s3).build();
            Upload upload = tm.upload(bucket_name,key_name,this_file);
            upload.waitForCompletion();
            tm.shutdownNow();
        }   catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
    }
}