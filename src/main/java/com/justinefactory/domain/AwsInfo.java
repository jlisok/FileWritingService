package com.justinefactory.domain;

import java.net.URI;
import java.util.UUID;

public class AwsInfo implements WritingInfo {

    private final URI uri;
    private final UUID id;
    private final String BUCKET_NAME;
    private final String KEY_NAME;


    public AwsInfo(String bucketName, String keyName) {
        BUCKET_NAME = bucketName;
        KEY_NAME = keyName;
        uri = URI.create(BUCKET_NAME + "/" + KEY_NAME);
        id = UUID.randomUUID();
    }


    @Override
    public URI getURI() {
        return uri;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public String getBucketName() {
        return BUCKET_NAME;
    }

    public String getKeyName() {
        return KEY_NAME;
    }

}
