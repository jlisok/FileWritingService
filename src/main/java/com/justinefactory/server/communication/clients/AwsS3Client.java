package com.justinefactory.server.communication.clients;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.justinefactory.server.communication.access.AwsSecurityCredentials;

public class AwsS3Client implements AwsClient {

    @Override
    public AmazonS3 buildClient(Regions region, AwsSecurityCredentials awsSecurityCredentials) {
        try {
            return AmazonS3ClientBuilder
                    .standard()
                    .withCredentials(new AWSStaticCredentialsProvider(awsSecurityCredentials.getCredentials()))
                    .withRegion(region)
                    .build();
        } catch (Throwable e) {
            throw new AmazonClientException("Trouble while building an AWSS3 client.", e);
        }
    }
}
