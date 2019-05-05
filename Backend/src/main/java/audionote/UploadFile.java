package audionote;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.transfer.Upload;

import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import java.io.File;

class UploadFile {

    // Keys

    private static String awsBucketName = System.getenv("AWS_BUCKET_NAME");

    // Upload

    public static void upload(File this_file, Boolean deleteOnCompletion) throws AmazonClientException, InterruptedException {
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        System.out.format("Uploading %s to S3 bucket %s...\n", this_file.getName(), awsBucketName);
        try {
            TransferManager tm = TransferManagerBuilder.standard().withS3Client(s3).build();
            Upload upload = tm.upload(awsBucketName, this_file.getName(), this_file);
            upload.waitForCompletion();
            System.out.println("File uploaded: " + this_file.getName());
            tm.shutdownNow();
            if (deleteOnCompletion) {
                this_file.delete();
            }
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
    }
}