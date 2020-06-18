package com.justinefactory.writing.util;

import com.amazonaws.services.s3.AmazonS3;
import com.justinefactory.domain.AwsInfo;
import com.justinefactory.writing.exceptions.AwsContentWritingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BucketCreator implements StorageContainerCreator<AwsInfo> {

    private final AmazonS3 client;
    private final Logger logger = LogManager.getLogger(this.getClass());


    public BucketCreator(AmazonS3 awsClient) {
        client = awsClient;
    }

    @Override
    public void createNonExistingStorageContainers(AwsInfo awsInfo) throws AwsContentWritingException {
        if (client.doesBucketExistV2(awsInfo.getBucketName())) return;
        try {
            client.createBucket(awsInfo.getBucketName());
        } catch (Throwable e) {
            logger.warn("Trouble while creating new AWS Bucket {}", awsInfo.getBucketName(), e);
            throw new AwsContentWritingException(e, "Trouble while creating new AWS Bucket." + awsInfo.getBucketName());
        }
    }
}
