package com.justinefactory.server.communication.distribution;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.justinefactory.domain.AwsInfo;

import java.util.Date;

public class PresignedUrlRequest {

    public GeneratePresignedUrlRequest generateRequest(AwsInfo info, Date expiration) {
        return new GeneratePresignedUrlRequest(info.getBucketName(), info.getKeyName())
                .withMethod(HttpMethod.GET)
                .withExpiration(expiration);
    }
}
