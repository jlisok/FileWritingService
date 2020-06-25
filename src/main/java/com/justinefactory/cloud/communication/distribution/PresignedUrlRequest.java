package com.justinefactory.cloud.communication.distribution;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.justinefactory.domain.AwsInfo;

import java.util.Date;
import java.time.Instant;

public class PresignedUrlRequest {

    public GeneratePresignedUrlRequest generateRequest(AwsInfo info, Instant expiration) {
        Date expirationDate =  Date.from(expiration);
        return new GeneratePresignedUrlRequest(info.getBucketName(), info.getKeyName())
                .withMethod(HttpMethod.GET)
                .withExpiration(expirationDate);
    }
}
