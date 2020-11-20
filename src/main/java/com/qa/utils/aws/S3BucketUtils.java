package com.qa.utils.aws;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.qa.constants.ConfigConstants;
import com.qa.io.UnravelConfigYamlReader;
import com.qa.utils.LoggingUtils;

import java.io.File;
import java.net.URL;
import java.util.Date;

/**
 * @author Ankur Jaiswal
 * Class contains the method to upload file to aws and get the link to access
 */
public class S3BucketUtils {
    // 7 Days for expire time
    private final long EXPIRE_TIME_IN_MILISEC = 7 * 24 * 60 * 60 * 1000;
    private final LoggingUtils LOGGER = new LoggingUtils(S3BucketUtils.class);

    /**
     * Connect with AWS account.
     * Create aws client with credentials api key and secret
     * residing in location ~.aws/credential file.
     */
    private AmazonS3 getS3Client() {
        LOGGER.info("Create aws s3 client and upload",null);
        Regions clientRegion = Regions.US_WEST_2;
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
            .withCredentials(new ProfileCredentialsProvider())
            .withRegion(clientRegion)
            .build();
        return s3Client;
    }

    /**
     * Upload file to aws bucket and generate public accessible link with
     * expire time.
     * @param file - File to upload on aws
     * @return - Public url of image uploaded on aws
     */
    public String uploadFileToS3Bucket(String file) {
        LOGGER.info("Read bucket information from config yaml",null);
        UnravelConfigYamlReader unravelConfigYamlReader = new UnravelConfigYamlReader();
        final String BUCKET_NAME =
            unravelConfigYamlReader.getAWSDetails().get(ConfigConstants.UnravelYamlConfig.BUCKET_NAME).toString();
        String objectName = "Unravel-" + System.currentTimeMillis() + ".png";
        AmazonS3 s3Client = getS3Client();
        URL url = null;
        try {
            Date expiration = new Date();
            long expTimeMillis = expiration.getTime();
            expTimeMillis += EXPIRE_TIME_IN_MILISEC;
            expiration.setTime(expTimeMillis);
            LOGGER.info("Upload file to AWS bucket: " + BUCKET_NAME,null);
            s3Client.putObject(new PutObjectRequest(
                BUCKET_NAME, objectName, new File(file)
            ));

            LOGGER.info("Creating presigned url to have public access",null);
            GeneratePresignedUrlRequest generatePresignedUrlGetRequest = new GeneratePresignedUrlRequest(BUCKET_NAME,
                objectName)
                .withMethod(HttpMethod.GET)
                .withExpiration(expiration);
            url = s3Client.generatePresignedUrl(generatePresignedUrlGetRequest);
            LOGGER.info("File uploaded successfully with public access.",null);
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
        return url.toString();
    }
}
