package com.justinefactory.writing.converters;

import com.justinefactory.reading.exceptions.ContentStoringException;
import com.justinefactory.writing.exceptions.ContentConversion2ReadyToWriteException;

public interface ContentConverter<Content, ReadyToWriteContent> {

    public ReadyToWriteContent convertContent(Content content) throws ContentConversion2ReadyToWriteException, ContentStoringException;

}
