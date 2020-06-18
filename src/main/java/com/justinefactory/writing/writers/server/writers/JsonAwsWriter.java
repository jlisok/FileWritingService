package com.justinefactory.writing.writers.server.writers;

import com.amazonaws.services.s3.AmazonS3;
import com.justinefactory.domain.AwsInfo;
import com.justinefactory.writing.domain.ContentStorage;
import com.justinefactory.writing.exceptions.AwsContentWritingException;
import com.justinefactory.writing.writers.ContentWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonAwsWriter implements ContentWriter<ContentStorage<String>, AwsInfo> {

    private final AmazonS3 client;
    private final Logger logger = LogManager.getLogger(this.getClass());

    public JsonAwsWriter(AmazonS3 awsClient) {
        client = awsClient;
    }

    @Override
    public void writeContent(ContentStorage<String> json, AwsInfo awsInfo) throws AwsContentWritingException {
        try {
            client.putObject(awsInfo.getBucketName(), awsInfo.getKeyName(), json.getAllContent().toString());
        } catch (Throwable e) {
            logger.warn("Trouble while writing content {} to AWS server", json.getAllContent().toString(), e);
            throw new AwsContentWritingException(e, "Trouble while writing content " + json.getAllContent().toString() + "to AWS server");
        }
    }
}
