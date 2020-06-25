package com.justinefactory.cloud.communication.clients;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.internal.S3DirectSpi;
import com.justinefactory.cloud.communication.access.AwsSecurityCredentials;

public interface AwsClient {

    S3DirectSpi buildClient(Regions region, AwsSecurityCredentials awsSecurityCredentials);
}
