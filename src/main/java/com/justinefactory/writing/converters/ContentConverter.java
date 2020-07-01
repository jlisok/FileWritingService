package com.justinefactory.writing.converters;

import com.justinefactory.writing.exceptions.ContentConversion2ReadyToWriteException;

public interface ContentConverter<ContentType, ReadyToWriteContent> {

    ReadyToWriteContent convertContent(ContentType content) throws ContentConversion2ReadyToWriteException;

}
