package com.justinefactory.writing.util;

import com.amazonaws.services.s3.AmazonS3;
import com.justinefactory.domain.AwsInfo;
import com.justinefactory.writing.exceptions.AwsContentWritingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckerIfServerContentAlreadyWritten implements CheckerIfContentAlreadyWritten<AwsInfo> {

    private final AmazonS3 client;
    private final Logger logger = LogManager.getLogger(this.getClass());

    public CheckerIfServerContentAlreadyWritten(AmazonS3 awsClient) {
        client = awsClient;
    }

    @Override
    public void assureNotExist(AwsInfo awsInfo) throws AwsContentWritingException {
        if (client.doesObjectExist(awsInfo.getBucketName(), awsInfo.getKeyName())) {
            logger.warn("Could not create a file {}. File already exists.", awsInfo.getURI());
            throw new AwsContentWritingException("Could not create file " + awsInfo.getURI() + ". File already exists.");
        }
    }
}
