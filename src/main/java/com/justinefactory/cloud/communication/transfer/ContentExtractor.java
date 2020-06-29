package com.justinefactory.cloud.communication.transfer;

import com.justinefactory.reading.exceptions.AwsContentReadingException;

public interface ContentExtractor<Object, Content> {

    Content extractContent(Object object) throws AwsContentReadingException;

}
