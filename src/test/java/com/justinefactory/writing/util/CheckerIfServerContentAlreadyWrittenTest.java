package com.justinefactory.writing.util;

import com.amazonaws.services.s3.AmazonS3;
import com.justinefactory.domain.AwsInfo;
import com.justinefactory.sending.exceptions.AwsSecurityCredentialsException;
import com.justinefactory.writing.exceptions.AwsContentWritingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.justinefactory.testutil.AwsClientCreatorBeforeEach.createAwsClient;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CheckerIfServerContentAlreadyWrittenTest {

    private static AmazonS3 awsClient;

    @BeforeEach
    public void createAwsClientAccessKeyAndAwsInfo() throws AwsSecurityCredentialsException {
        awsClient = createAwsClient();
    }

    @Test
    public void assureNotExistWhenFileDoesNotExistButBucketExists() throws AwsContentWritingException {
        //given
        AwsInfo info = new AwsInfo("com.justyna.lisok.factory.content-bucket", "content.json");

        //when
        CheckerIfServerContentAlreadyWritten checker = new CheckerIfServerContentAlreadyWritten(awsClient);

        //then
        checker.assureNotExist(info);
    }

    @Test
    public void assureNotExistWhenFileAndBucketDoesNotExist() throws AwsContentWritingException {
        //given
        AwsInfo info = new AwsInfo("com.justyna.lisok.factory.content", "content.json");

        //when
        CheckerIfServerContentAlreadyWritten checker = new CheckerIfServerContentAlreadyWritten(awsClient);

        //then
        checker.assureNotExist(info);
    }

    @Test
    public void assureNotExistWhenFileAndBucketExists() {
        //given
        AwsInfo info = new AwsInfo("com.justyna.lisok.factory.content-bucket", "test.txt");

        //when
        CheckerIfServerContentAlreadyWritten checker = new CheckerIfServerContentAlreadyWritten(awsClient);

        //then
        assertThrows(AwsContentWritingException.class, () -> checker.assureNotExist(info));
    }

}