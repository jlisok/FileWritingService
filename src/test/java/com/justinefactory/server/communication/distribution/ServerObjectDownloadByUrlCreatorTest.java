package com.justinefactory.server.communication.distribution;

import com.amazonaws.services.s3.AmazonS3;
import com.justinefactory.domain.AwsInfo;
import com.justinefactory.sending.exceptions.AwsSecurityCredentialsException;
import com.justinefactory.util.TimeCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.time.Duration;

import static com.justinefactory.testutil.AwsClientCreatorBeforeEach.createAwsClient;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ServerObjectDownloadByUrlCreatorTest {

    private static AmazonS3 awsClient;
    private static AwsInfo info;
    private static TimeCalculator timeCalculator;
    private static PresignedUrlRequest urlRequest;

    @BeforeEach
    public void createAwsClientAccessKeyAndAwsInfo() throws AwsSecurityCredentialsException {
        awsClient = createAwsClient();
        timeCalculator = new TimeCalculator();
        urlRequest = new PresignedUrlRequest();
        info = new AwsInfo("com.justyna.lisok.factory.content-bucket", "test.txt");
    }

    @Test
    void createAccessWithPresignedUrl() {
        //given
        ServerObjectDownloadByUrlCreator urlCreator = new ServerObjectDownloadByUrlCreator(timeCalculator, urlRequest);

        //when
        Duration duration = Duration.ofMinutes(30);
        URL url = urlCreator.createAccessWithPresignedUrl(awsClient, info, duration);

        //then
        assertFalse(url.toString().isEmpty());
    }
}