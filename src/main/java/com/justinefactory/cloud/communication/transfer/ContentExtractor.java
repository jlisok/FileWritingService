package com.justinefactory.cloud.communication.transfer;

import com.justinefactory.reading.exceptions.AwsContentReadingException;

public interface ContentExtractor<Object, ContentType> {

    ContentType extractContent(Object object) throws AwsContentReadingException;

}
