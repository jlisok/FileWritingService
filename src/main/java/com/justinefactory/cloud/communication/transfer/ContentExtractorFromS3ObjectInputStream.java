package com.justinefactory.cloud.communication.transfer;

import com.amazonaws.services.s3.model.S3ObjectInputStream;

public interface ContentExtractorFromS3ObjectInputStream<ContentType> extends ContentExtractor<S3ObjectInputStream, ContentType> {

}
