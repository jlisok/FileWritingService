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
import java.lang.reflect.Type;

class CloudObjectTransferService<Content, DeserializedContent> {

    private final CloudObjectContentGetter<Content> cloudObjectContentGetter;
    private final Deserializer<Content, DeserializedContent> deserializer;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    CloudObjectTransferService(CloudObjectContentGetter<Content> cloudObjectContentGetter, Deserializer<Content, DeserializedContent> deserializer) {
        logger.info("Transfer service - initialization");
        this.cloudObjectContentGetter = cloudObjectContentGetter;
        this.deserializer = deserializer;
    }

    public DeserializedContent getAndDeserializeCloudObject(AwsInfo info, Type classToDeserialize) throws IOException, AwsContentReadingException, ContentDeserializationException {
        Content input = cloudObjectContentGetter.getObjectContent(info);
        logger.debug("Transfer Service - extracting content from cloud object - success.");
        DeserializedContent deserializedContent = deserializer.deserialize(input, classToDeserialize);
        logger.debug("Transfer Service - content deserialization - success.");
        logger.info("Transfer Service - finished with success.");
        return deserializedContent;
    }

}
