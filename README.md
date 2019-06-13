# AudioNote


# Project Summary

  AudioNote lets you transcribe your notes.

# Installation

  ## Prerequisites
  
  AudioNote uses AWS to upload files and perform transcriptions. Before you begin...
  
  1. Create an AWS account.
  2. Create an S3 bucket to which audio files will be uploaded.

  ## Installation Steps
  
  1. Obtain your AWS **Access Key Id** and **Secret Access Key**. Also take note of the bucket name in S3.
  2. To test the backend locally, you must set a few environmental variables on your computer by running the following in the command line:
  
  ```
  export AWS_ACCESS_KEY_ID=YOUR_ACCESS_ID // Access ID from AWS
  export AWS_SECRET_ACCESS_KEY=YOUR_ACCESS_SECRET // Access secret from AWS
  export AWS_REGION=us-east-1 // AWS region where you want to run AudioNote
  export AWS_BUCKET_NAME=YOUR_BUCKET_NAME // AWS region where you want to run AudioNote
  export AUDIONOTE_TOKEN=1234 // Token AudioNote will use to authenticate requests. Include it as the 'Token' header in all requests. 
  ```
  3. You can start the server by moving to the 'Backend' directory and running `mvn spring-boot:run`.
  4. You can run the app in Android Studio.

# Resources

AWS Sagemaker: https://aws.amazon.com/sagemaker/

Mozilla Common Voice: https://voice.mozilla.org/en
