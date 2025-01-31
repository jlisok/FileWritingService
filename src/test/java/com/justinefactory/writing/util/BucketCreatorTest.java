package com.justinefactory.writing.util;

import com.amazonaws.services.s3.AmazonS3;
import com.justinefactory.domain.AwsInfo;
import com.justinefactory.sending.exceptions.AwsSecurityCredentialsException;
import com.justinefactory.writing.exceptions.AwsContentWritingException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.justinefactory.testutil.AwsClientCreatorBeforeEach.createAwsClient;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BucketCreatorTest {

    private static AmazonS3 awsClient;
    private static String additionalBucket;

    @BeforeEach
    public void createAwsClientAccessKeyAndAwsInfo() throws AwsSecurityCredentialsException {
        awsClient = createAwsClient();
        Random random = new Random();
        additionalBucket = "com.justyna.lisok.factory.content-bucket-test-" + random.nextInt();
    }


    @AfterAll
    public static void removeAdditionalBucket() {
        awsClient.deleteBucket(additionalBucket);
    }


    @Test
    void createNonExistingStorageContainersWhenBucketExists() throws AwsContentWritingException {
        //given
        AwsInfo info = new AwsInfo("com.justyna.lisok.factory.content-bucket", "content.json");
        assertTrue(awsClient.doesBucketExistV2(info.getBucketName()));

        //when
        BucketCreator creator = new BucketCreator(awsClient);
        creator.createNonExistingStorageContainers(info);

        //then
        assertTrue(awsClient.doesBucketExistV2(info.getBucketName()));
    }


    @Test
    void createNonExistingStorageContainersWhenBucketDoesNotExist() throws AwsContentWritingException {
        //given
        AwsInfo info = new AwsInfo(additionalBucket, "content.json");
        assertFalse(awsClient.doesBucketExistV2(info.getBucketName()));


        //when
        BucketCreator creator = new BucketCreator(awsClient);
        creator.createNonExistingStorageContainers(info);

        //then
        assertTrue(awsClient.doesBucketExistV2(info.getBucketName()));
    }


}