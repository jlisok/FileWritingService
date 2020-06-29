package com.justinefactory.cloud.communication.transfer;

import com.amazonaws.services.s3.model.S3ObjectInputStream;

public interface ContentExtractorFromS3ObjectInputStream<Content> extends ContentExtractor<S3ObjectInputStream, Content> {

}
