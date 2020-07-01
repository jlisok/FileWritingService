package com.justinefactory.cloud.communication.transfer;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.justinefactory.reading.exceptions.AwsContentReadingException;
import com.justinefactory.writing.domain.Json;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


public class JsonExtractorFromS3ObjectInputStream implements ContentExtractorFromS3ObjectInputStream<Json> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public Json extractContent(S3ObjectInputStream inputStream) throws AwsContentReadingException {
        if (inputStream == null) {
            throw new AwsContentReadingException("Trouble while processing inputStream - input was empty.");
        }
        try {
            return new Json(IOUtils.toString(inputStream));
        } catch (IOException e) {
            logger.warn("Trouble while extracting json object from inputStream {}", inputStream, e);
            throw new AwsContentReadingException(e, "Trouble while extracting json object from inputStream " + inputStream);
        }
    }
}
