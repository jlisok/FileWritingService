package com.justinefactory.testutil;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.justinefactory.sending.exceptions.AwsSecurityCredentialsException;
import com.justinefactory.cloud.communication.access.AwsSecurityCredentials;
import com.justinefactory.cloud.communication.clients.AwsS3Client;

public class AwsClientCreatorBeforeEach {

    public static AmazonS3 createAwsClient() throws AwsSecurityCredentialsException {
        AwsSecurityCredentials credentials = new AwsSecurityCredentials();
        AwsS3Client client = new AwsS3Client();
        return client.buildClient(Regions.EU_CENTRAL_1, credentials);
    }

}
