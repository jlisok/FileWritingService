package com.justinefactory.writing.writers.tocloud;

import com.amazonaws.services.s3.AmazonS3;
import com.justinefactory.domain.AwsInfo;
import com.justinefactory.writing.domain.JsonContent;
import com.justinefactory.writing.exceptions.AwsContentWritingException;
import com.justinefactory.writing.writers.ContentWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonAwsWriter implements ContentWriter<JsonContent, AwsInfo> {

    private final AmazonS3 client;
    private final Logger logger = LogManager.getLogger(this.getClass());

    public JsonAwsWriter(AmazonS3 awsClient) {
        client = awsClient;
    }

    @Override
    public void writeContent(JsonContent jsonContentStorage, AwsInfo awsInfo) throws AwsContentWritingException {
        String jsonToSend = jsonContentStorage.getContent();
        try {
            client.putObject(awsInfo.getBucketName(), awsInfo.getKeyName(), jsonToSend);
        } catch (Throwable e) {
            logger.warn("Trouble while writing content {} to AWS server", jsonToSend, e);
            throw new AwsContentWritingException(e, "Trouble while writing content " + jsonToSend + "to AWS server");
        }
    }
}
