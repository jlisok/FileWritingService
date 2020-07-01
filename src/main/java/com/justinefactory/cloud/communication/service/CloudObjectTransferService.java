package com.justinefactory.cloud.communication.service;


import com.justinefactory.cloud.communication.transfer.CloudObjectContentGetter;
import com.justinefactory.deserialization.deserializers.Deserializer;
import com.justinefactory.domain.AwsInfo;
import com.justinefactory.reading.exceptions.AwsContentReadingException;
import com.justinefactory.reading.exceptions.ContentDeserializationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

class CloudObjectTransferService<ContentType, DeserializedContent> {

    private final CloudObjectContentGetter<ContentType> cloudObjectContentGetter;
    private final Deserializer<ContentType, DeserializedContent> deserializer;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    CloudObjectTransferService(CloudObjectContentGetter<ContentType> cloudObjectContentGetter, Deserializer<ContentType, DeserializedContent> deserializer) {
        logger.info("Transfer service - initialization");
        this.cloudObjectContentGetter = cloudObjectContentGetter;
        this.deserializer = deserializer;
    }

    public DeserializedContent getAndDeserializeCloudObject(AwsInfo info, Class<DeserializedContent> classToDeserialize) throws IOException, AwsContentReadingException, ContentDeserializationException {
        ContentType input = cloudObjectContentGetter.getObjectContent(info);
        logger.debug("Transfer Service - extracting content from cloud object - success.");
        DeserializedContent deserializedContent = deserializer.deserialize(input, classToDeserialize);
        logger.info("Transfer Service - finished with success.");
        return deserializedContent;
    }

}
