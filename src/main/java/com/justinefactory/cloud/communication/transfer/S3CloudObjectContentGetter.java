package com.justinefactory.cloud.communication.transfer;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.justinefactory.domain.AwsInfo;
import com.justinefactory.domain.Storage;
import com.justinefactory.reading.exceptions.AwsContentReadingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


public class S3CloudObjectContentGetter<Content> implements CloudObjectContentGetter<Content> {

    private final AmazonS3 client;
    private final ContentExtractorFromS3ObjectInputStream<Content> contentExtractor;
    private final Logger logger = LogManager.getLogger(this.getClass());

    public S3CloudObjectContentGetter(AmazonS3 awsClient, ContentExtractorFromS3ObjectInputStream<Content> contentExtractor) {
        client = awsClient;
        this.contentExtractor = contentExtractor;
    }

    @Override
    public Content getObjectContent(AwsInfo info) throws IOException, AwsContentReadingException {
        try (S3ObjectInputStream inputStream = client.getObject(new GetObjectRequest(info.getBucketName(), info.getKeyName())).getObjectContent()) {
            return contentExtractor.extractContent(inputStream);
        } catch (AmazonServiceException e) {
            logger.warn("Trouble with Amazon S3 while processing get request of {}.", info.getURI());
            throw new AmazonServiceException("Trouble with Amazon S3 while processing request on ." + info.getURI(), e);
        } catch (SdkClientException e) {
            logger.warn("Trouble with Amazon S3 connection while processing get request on {}.", info.getURI());
            throw new SdkClientException("Trouble with Amazon S3 connection.", e);
        }
    }
}
