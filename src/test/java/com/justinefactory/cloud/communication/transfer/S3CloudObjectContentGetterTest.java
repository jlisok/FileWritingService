package com.justinefactory.cloud.communication.transfer;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.justinefactory.domain.AwsInfo;
import com.justinefactory.reading.exceptions.AwsContentReadingException;
import com.justinefactory.sending.exceptions.AwsSecurityCredentialsException;
import com.justinefactory.writing.domain.Json;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.justinefactory.testutil.AwsClientCreatorBeforeEach.createAwsClient;
import static org.junit.jupiter.api.Assertions.*;

class S3CloudObjectContentGetterTest {

    private static AmazonS3 awsClient;
    private static AwsInfo info;

    @BeforeEach
    public void createAwsClientAndAwsInfo() throws AwsSecurityCredentialsException {
        awsClient = createAwsClient();
        info = new AwsInfo("com.justyna.lisok.factory.content-bucket", "three-element-content-test.json");
    }


    @Test
    public void getObjectWhenMeetingConditions() throws IOException, AwsContentReadingException {
        //given
        JsonExtractorFromS3ObjectInputStream extractor = new JsonExtractorFromS3ObjectInputStream();
        S3CloudObjectContentGetter<Json> getter = new S3CloudObjectContentGetter<>(awsClient, extractor);

        //when
        Json storage = getter.getObjectContent(info);

        //then
        assertNotNull(storage.getContent());
        assertFalse(storage.getContent().isEmpty());
    }


    @Test
    public void getObjectWhenNoSuchFile() {
        //given
        JsonExtractorFromS3ObjectInputStream extractor = new JsonExtractorFromS3ObjectInputStream();
        S3CloudObjectContentGetter<Json> getter = new S3CloudObjectContentGetter<>(awsClient, extractor);

        //when
        AwsInfo infoNoSuchFile = new AwsInfo("com.justyna.lisok.factory.content-bucket", "three-element-content-test-no-such-file.json");

        //then
        assertThrows(AmazonServiceException.class, () -> getter.getObjectContent(infoNoSuchFile));
    }

}