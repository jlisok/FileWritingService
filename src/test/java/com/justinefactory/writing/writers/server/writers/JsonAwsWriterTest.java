package com.justinefactory.writing.writers.server.writers;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.justinefactory.domain.AwsInfo;
import com.justinefactory.sending.exceptions.AwsSecurityCredentialsException;
import com.justinefactory.writing.domain.ContentStorage;
import com.justinefactory.writing.exceptions.AwsContentWritingException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.justinefactory.testutil.AwsClientCreatorBeforeEach.createAwsClient;
import static org.junit.jupiter.api.Assertions.*;

class JsonAwsWriterTest {

    private static AmazonS3 awsClient;
    private static String jsonName = "content.json";
    private static String bucketName = "com.justyna.lisok.factory.content-bucket";

    @BeforeEach
    public void createClient() throws AwsSecurityCredentialsException {
        awsClient = createAwsClient();
    }

    @AfterAll
    public static void removeObjectFromAws() {
        awsClient.deleteObject(new DeleteObjectRequest(bucketName, jsonName));
    }

    @Test
    void writeContentWhenContentDoesNotExist() throws AwsContentWritingException {
        //given
        AwsInfo info = new AwsInfo(bucketName, jsonName);

        ContentStorage<String> readyToWriteContent = new ContentStorage<>();
        readyToWriteContent.addContent("\"{\\n\" +\n" +
                "                \"  \\\"content\\\": {\\n\" +\n" +
                "                \"    \\\"content\\\": [\\n\" +\n" +
                "                \"      {\\n\" +\n" +
                "                \"        \\\"timeStamp\\\": 1590147349818750700,\\n\" +\n" +
                "                \"        \\\"randomInt\\\": -840762737,\\n\" +\n" +
                "                \"        \\\"randomString\\\": \\\"ChristopherRobin\\\"\\n\" +\n" +
                "                \"      }\\n\" +\n" +
                "                \"    ]\\n\" +\n" +
                "                \"  },\\n\" +\n" +
                "                \"  \\\"stats\\\": {\\n\" +\n" +
                "                \"    \\\"count\\\": 1,\\n\" +\n" +
                "                \"    \\\"distinctCount\\\": 1,\\n\" +\n" +
                "                \"    \\\"max\\\": {\\n\" +\n" +
                "                \"      \\\"timeStamp\\\": 1590147349818750700,\\n\" +\n" +
                "                \"      \\\"randomInt\\\": -840762737,\\n\" +\n" +
                "                \"      \\\"randomString\\\": \\\"ChristopherRobin\\\"\\n\" +\n" +
                "                \"    }\\n\" +\n" +
                "                \"  }\\n\" +\n" +
                "                \"}\";");
        JsonAwsWriter writer = new JsonAwsWriter(awsClient);

        //when
        writer.writeContent(readyToWriteContent, info);

        //then
        assertTrue(awsClient.doesObjectExist(info.getBucketName(),info.getKeyName()));
        assertTrue(awsClient.getObjectMetadata(info.getBucketName(),info.getKeyName()).getContentLength() > 0);
    }
}