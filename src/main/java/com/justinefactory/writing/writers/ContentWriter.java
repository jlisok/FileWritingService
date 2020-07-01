package com.justinefactory.writing.writers;

import com.justinefactory.domain.WritingInfo;
import com.justinefactory.writing.exceptions.ContentWritingException;

public interface ContentWriter<ContentType, WritingInfo> {

    void writeContent(ContentType content, WritingInfo info) throws ContentWritingException;

}
