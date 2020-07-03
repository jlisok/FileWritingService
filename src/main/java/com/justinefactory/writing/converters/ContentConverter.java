package com.justinefactory.writing.converters;

import com.justinefactory.writing.exceptions.ContentConversion2ReadyToWriteException;

public interface ContentConverter<Content, ReadyToWriteContent> {

    ReadyToWriteContent convertContent(Content content) throws ContentConversion2ReadyToWriteException;

}
