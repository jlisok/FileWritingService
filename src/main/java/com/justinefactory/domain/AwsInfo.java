package com.justinefactory.domain;

import java.net.URI;
import java.util.UUID;

public class AwsInfo implements WritingInfo {

    private final URI uri;
    private final UUID id;
    private final String bucketName;
    private final String keyName;


    public AwsInfo(String bucketName, String keyName) {
        this.bucketName = bucketName;
        this.keyName = keyName;
        uri = URI.create("s3://" + this.bucketName + "/" + this.keyName);
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
        return bucketName;
    }

    public String getKeyName() {
        return keyName;
    }

}
