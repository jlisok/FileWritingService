package com.justinefactory.cloud.communication.transfer;

import com.justinefactory.domain.AwsInfo;
import com.justinefactory.reading.exceptions.AwsContentReadingException;

import java.io.IOException;

public interface CloudObjectContentGetter<ContentType> {

    ContentType getObjectContent(AwsInfo info) throws IOException, AwsContentReadingException;
}
