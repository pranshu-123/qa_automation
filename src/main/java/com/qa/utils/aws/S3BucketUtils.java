package com.qa.utils.aws;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import java.io.File;

public class S3BucketUtils {

    private AmazonS3 getS3Client() {
        Regions clientRegion = Regions.AP_SOUTH_1;
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
            .withRegion(clientRegion)
            .build();
        return s3Client;
    }

    public String uploadFileToS3Bucket(String file) {
        String bucketName = "ankur-unravel-test";
        String objectName = "Unravel-" + System.currentTimeMillis() + ".png";
        String stringObjKeyName = "screenshots/" + objectName;
        AmazonS3 s3Client = getS3Client();
        try {
            // Upload a text string as a new object.
            s3Client.putObject(new PutObjectRequest(
                bucketName, stringObjKeyName, new File(file)
            ).withCannedAcl(CannedAccessControlList.PublicRead));
            // Upload a file as a new object with ContentType and title specified.
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
        return s3Client.getUrl("ankur-unravel-test",
            "screenshots/" + objectName).toExternalForm();
    }

    public static void main(String[] args) {
        S3BucketUtils s3 = new S3BucketUtils();
        s3.uploadFileToS3Bucket("/home/ankur/Unravel/projects/UnravelUIAutomation/screenshots/screenshot10511980770033368.png");
    }
}
